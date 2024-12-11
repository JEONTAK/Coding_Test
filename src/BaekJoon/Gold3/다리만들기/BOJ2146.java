package BaekJoon.Gold3.다리만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2146 {

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }

    static int N, islandCnt = 0;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] check;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int result = Integer.MAX_VALUE;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, ++islandCnt);
                }
            }
        }

        check = new boolean[islandCnt + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && !check[map[i][j]]) {
                    check[map[i][j]] = true;
                    result = Math.min(result, makeBridge(i, j, map[i][j]));
                }
            }
        }
        System.out.println(result - 1);
    }

    private static int makeBridge(int x, int y, int idx) {

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(x, y, 0));
        visited = new boolean[N][N];
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (map[cur.x][cur.y] != 0 && map[cur.x][cur.y] != idx) {
                return cur.d;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isAvailable(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, map[nx][ny] == idx ? cur.d : cur.d + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static void bfs(int x, int y, int idx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        map[x][y] = idx;
        visited[x][y] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isAvailable(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
                    map[nx][ny] = idx;
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ2146.solution();
    }
}

/*
문제 분석
1. 정보
    - 지도 N * N (1 <= N <= 100)
    - 0 : 바다
    - 1 : 섬(대륙)

2. 목표
    - 두 대륙을 연결하는 가장 짧은 다리의 길이를 출력

3. 제약 조건
    - 항상 두 개 이상의 섬을 데이터로 주어짐

풀이
1. 알고리즘
    - BFS
        - 먼저 BFS를 활용해 대륙을 설정해준다.
            - 각 대륙에 번호를 매겨 대륙을 구별할 수 있도록 해줌

    - 우선순위 큐
        - 이후 대륙 간 다리를 놓을 수 있는 최소 거리를 비교하여 최솟값을 구한다.
2. 탐색 과정
        - BFS
            - 먼저 BFS를 활용해 대륙을 설정해준다.
                - 각 대륙에 번호를 매겨 대륙을 구별할 수 있도록 해줌
        - 우선순위 큐
            - 이후 대륙 간 다리를 놓을 수 있는 최소 거리를 비교하여 최솟값을 구한다.
            - 우선순위 큐를 사용해 설치한 다리의 개수가 작은 좌표부터 꺼내 해당 좌표가 다른 대륙일 경우 다리의 개수를 반환해준다.
 */
