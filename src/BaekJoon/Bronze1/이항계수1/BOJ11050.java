package BaekJoon.Bronze1.이항계수1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11050 {

    static int[][] dp;

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

        return dp[n][k] = pascalRule(n - 1, k - 1) + pascalRule(n - 1, k);
    }

    public static void main(String[] args) throws IOException {
        BOJ11050.solution();
    }
}

/*
문제 분석
1. 정보
    - 자연수 N과 정수 K가 주어졌을 때 이항계수 (N,K)를 구하는 프로그램을 작성
2. 목표
    - 자연수 N과 정수 K가 주어졌을 때 이항계수 (N,K)를 구하여 출력
3. 제약 조건
    - 1 <= N <= 10
    - 0 <= K <= N

풀이
1. 아이디어
    - 파스칼의 법칙과 nC0 = nCn = 1을 이용하여 풀이
    - 또한 메모이제이션을 위한 dp 배열을 선언하여 이미 구한 값일 경우, 해당 값을 사용
    - 재귀 알고리즘을 통해 파스칼의 법칙 수행
*/
