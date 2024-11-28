package BaekJoon.Gold4.알파벳;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {

    static int R, C;
    static char[][] map;
    static int max = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        boolean[] alphabet = new boolean[26];
        visited[0][0] = true;
        alphabet[map[0][0] - 'A'] = true;
        compute(0, 0, 1, alphabet, visited);
        System.out.println(max);
    }

    static void compute(int x, int y, int t, boolean[] alphabet, boolean[][] visited){
        max = Math.max(max, t);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isAvailable(nx, ny) && !visited[nx][ny] && !alphabet[map[nx][ny] - 'A']) {
                visited[nx][ny] = true;
                alphabet[map[nx][ny] - 'A'] = true;
                compute(nx, ny, t + 1, alphabet, visited);
                visited[nx][ny] = false;
                alphabet[map[nx][ny] - 'A'] = false;
            }
        }
    }

    static boolean isAvailable(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) throws IOException {
        BOJ1987.solution();
    }
}
