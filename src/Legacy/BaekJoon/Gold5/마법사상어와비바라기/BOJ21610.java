package BaekJoon.Gold5.마법사상어와비바라기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21610 {

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, d, s, result = 0;
    static int[][] map;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static Queue<Node> q = new LinkedList<>();
    static Queue<Node> move = new LinkedList<>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        q.add(new Node(N - 1, 0));
        q.add(new Node(N - 1, 1));
        q.add(new Node(N - 2, 0));
        q.add(new Node(N - 2, 1));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            compute(d, s);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += map[i][j];
            }
        }
        System.out.println(result);
    }

    static void compute(int d,int s) {
        boolean[][] visited = new boolean[N][N];
        while(!q.isEmpty()) {
            Node cur = q.poll();
            int nx = cur.x, ny = cur.y;
            for (int i = 0; i < s; i++) {
                nx = (nx + dx[d] + N) % N;
                ny = (ny + dy[d] + N) % N;
            }
            map[nx][ny]++;
            move.add(new Node(nx, ny));
        }
        while(!move.isEmpty()) {
            Node cur = move.poll();
            visited[cur.x][cur.y] = true;
            int sum = 0;
            for (int i = 2; i <= 8; i += 2) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isAvailable(nx, ny) && map[nx][ny] > 0) {
                    sum++;
                }
            }
            map[cur.x][cur.y] += sum;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2 && !visited[i][j]) {
                    q.add(new Node(i, j));
                    map[i][j] -= 2;
                }
            }
        }
    }

    static boolean isAvailable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ21610.solution();
    }
}
