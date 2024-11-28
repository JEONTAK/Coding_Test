package BaekJoon.Gold5.감시피하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ18428 {

    static int N;
    static char[][] map;
    static boolean[][] check;
    static String result;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().replace(" ", "").toCharArray();
        }
        check = new boolean[N][N];
        compute(0);
        System.out.println(result);
    }

    static void compute(int cnt) {
        if(cnt == 3){
            result = getResult();
            if (result.equals("YES")) {
                System.out.println(result);
                System.exit(0);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!check[i][j]) {
                    if (map[i][j] == 'X') {
                        check[i][j] = true;
                        map[i][j] = 'O';
                        compute(cnt + 1);
                        map[i][j] = 'X';
                        check[i][j] = false;
                    }
                }
            }
        }
    }

    static String getResult() {
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'S') {
                    flag = meetTeacher(i, j);
                }
                if(!flag) return "NO";
            }
        }
        return "YES";
    }

    static boolean meetTeacher(int x, int y){
        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            while (true) {
                nx += dx[i];
                ny += dy[i];
                if (!isAvailable(nx, ny) || map[nx][ny] == 'O') break;
                if (map[nx][ny] == 'T') {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }


    public static void main(String[] args) throws IOException {
        BOJ18428.solution();
    }
}
