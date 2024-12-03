package BaekJoon.Level3.불;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179 {

    static class Node{
        int x;
        int y;
        int t;

        public Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    static int R, C;
    static char[][] map;
    static int[][] fireTime;
    static Node jihun;
    static Queue<Node> q = new LinkedList<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int result = -1;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        fireTime = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(fireTime[i], -1);
        }
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'J') {
                    jihun = new Node(i, j, 0);
                } else if (map[i][j] == 'F') {
                    visited[i][j] = true;
                    q.add(new Node(i, j, 0));
                }
            }
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();
            fireTime[cur.x][cur.y] = cur.t;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isAvailable(nx, ny) && map[nx][ny] != '#' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, cur.t + 1));
                }
            }
        }

        q.clear();
        q.add(jihun);
        visited = new boolean[R][C];
        visited[jihun.x][jihun.y] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == R - 1 || cur.y == C - 1 || cur.x == 0 || cur.y == 0) {
                result = cur.t + 1;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isAvailable(nx, ny) && map[nx][ny] != '#' && !visited[nx][ny] && (fireTime[nx][ny] == -1 || cur.t + 1 < fireTime[nx][ny])) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, cur.t + 1));
                }
            }
        }

        System.out.println(result == -1 ? "IMPOSSIBLE" : result);
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) throws IOException {
        BOJ4179.solution();
    }
}

/*
문제 분석
    1. 정보
        - 미로의 크기 : R * C (1 <= R,C <= 1000)
        - 미로의 구성
            - # : 벽
            - . : 지나갈 수 있는 공간
            - J : 지훈이의 초기 위치
            - F : 불이 난 공간
        - 이동 규칙
            - 지훈이와 불은 매 분마다 상 하 좌 우로 이동 가능
            - 벽(#)은 통과 불가
            - 불은 네 방향으로 동시에 확산, 지훈이가 지나가는 칸도 태움

    2. 목표
        - 지훈이가 불이 붙기 전에 미로의 가장자리를 통해 탈출할 수 있는 최소 시간을 계산
        - 탈출이 불가능한 경우 IMPOSSIBLE 출력

    3. 제약 조건
        - 불과 지훈이의 이동
            - 불은 모든 방향으로 확산 -> BFS 통해 각 칸에 도달하는 시간 계산
            - 지훈이는 불이 퍼지기 전에만 이동 가능 -> BFS로 탈출 경로 탐색
        - 탈출 조건 : 지훈이가 가장자리에 칸에 도달하면 탈출 성공

풀이
    1. BFS 활용
        - 불의 확산 시간 계산
            - 불이 각 칸에 도달한느 최소 시간을 BFS 통해 계산
        - 지훈이의 탈출 경로 탐색
            - 지훈이가 각 칸에 도달하는 시간과 해당 칸의 불 도달 시간 비교하며 BFS 탐색

    2. 탐색 과정
        1. 불과 지훈이의 초기 위치 큐에 삽입
        2. BFS로 불이 각 칸에 도달하는 시간 먼저 계산
        3. BFS로 지훈이의 탈출 경로 계산

    3. 시간 복잡도
        O(R * C) -> 최대 O(10^6)
주의 할 점
    1. 불은 여러곳에서 동시에 퍼질 수 있다.(불은 한 곳에만 나오는 것이 아니다)
    2. 불의 시간을 -1로 초기화 해 불이 퍼지지 않은 곳에는 정상적으로 지훈이가 갈 수 있도록 처리해 주어야 한다.
 */