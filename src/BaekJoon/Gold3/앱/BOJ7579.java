package BaekJoon.Gold3.앱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7579 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] app = new int[N][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            app[i][0] = Integer.parseInt(st.nextToken());
        }

        int cost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            app[i][1] = Integer.parseInt(st.nextToken());
            cost += app[i][1];
        }

        int[] dp = new int[cost + 1];

        for (int i = 0; i < N; i++) {
            for (int j = cost; j >= app[i][1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - app[i][1]] + app[i][0]);
            }
        }

        for (int i = 0; i <= cost; i++) {
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
    - N개의 앱, A_1, ..., A_N 이 활성화 되어 있다고 가정
    - 앱 A_i 는 각각 m_i 바이트 만큼의 메모리를 사용중
    - 앱 A_i 를 비활성화 한 후에 다시 실행하고자 할 경우, 추가 비용이 c_i 만큼 들음
    - M 바이트의 추가 메모리가 필요함

2. 목표
    - 필요한 메모리 M 바이트를 확보하기 위한 앱 비활성화의 최소 비용을 출력
3. 제약 조건
     - N : 1 <= N <= 100
     - M : 1 <= M <= 10_000_000
     - 1 <= m_1, ..., m_N <= 10_000_000
     - 0 <= c_1, ..., c_N <= 100
     - M <= m_1 + m_2 + ... + m_N

풀이
1. 알고리즘
    - app[N][2] : app[N][0] : 메모리 / app[N][1] : 비용
    - DP 알고리즘
        - DP[i] : i 비용을 썼을 때, 비 활성화 할 수 있는 메모리의 최댓값
        - DP 범위 : 0 ~ 비용의 합(cost)
            - 앱 i : 0 ~ N
            - 비용 j : cost ~ app[i]의 비용까지
                - DP[j] : j 비용을 썼을 때의 최대 메모리 값, app[i]를 비활성화를 하여 j 비용이 되었을 때의 최대 메모리값을 비교하여 최댓값 저장
                - DP[j] = Math.max(DP[j], DP[j - app[i][1] + app[i][0]);
*/
