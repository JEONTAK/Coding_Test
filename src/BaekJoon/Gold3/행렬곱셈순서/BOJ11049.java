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
        long[][] DP = new long[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        //len : 행렬의 곱 연산의 길이 (1 ~ N - 1)
        for (int len = 1; len < N; len++) {
            //i : (0 ~ N - 1) 시작, (0 ~ 1) 끝
            for (int i = 0; i < N - len; i++) {
                int j = i + len;//범위 끝
                DP[i][j] = Long.MAX_VALUE;

                //행렬 i ~ 행렬 j 까지의 곱의 연산의 최소 연산 개수 구하기
                //k를 통해 i ~ k, k + 1 ~ j의 연산으로 나누어 최솟값 구하기
                for (int k = i; k < j; k++) {
                    long cost = DP[i][k] + DP[k + 1][j] + (long) matrix[i][0] * matrix[k][1] * matrix[j][1];
                    DP[i][j] = Math.min(DP[i][j], cost);
                }
            }
        }

        System.out.println(DP[0][N - 1]);
    }

    public static void main(String[] args) throws IOException {
        BOJ11049.solution();
    }
}
/*
문제 분석
1. 정보
    - 크기가 N * M인 행렬 A
    - 크기가 M * K인 행렬 B
    - 두 행렬을 곱할떄 필요한 곱셈 연산의 수는 총 N * M * K번
    - 행렬을 곱하는 순서에 따라 곱셈 연산의 수가 달라짐
        - (AB)C -> A * B + (AB) * C
        - A(BC) -> B * C + (BC) * A

2. 목표
    - 행렬 N개의 크기가 주어졌을 떄, 모든 행렬을 곱하는데 필요한 곱셈 연산 횟수의 최솟값 구하기

3. 제약 조건
    - 행렬의 개수 (1 <= N <= 500)
    - 행렬의 크기 r, c (1 <= r, c <= 500)
    - 항상 순서대로 곱셈을 할 수 있는 크기만 입력으로 주어짐
    - 정답은 2^31 - 1보다 작거나 같은 자연수
    - 최악의 연산의 경우에도 2^31 - 1보다 작거나 같음

풀이
1. 알고리즘
    - DP 사용
        - dp[i][j] : 행렬 i 부터 행렬 j까지의 곱 연산의 최소 연산 개수 값
        - dp[i][j] = i <= k < j일 떄,  (dp[i][k] + dp[k + 1][j] + ri * ck * cj)값들 중 최솟값
            - dp[i][k] : 행렬 i ~  행렬 k 곱의 최소 연산 개수
            - dp[k + 1][j] : 행렬 k + 1 ~ 행렬 j 곱 의 최소 연산 개수
            - 위 두 행렬을 곱할떄, 곱의 연산 개수 : ri * ck * cj
            - 위 3개를 모두 더하면 k를 기준으로 양쪽 곱의 연산을 한 후, 두 행렬을 곱했을 때 총 곱의 연산 개수가 나오게 됨
            - 즉, k를 i 부터 j - 1 까지 바꿔가며 계산한 곱의 연산 개수의 총합의 최솟값을 구하면 해당 값이 dp[i][j]의 최솟값이 됨.
        - dp[1][N]의 값을 출력 하면 됨

2. 풀이 과정
    - 곱연산의 길이 (1 ~ N - 1)
        - 해당 길이 만큼의 범위 구함 (i, j 선언)
            - i ~ j 까지의 곱 연산을 k를 통해 중간에 나누어 곱 연산함
                - 해당 값들 중 최솟값을 DP[i][j] 배열에 저장

    - 계산 후, 첫번째 행렬(0) 부터 마지막 행렬(N - 1) 까지의 결과 값을 출력
        - DP[0][N - 1] 값 출력
 */