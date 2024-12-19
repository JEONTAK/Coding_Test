package BaekJoon.Gold3.캐슬디펜스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17135 {

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, D, result = 0;
    static int[][] map;
    static boolean[] check;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check = new boolean[M + 1];
        BruteForce(0, 0, new ArrayList<>());
        System.out.println(result);
    }

    static void BruteForce(int cnt, int idx, ArrayList<Node> archer) {
        if (cnt == 3) {
            BFS(archer);
        }

        for (int i = idx; i < M; i++) {
            if (!check[i]) {
                check[i] = true;
                archer.add(new Node(N, i));
                BruteForce(cnt + 1, i, archer);
                check[i] = false;
                archer.remove(archer.size() - 1);
            }
        }
    }

    static void BFS(ArrayList<Node> archer) {
        int[][] copyM = copyMap();
        int enemyRemoveCnt = 0;

        while (hasEnemy(copyM)) {
            Set<Node> targets = new HashSet<>();
            for (Node cur : archer) {
                Node target = findTarget(copyM, cur);
                if (target != null) {
                    targets.add(target);
                }
            }
            for (Node target : targets) {
                if (copyM[target.x][target.y] == 1) {
                    copyM[target.x][target.y] = 0;
                    enemyRemoveCnt++;
                }
            }
            moveEnemy(copyM);
        }
        result = Math.max(result, enemyRemoveCnt);
    }

    static void moveEnemy(int[][] copyM) {
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                copyM[i + 1][j] = copyM[i][j];
            }
        }
        Arrays.fill(copyM[0], 0);
    }

    static Node findTarget(int[][] copyM, Node cur) {
        Node target = null;
        int minDist = D + 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (copyM[i][j] == 1) {
                    int dist = Math.abs(cur.x - i) + Math.abs(cur.y - j);
                    if (dist <= D) {
                        if (dist < minDist || (dist == minDist && j < target.y)) {
                            target = new Node(i, j);
                            minDist = dist;
                        }
                    }
                }
            }
        }
        return target;
    }

    static boolean hasEnemy(int[][] copyM) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyM[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int[][] copyMap() {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = map[i][j];
            }
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BOJ17135.solution();
    }
}

/*
문제 분석
1. 정보
     - N * M 크기의 격자판
        - 0 : 빈칸
        - 1 : 적
     - 각 칸에 포함된 적의 수는 최대 1명
     - N번 행의 바로 아래(N + 1)번의 모든 칸에는 성이 존재
     - 궁수 3명을 배치
        - 궁수는 성이 있는 칸에 배치 가능
        - 한 칸에는 최대 1명의 궁수 배치 가능
        - 각 턴마다 궁수는 적 하나 공격 가능하고, 모든 궁수가 동시에 공격
        - 궁수가 공격하는 적은 거리 D 이하인 적 중에서 가장 가깝고, 가까운 적이 여럿일 경우 가장 왼쪽의 적 공격
        - 여러 궁수가 같은 적 공격 가능
        - 적은 공격 당하면, 게임에서 제거
     - 이후 적은 한칸 아래로 이동, 성이 있는 칸으로 이동한 경우, 게임에서 제거
     - 모든 적이 사라지면, 게임이 끝
     - 격자판의 두 위치 (r_1,c_1), (r_2,c_2)의 거리는 |r_1 - r_2| + |c_1 + c_2|
2. 목표
    - 3명의 궁수를 배치하여 가장 많은 적을 제거하는 경우의 수를 출력

3. 제약 조건
    3 <= N, M <= 15
    1 <= D <= 10
풀이
1. 알고리즘
    - 브루트포스
        - 3명의 궁수의 위치에 대한 모든 경우의 수 구함
    - BFS
        - BFS 사용하여 시뮬레이션 진행

2. 탐색 과정
    1. 격자판의 정보 저장
    2. 브루트포스 알고리즘 통해 3명의 궁수의 위치에 대한 경우의 수 구함
    3. 궁수의 위치가 정해졌을 경우 적의 위치를 이용해 시뮬레이션
        - 적이 존재할 경우
            - 모든 궁수 별로 죽일 수 있는 적을 찾음
            - 해당 적의 칸 0으로 업데이트
            - 죽인 킬수 더해줌
            - 적들이 한칸 아래로 이동
    4. 모든 적이 죽었을 경우, 죽인 적의 수를 결과값과 비교후 업데이트
    5. 2번으로 돌아가 실행
 */
