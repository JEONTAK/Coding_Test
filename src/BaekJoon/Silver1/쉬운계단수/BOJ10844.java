package BaekJoon.Silver1.쉬운계단수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10844 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MOD = 1_000_000_000;
        int N = Integer.parseInt(br.readLine());
        int[][] DP = new int[N + 1][10];
        for (int i = 1; i <= 9; i++) {
            DP[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            DP[i][0] += (DP[i - 1][1] % MOD);
            for (int j = 1; j <= 8; j++) {
                DP[i][j] += (DP[i - 1][j - 1] + DP[i - 1][j + 1]) % MOD;
            }
            DP[i][9] += (DP[i - 1][8] % MOD);
        }

        int result = 0;
        for (int i = 0; i <= 9; i++) {
            result = (result + DP[N][i]) % MOD;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ10844.solution();
    }
}

/*
문제 분석
1. 정보
    - 45656과 같이, 인접한 모든 자리의 차이가 1인 수를 계단 수라고 함.

2. 목표
    - N이 주어질 때, 길이가 N인 계단 수가 총 몇개 있는지 구한다.

3. 제약 조건
    - N : 1 <= N <= 100
    - 정답을 10^9로 나눈 나머지를 출력함.
풀이
1. 알고리즘
    - DP
        - DP[i][j] : i번째 자리에 j가 들어갈 경우의 수

2. 탐색 과정
    - DP
        - DP[i][j] : i번째 자리에 j가 들어갈 경우의 수
            - i : 1 ~ N 까지 반복
            - j : 0 ~ 9 까지 반복
                - 이때, 첫번째 자리에는 0이 들어와서는 안됨.
                - 0이 들어갈 경우는 이전 자리의 숫자가 1인 경우
                - 1 ~ 8이 들어갈 경우는 이전 자리의 숫자가 +-1 인경우
                - 9가 들어갈 경우는 이전 자리의 숫자가 8인 경우
            - 따라서 DP[i][j] :
                - 0 : += DP[i - 1][j + 1];
                - 1 ~ 8 : += DP[i - 1][j - 1] + DP[i - 1][j + 1];
                - 9 : += DP[i - 1][j - 1];
        - 모든 DP를 계산 한 후, DP[N][0] ~ DP[N][9] 까지 경우의 수를 모두 더해주면 결과 값이 나옴

3. 주의 할 점
    - 10^9로 나눈 나머지를 출력 해야 함.
 */
