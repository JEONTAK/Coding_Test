package BaekJoon.Gold5.토마토2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {

    static class Tomato implements Comparable<Tomato>{
        int z;
        int x;
        int y;
        int day;

        public Tomato(int z, int x, int y, int day) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.day = day;
        }

        @Override
        public int compareTo(Tomato o) {
            return this.day - o.day;
        }
    }

    static int M, N, H, T = 0, result = Integer.MAX_VALUE;
    static int[][][] map;
    static PriorityQueue<Tomato> q = new PriorityQueue<>();
    static int[][] delta = {{-1, 0, 0}, {1, 0, 0}, {0, 0, -1}, {0, 0, 1}, {0, -1, 0}, {0, 1, 0}};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N][M];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 1) {
                        q.offer(new Tomato(i, j, k, 0));
                    } else if (map[i][j][k] == 0) {
                        T++;
                    }
                }
            }
        }
        if (T == 0) {
            System.out.println(0);
            return;
        }
        while (!q.isEmpty()) {
            Tomato cur = q.poll();
            for (int i = 0; i < 6; i++) {
                int nz = cur.z + delta[i][0];
                int nx = cur.x + delta[i][1];
                int ny = cur.y + delta[i][2];
                if (isAvailable(nz, nx, ny) && map[nz][nx][ny] == 0) {
                    T--;
                    map[nz][nx][ny] = 1;
                    q.offer(new Tomato(nz, nx, ny, cur.day + 1));
                    if (T == 0) {
                        System.out.println(cur.day + 1);
                        System.exit(0);
                    }
                }
            }
        }
        System.out.println(-1);
    }

    static boolean isAvailable(int z, int x, int y) {
        return z >= 0 && z < H && x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BOJ7569.solution();
    }
}
