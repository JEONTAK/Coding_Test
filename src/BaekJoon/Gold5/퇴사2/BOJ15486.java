package BaekJoon.Gold5.퇴사2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15486 {

    static int N;
    static int[][] TP;
    static int[] dp;
    static int result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        TP = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            TP[i][0] = Integer.parseInt(st.nextToken());
            TP[i][1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N + 2];
        int max = -1;
        for (int i = 1; i <= N ; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
            if (i + TP[i][0] <= N + 1) {
                dp[i + TP[i][0]] = Math.max(dp[i + TP[i][0]], max + TP[i][1]);
            }
        }
        for (int i = 0; i <= N + 1; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ15486.solution();
    }
}
