package BaekJoon.Gold5.동전1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293 {

    static int N, K;
    static int[] dp, coin;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coin = new int[N];
        dp = new int[K + 1];
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = coin[i]; j <= K; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }

        System.out.println(dp[K]);
    }

    public static void main(String[] args) throws IOException {
        BOJ2293.solution();
    }
}
