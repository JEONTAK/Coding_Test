package BaekJoon.Gold1.이항계수3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11401 {

    static int[][] dp;
    static final int p = 1_000_000_007;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][K + 1];

        System.out.println(pascalRule(N, K));
    }

    private static int pascalRule(int n, int k) {
        if (dp[n][k] > 0) {
            return dp[n][k];
        }

        if (n == k || k == 0) {
            return dp[n][k] = 1;
        }

        return dp[n][k] = (pascalRule(n - 1, k - 1) % p + pascalRule(n - 1, k) % p) % p;
    }

    public static void main(String[] args) throws IOException {
        BOJ11401.solution();
    }
}
/*
문제 분석
1. 정보
    - 자연수 N과 정수 K가 주어졌을 때, 이항 계수 C(N,K)를 1,000,000,007로 나눈 나머지를 구하여라.
2. 목표
    - 자연수 N과 정수 K가 주어졌을 때, 이항 계수 C(N,K)를 1,000,000,007로 나눈 나머지를 구하여라.
3. 제약 조건
    - 1 <= N <= 4,000,000
    - 0 <= K <= N

풀이
    - 먼저 이항계수를 잘 몰라 찾아보았다.
    - 간단하게 생각하면 고등학교 수학시간에 배운 조합 -> nCk를 생각하면 편하다는 것을 알았다.
    - 즉 N, K가 주어졌을때, C(N,K) = N! / (k! * (n - k)!) 이다.
    - 해당 문제에서는 N이 4,000,000이기 때문에 단순 계산을 하면 반드시 초과 오류가 발생할 것이라 생각했다.

1. 아이디어
    - 이항 계수 2의 내용에 추가로
    - 10007로 나누던 것을 1000000007로 나누면 될 것이다.
*/
