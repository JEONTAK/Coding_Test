package BaekJoon.Gold4.동전1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293 {
    
    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dp = new int[K + 1];
        int[] coin = new int[N];
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = coin[i]; j <= K; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }
        System.out.println(dp[K]);
    }
    
    public static void main(String[] args) throws IOException {
        BOJ2293.solution();
    }
}

/*
문제 분석
1. 정보
    - N가지 종류의 동전
    - 동전을 적당히 사용해 가치 합이 K원이 되도록 하고 싶음
    - 각 동전은 몇 개라도 사용이 가능함
2. 목표
    - 가치 합이 K원이 되도록 하는 경우의 수를 출력

3. 제약 조건
    - N : 1 <= N <= 100
    - K : 1 <= K <= 10000
    - 동전의 가치 V : 1 <= V <= 100000
    - 경우의 수 <= 2^{31}
풀이
1. 알고리즘
    - DP 알고리즘
        - DP[i] : 동전의 가치가 i일 때, 경우의 수
        - DP[0] : 1로 초기화 -> 0원을 만들 수 있는 경우의 수 = 1
        - DP[i] : DP[i] + DP[i - 동전의 가치];
            - 동전의 종류 i : 0 ~ N 까지
            - DP 탐색 범위 : coin[i] ~ K 까지 coin[i]보다 작은 곳은 도달 불가능 하기 때문
                - 즉, DP[j] += DP[j - coin[i];
 */
