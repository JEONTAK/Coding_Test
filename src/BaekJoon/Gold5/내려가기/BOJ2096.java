package BaekJoon.Gold5.내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2096 {

    static int N;
    static int[][] map, dp;
    static int max = 0, min = Integer.MAX_VALUE;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 3; i++) {
            dp[0][i] = map[0][i];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]) + map[i][0];
            dp[i][1] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2])) + map[i][1];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]) + map[i][2];
        }
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dp[N - 1][i]);
        }
        dp = new int[N ][3];
        for (int i = 0; i < 3; i++) {
            dp[0][i] = map[0][i];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + map[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2])) + map[i][1];
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + map[i][2];
        }

        for (int i = 0; i < 3; i++) {
            min = Math.min(min, dp[N - 1][i]);
        }
        System.out.println(max + " " + min);
    }

    public static void main(String[] args) throws IOException {
        BOJ2096.solution();
    }
}
