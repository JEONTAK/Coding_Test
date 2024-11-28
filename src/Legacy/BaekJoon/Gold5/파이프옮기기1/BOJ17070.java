package BaekJoon.Gold5.파이프옮기기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {

    static int N;
    static int[][] map;
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 1, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = compute(0, 1, 0);
        System.out.println(result);
    }

    static int compute(int x, int y, int d) {
        int sum = 0;
        if (x == N - 1 && y == N - 1) {
            return 1;
        }

        for (int i = 0; i < 3; i++) {
            if(d == 0 && i == 2) continue;
            if(d == 2 && i == 0) continue;
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isAvailable(nx, ny, i)) {
                sum += compute(nx, ny, i);
            }
        }
        return sum;
    }

    static boolean isAvailable(int x, int y, int d) {
        if (d == 1) {
            if(x >= 0 && x < N && y >= 0 && y < N){
                return map[x][y] == 0 && map[x - 1][y] == 0 && map[x][y - 1] == 0;
            }
        }else{
            if(x >= 0 && x < N && y >= 0 && y < N){
                return map[x][y] == 0;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BOJ17070.solution();
    }
}
