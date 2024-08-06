package BaekJoon.Gold5.동전2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2294 {

    static int N, K;
    static int[] dp, coin;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coin = new int[N];
        dp = new int[K + 1];
        Arrays.fill(dp, 20000);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j < N; j++) {
                if (i + coin[j] <= K) {
                    dp[i + coin[j]] = Math.min(dp[i + coin[j]], dp[i] + 1);
                }
            }
        }
        if(dp[K] == 20000) {
            System.out.println(-1);
        }else{
            System.out.println(dp[K]);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ2294.solution();
    }
}
