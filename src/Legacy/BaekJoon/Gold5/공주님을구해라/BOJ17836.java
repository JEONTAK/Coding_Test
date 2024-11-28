package BaekJoon.Gold5.공주님을구해라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17836 {

    static class Node{
        int x;
        int y;
        int t;
        boolean s;

        public Node(int x, int y, int t, boolean s) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.s = s;
        }
    }

    static int N, M, T;
    static int[][] map;
    static int result = Integer.MAX_VALUE;
    static boolean[][][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N][M][2];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, false));
        visited[0][0][0] = true;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.x == N - 1 && cur.y == M - 1) {
                result = Math.min(result, cur.t);
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isAvailable(nx, ny)) {
                    if (!cur.s) {
                        if (!visited[nx][ny][0] && map[nx][ny] == 0) {
                            visited[nx][ny][0] = true;
                            q.add(new Node(nx, ny, cur.t + 1, false));
                        } else if (!visited[nx][ny][0] && map[nx][ny] == 2) {
                            visited[nx][ny][0] = true;
                            q.add(new Node(nx, ny, cur.t + 1, true));
                        }
                    }else{
                        if (!visited[nx][ny][1]) {
                            visited[nx][ny][1] = true;
                            q.add(new Node(nx, ny, cur.t + 1, true));
                        }
                    }
                }
            }
        }
        if (result > T) {
            System.out.println("Fail");
        }else{
            System.out.println(result);
        }
    }

    static boolean isAvailable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BOJ17836.solution();
    }
}
