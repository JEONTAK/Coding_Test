package BaekJoon.Gold3.앱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7579 {

    static int N, M, maxCost = 0;
    static int[][] app;
    static int[] dp;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        app = new int[N][2];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            app[i][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            app[i][1] = Integer.parseInt(st.nextToken());
            maxCost += app[i][1];
        }

        dp = new int[maxCost + 1];

        for (int i = 0; i < N; i++) {
            for (int j = maxCost; j >= app[i][1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - app[i][1]] + app[i][0]);
            }
        }

        for (int i = 0; i <= maxCost; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ7579.solution();
    }
}

/*
문제 분석
1. 정보
    - N개의 앱이 활성화 되어있음 (A_1, ..., A_N)
        - 각 앱 A_i
            - m_i : 메모리 사용량
            - c_i : 비활성화 이후 다시 실행할 시 추가적인 비용
    - 새로운 앱 B를 실행하고자 하기 위해 필요한 메모리의 양 : M
        - 즉 A_1, ... ,A_N 중에서 몇 개를 비활성화 하여 M 바이트 이상의 메모리를 확보 해야함
        - 이 때의 비용 c_i의 합을 최소화 해야함.
2. 목표
    - 필요한 메모리 M 바이트를 확보하기 위한 앱 비활성화의 최소의 비용

3. 제약 조건
    - 1 <= N <= 100
    - 1 <= M <= 10,000,000
    - 1 <= m_1, ..., m_N <= 10,000,000
    - 1 <= c_1, ..., c_N <= 100
    - M <= m_1 + m_2 + ... + m_N

풀이
1. 알고리즘
    - DP
        - DP[i] : 비용이 i일때 확보할수 있는 최대 메모리
        - 0 ~ N까지의 앱(i)
            - 전체 cost 총합 ~ c_i 까지(j)
            - DP[j] = DP[j - c_i] + m_i의 값들 중 최대값
            - 비용 j일때의 최대 메모리 = (j - 현재 App의 비용)일때의 최대 메모리 + 현재 앱의 메모리 의 최대 값

    - 이후 해당 DP 배열 0부터 maxCost까지
        - 만약 DP[i] >= M -> i의 비용으로 메모리 확보 가능 -> i가 정답
 */
