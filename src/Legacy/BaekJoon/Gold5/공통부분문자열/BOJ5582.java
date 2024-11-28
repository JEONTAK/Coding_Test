package BaekJoon.Gold5.공통부분문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5582 {

    static String str1, str2;
    static int s1, s2;
    static int[][] dp;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        s1 = str1.length();
        s2 = str2.length();
        dp = new int[s1 + 1][s2 + 1];
        int max = 0;
        for (int i = 1; i <= s1; i++) {
            for (int j = 1; j <= s2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        BOJ5582.solution();
    }
}
