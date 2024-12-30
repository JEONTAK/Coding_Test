package BaekJoon.Gold3.행렬곱셈순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11049 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][2];
        long[][] dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int len = 1; len < N; len++) {
            for (int i = 0; i < N - len; i++) {
                int j = i + len;

                dp[i][j] = Long.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    long cost = dp[i][k] + dp[k + 1][j] + ((long) matrix[i][0] * matrix[k][1] * matrix[j][1]);
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        System.out.println(dp[0][N - 1]);
    }

    public static void main(String[] args) throws IOException {
        BOJ11049.solution();
    }
}

/*
문제 분석
1. 정보
    - 크기가 N X M인 행렬 A와 M X K인 B를 곱할 때 필요한 곱셈 연산의 수는 총 N X M X K번
    - 같은 곱셈일지라도 곱셈 순서에 따라 곱셈 연산의 수가 달라짐

2. 목표
    - N개의 행렬이 주어졌을 때, 모든 행렬을 곱하는데 필요한 곱셈 연산 횟수의 최솟값 출력

3. 제약 조건
    - 행렬의 개수 N : 1 <= N <= 500
    - 행렬의 크기 r, c : 1 <= r, c <= 500
    - 정답 <= 2^{31} - 1
    - 연산 횟수 <= 2^{31} - 1

풀이
1. 알고리즘
    - 최대 2^{31} - 1이므로 long 타입 사용
    - DP 알고리즘
        - DP[i][j] : i번째 행렬 부터 j번째 행렬까지 곱연산 할때의 최소 비용
            - DP[i][j] = DP[i][k] + DP[k + 1][j] + (matrix[i][0] * matrix[k][1] * matrix[j][1]) 값들 중 최솟값
            - 길이 len : 1 ~ N - 1까지
                - i : 0 ~ N - len 까지
                - j : i + len
                - k : i ~ j
        - 마지막에 DP[0][N - 1] 출력
*/
