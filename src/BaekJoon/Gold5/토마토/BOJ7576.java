package BaekJoon.Gold5.토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ7576 {

    static class Tomato implements Comparable<Tomato>{
        int x;
        int y;
        int t;

        public Tomato(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }

        @Override
        public int compareTo(Tomato o) {
            return this.t - o.t;
        }
    }

    static int N, M;
    static int[][] map;
    static int RT = 0;
    static PriorityQueue<Tomato> pq = new PriorityQueue<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    RT++;
                }else if(map[i][j] == 1){
                    pq.add(new Tomato(i, j, 0));
                }
            }
        }
        if (RT == 0) {
            System.out.println(0);
            System.exit(0);
        }
        while(!pq.isEmpty()){
            Tomato cur = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isAvailable(nx, ny) && map[nx][ny] == 0) {
                    RT--;
                    map[nx][ny] = 1;
                    pq.offer(new Tomato(nx, ny, cur.t + 1));
                    if (RT == 0) {
                        System.out.println(cur.t + 1);
                        System.exit(0);
                    }
                }
            }
        }
        System.out.println(-1);
    }

    static boolean isAvailable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
    public static void main(String[] args) throws IOException {
        BOJ7576.solution();
    }
}
