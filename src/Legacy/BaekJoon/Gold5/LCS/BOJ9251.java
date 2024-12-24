package Legacy.BaekJoon.Gold5.LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251 {

    static String s1, s2;
    static int l1, l2;
    static int[][] dp;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine();
        s2 = br.readLine();
        l1 = s1.length();
        l2 = s2.length();
        dp = new int[l1 + 1][l2 + 1];
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[l1][l2]);
    }

    public static void main(String[] args) throws IOException {
        BOJ9251.solution();
    }
}
