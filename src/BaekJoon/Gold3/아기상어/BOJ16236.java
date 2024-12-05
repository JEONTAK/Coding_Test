package BaekJoon.Gold3.아기상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {

    static class Shark implements Comparable<Shark>{
        int x;
        int y;
        int size;
        int eatCnt;
        int d;

        public Shark(int x, int y, int size, int eatCnt) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.eatCnt = eatCnt;
        }

        public Shark(int x, int y, int size, int eatCnt, int d) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.eatCnt = eatCnt;
            this.d = d;
        }

        @Override
        public int compareTo(Shark o) {
            if (this.d == o.d) {
                if (this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.d - o.d;
        }
    }

    static int N, result = 0;
    static int[][] map;
    static Shark baby;
    static PriorityQueue<Shark> canEat = new PriorityQueue<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    baby = new Shark(i, j, 2, 0);
                }
            }
        }

        while(true){
            canEat.clear();
            if (findShark()) {
                break;
            }

            Shark eatShark = canEat.poll();
            map[eatShark.x][eatShark.y] = 9;
            map[baby.x][baby.y] = 0;
            result += eatShark.d;
            baby.x = eatShark.x;
            baby.y = eatShark.y;
            baby.eatCnt++;
            if (baby.eatCnt == baby.size) {
                baby.size++;
                baby.eatCnt = 0;
            }
        }

        System.out.println(result);
    }

    static boolean findShark(){
        Queue<Shark> q = new LinkedList<>();
        q.add(new Shark(baby.x, baby.y, baby.size, baby.eatCnt, baby.d));
        boolean[][] visited = new boolean[N][N];
        visited[baby.x][baby.y] = true;
        while (!q.isEmpty()) {
            Shark cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isAvailable(nx, ny) && !visited[nx][ny] && cur.size >= map[nx][ny]) {
                    visited[nx][ny] = true;
                    if (map[nx][ny] >= 1 && map[nx][ny] < cur.size) {
                        canEat.add(new Shark(nx, ny, cur.size, cur.eatCnt, cur.d + 1));
                    }else{
                        q.add(new Shark(nx, ny, cur.size, cur.eatCnt, cur.d + 1));
                    }
                }
            }
        }

        return canEat.isEmpty();
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ16236.solution();
    }
}

/*
문제 분석
1. 정보
- 공간의 크기 N * N (2 <= N <= 20)
- 칸의 정보
    - 0 : 빈칸
    - 1 ~ 6 : 해당 숫자의 크기를 가지고 있는 물고기
    - 9 : 아기 상어의 위치
- 아기 상어 :
    - 초기 크기 : 2
    - 물고기 먹기 :
        - 자신의 크기보다 작은 물고기만 먹기 가능
        - 크기가 같다면 지나갈 순 있지만 먹기 불가능
        - 크기가 크다면 이동 불가
2. 목표
    - 아기 상어가 물고기를 먹어 크기를 키우며 이동할 수 있는 시간 계산
        - 먹을 수 있는 물고기가 없을 때까지 시간 출력
3. 제약 조건
    - 물고기 선택 규칙
        - 거리 기준 : 가장 가까운 물고기
        - 위치 기준(거리 같을 경우) : 가장 위쪽 -> 가장 왼쪽
    - 아기 상어 크기 증가
        - 자신의 크기만큼 물고기 먹으면 크기 1 증가
풀이
1. BFS 탐색
    - 상 하 좌 우 물고기 탐색
    - 가장 가까운 물고기 선택
2. 탐색 및 먹기 과정
    - 초기 상태
        - 공간에서 아기 상어 위치 찾고 BFS 시작
    - 물고기 탐색
        - 먹을 수 있는 물고기 중 가장 가까운 물고기 선택
        - 물고기 먹으면 아기 상어 크기와 먹은 물고기 개수 갱신
    - 이동 시간 누적
        - 이동 거리 누적하여 총 시간 계산
3. 종료 조건
    - 먹을 수 있는 물고기가 없으면 시뮬레이션 종료
 */
