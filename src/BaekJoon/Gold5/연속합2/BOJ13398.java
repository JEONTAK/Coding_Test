package BaekJoon.Gold5.연속합2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13398 {

    static int N;
    static int[] seq;
    static int[][] dp;
    static int max = Integer.MIN_VALUE;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seq = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N + 1][2];
        dp[1][0] = seq[1];
        dp[1][1] = seq[1];
        for (int i = 2; i <= N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + seq[i], seq[i]);
            dp[i][1] = Math.max(dp[i - 2][0] + seq[i], dp[i - 1][1] + seq[i]);
        }
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dp[i][1]);
            max = Math.max(max, dp[i][0]);
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        BOJ13398.solution();
    }
}
