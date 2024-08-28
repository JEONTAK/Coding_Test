package BaekJoon.Gold5._1학년;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5557 {

    static int N, M;
    static int[] seq;
    static long[][] dp;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N - 1][21];
        seq = new int[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(st.nextToken());
        dp[0][seq[0]] = 1;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < 21; j++) {
                if (dp[i][j] != 0) {
                    int plus = j + seq[i + 1];
                    int minus = j - seq[i + 1];
                    if (plus >= 0 && plus <= 20) {
                        dp[i + 1][plus] += dp[i][j];
                    }
                    if(minus >= 0 && minus <= 20) {
                        dp[i + 1][minus] += dp[i][j];
                    }
                }
            }
        }
        System.out.println(dp[N - 2][M]);
    }

    public static void main(String[] args) throws IOException {
        BOJ5557.solution();
    }
}
