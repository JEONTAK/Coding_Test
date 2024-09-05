package BaekJoon.Gold5.CCW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11758 {

    static int[][] P;

    private static void solution() throws IOException {
        P = new int[3][2];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                P[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = compute();
        if (result == 0) {
            System.out.println(result);
        } else if (result > 0) {
            System.out.println(1);
        }else{
            System.out.println(-1
            );
        }
    }

    static int compute(){
        //(X2 - X1) * (Y3 - Y1) - (X3 - X1) * (Y2 - Y1)
        return (P[1][0] - P[0][0]) * (P[2][1] - P[0][1]) - (P[2][0] - P[0][0]) * (P[1][1] - P[0][1]);
    }

    public static void main(String[] args) throws IOException {
        BOJ11758.solution();
    }
}
