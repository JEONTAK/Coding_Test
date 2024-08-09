package BaekJoon.Gold5.암호코드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {

    static String word;
    static int[] dp;
    static int MOD = 1000000;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine();
        dp = new int[word.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= word.length(); i++) {
            int cur = word.charAt(i - 1) - '0';
            if (cur >= 1 && cur <= 9) {
                dp[i] += dp[i - 1];
                dp[i] %= MOD;
            }
            if (i == 1) {
                continue;
            }
            int prev = word.charAt(i - 2) - '0';
            if (prev == 0) {
                continue;
            }
            int v = prev * 10 + cur;
            if(v >= 10 && v <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %= MOD;
            }
        }
        System.out.println(dp[word.length()]);
    }

    public static void main(String[] args) throws IOException {
        BOJ2011.solution();
    }
}
