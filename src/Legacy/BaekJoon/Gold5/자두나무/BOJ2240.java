package BaekJoon.Gold5.자두나무;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2240 {

    static int T, W;
    static int[] tree;
    static int[][][] dp;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        tree = new int[T  + 1];
        for (int i = 1; i <= T; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[T + 1][W + 1][3];

        if (tree[1] == 1) {
            dp[1][0][1] = 1;
            dp[1][1][2] = 0;
        }else if(tree[1] == 2) {
            dp[1][0][1] = 0;
            dp[1][1][2] = 1;
        }

        for (int i = 2; i <= T; i++) {
            if (tree[i] == 1) {
                dp[i][0][1] = dp[i - 1][0][1] + 1;
                dp[i][0][2] = dp[i - 1][0][2];

                for (int j = 1; j <= W; j++) {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]) + 1;
                    dp[i][j][2] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j][2]);
                }
            }else{
                dp[i][0][1] = dp[i - 1][0][1];
                dp[i][0][2] = dp[i - 1][0][2] + 1;

                for (int j = 1; j <= W; j++) {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]);
                    dp[i][j][2] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j][2]) + 1;
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i <= W; i++) {
            result = Math.max(result, Math.max(dp[T][i][1], dp[T][i][2]));
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ2240.solution();
    }
}
