package BaekJoon.Gold5.카드게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10835 {

    static int N;
    static int[][] card;
    static int[][] dp;
    static int max;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        card = new int[2][N];
        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                card[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }
        dp[0][0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]);
                dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
                if (card[0][i] > card[1][j]) {
                    dp[i][j + 1] = Math.max(dp[i][j] + card[1][j], dp[i][j + 1]);
                }
            }
        }
        for (int i = 0; i <= N; i++) {
            max = Math.max(max, Math.max(dp[N][i], dp[i][N]));
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        BOJ10835.solution();
    }
}
