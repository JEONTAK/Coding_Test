package BaekJoon.Silver3.피보나치함수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1003 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[][] DP = new int[41][2];

        DP[0][0] = 1;
        DP[0][1] = 0;
        DP[1][0] = 0;
        DP[1][1] = 1;
        for (int i = 2; i <= 40; i++) {
            DP[i][0] = DP[i - 1][0] + DP[i - 2][0];
            DP[i][1] = DP[i - 1][1] + DP[i - 2][1];
        }

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(DP[N][0]).append(" ").append(DP[N][1]).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ1003.solution();
    }
}

/*
문제 분석
1. 정보
    - 피보나치 수를 구하는 함수 : fibo(n) = fibo(n - 1) + fibo(n - 2);
    - 해당 과정을 하다보면, 0 또는 1로 귀결
    
2. 목표
    - N을 호출 했을 때, 0과 1이 각각 몇번 출력되는지 출력

3. 제약 조건
    - N : 0 <= N <= 40

풀이
1. 알고리즘
    - DP 사용
        - DP[i][0] : 숫자가 i일때 0이 나온 횟수
        - DP[i][1] : 숫자가 i일때 1이 나온 횟수
    - fibo(N) = fibo(N - 1) + fibo(N - 2);
        - DP[i][0] = DP[i - 1][0] + DP[i - 2][0];
        - DP[i][1] = DP[i - 1][1] + DP[i - 2][1];
    - i : 0 ~ 40까지(최대 40이기 때문, 테스트 케이스의 개수가 주어지기 때문에 한번에 구하고 출력만 해주면 수월)

 */
