package BaekJoon.Gold5.상범빌딩;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6593 {

    static class Node{
        int z;
        int x;
        int y;
        int t;

        public Node(int z, int x, int y, int t) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    static int L, R, C;
    static int[][][] map;
    static boolean[][][] visited;
    static int sx, sy, sz;
    static int[][] delta = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        while(L != 0 && R != 0 && C != 0) {
            map = new int[L][R][C];
            visited = new boolean[L][R][C];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String temp = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = temp.charAt(k);
                        if (map[i][j][k] == 'S') {
                            sz = i;
                            sx = j;
                            sy = k;
                        }
                    }
                }
                br.readLine();
            }
            Queue<Node> q = new LinkedList<>();
            q.add(new Node(sz, sx, sy, 0));
            visited[sz][sx][sy] = true;
            boolean flag = false;
            while (!q.isEmpty()) {
                Node cur = q.poll();
                if (map[cur.z][cur.x][cur.y] == 'E') {
                    sb.append("Escaped in " + cur.t + " minute(s).\n");
                    flag = true;
                    break;
                }

                for (int i = 0; i < 6; i++) {
                    int nz = cur.z + delta[i][0];
                    int nx = cur.x + delta[i][1];
                    int ny = cur.y + delta[i][2];
                    if (isAvailable(nz, nx, ny) && !visited[nz][nx][ny]) {
                        if (map[nz][nx][ny] != '#') {
                            visited[nz][nx][ny] = true;
                            q.add(new Node(nz, nx, ny, cur.t + 1));
                        }
                    }
                }
            }
            if (!flag) {
                sb.append("Trapped!\n");
            }
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb.toString());
    }

    private static boolean isAvailable(int nz, int nx, int ny) {
        return nz >= 0 && nx >= 0 && ny >= 0 && nz < L && nx < R && ny < C;
    }

    public static void main(String[] args) throws IOException {
        BOJ6593.solution();
    }
}
