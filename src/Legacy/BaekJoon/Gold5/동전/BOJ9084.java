package BaekJoon.Gold5.동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9084 {

    static int T, N, M;
    static int[] coin;
    static long[] dp;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int a = 0; a < T; a++) {
            N = Integer.parseInt(br.readLine());
            coin = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());
            dp = new long[M + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (j - coin[i] > 0) {
                        dp[j] += dp[j - coin[i]];
                    }else if(j - coin[i] == 0){
                        dp[j]++;
                    }
                }
            }
            System.out.println(dp[M]);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ9084.solution();
    }
}
