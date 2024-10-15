package BaekJoon.Gold4.스도쿠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2580 {

    static int[][] map = new int[9][9];

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        compute(0, 0);
    }

    static void compute(int row, int col) {
        if (col == 9) {
            compute(row + 1, 0);
            return;
        }

        if (row == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
            System.exit(0);
        }

        if(map[row][col] == 0){
            for (int i = 1; i <= 9; i++) {
                if (isAvailable(row, col, i)) {
                    map[row][col] = i;
                    compute(row, col + 1);
                }
            }
            map[row][col] = 0;
            return;
        }
        compute(row, col + 1);
    }

    static boolean isAvailable(int row, int col, int val) {
        for (int i = 0; i < 9; i++) {
            if (map[row][i] == val) {
                return false;
            }
            if (map[i][col] == val) {
                return false;
            }
        }
        for(int i = (row / 3) * 3 ; i < (row / 3) * 3 + 3 ; i++){
            for(int j = (col / 3) * 3 ; j < (col / 3) * 3 + 3 ; j++){
                if(map[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BOJ2580.solution();
        
    }
}
