package BaekJoon.Gold5.LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        int aLen = A.length();
        int bLen = B.length();
        int[][] dp = new int[aLen + 1][bLen + 1];
        for (int i = 1; i <= aLen; i++) {
            for (int j = 1; j <= bLen; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[aLen][bLen]);
    }

    public static void main(String[] args) throws IOException {
        BOJ9251.solution();
    }
}

/*
문제 분석
1. 정보
    - LCS : 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것
    
2. 목표
    - 두 문자열의 LCS의 길이를 출력
3. 제약 조건
    - 두 문자열의 길이 : 1 <= 길이 <= 1000
    - 문자열은 알파벳 대문자로만 이루어져 있다.
풀이
1. 알고리즘
    - DP
        - DP[i][j] : 문자열 A의 i번째, 문자열 B의 j번째의 문자일때의 LCS 길이
            - DP[i][0], DP[0][j] : 0
            - 만약 두 문자가 같으면 : DP[i][j] = DP[i - 1][j - 1] + 1
            - 만약 두 문자가 다르면 : DP[i][j] = Math.max(DP[i - 1][j] , DP[i][j - 1])
        - DP[A의 길이][B의 길이]가 결과 값

*/