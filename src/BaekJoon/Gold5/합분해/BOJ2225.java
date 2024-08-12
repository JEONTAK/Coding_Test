package BaekJoon.Gold5.합분해;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2225 {

    static int N, K;
    static int[][] dp;
    static final int MOD = 1_000_000_000;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1][N + 1];
        Arrays.fill(dp[1], 1);
        for (int i = 1; i <= K; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= N; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        System.out.println(dp[K][N]);
    }

    public static void main(String[] args) throws IOException {
        BOJ2225.solution();
    }
}
