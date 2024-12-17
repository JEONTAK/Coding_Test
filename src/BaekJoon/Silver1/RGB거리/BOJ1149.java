package BaekJoon.Silver1.RGB거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            house[i][0] = Integer.parseInt(st.nextToken());
            house[i][1] = Integer.parseInt(st.nextToken());
            house[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] DP = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                DP[i][j] = Math.min(DP[i - 1][(j + 1) % 3], DP[i - 1][(j + 2) % 3]) + house[i][j];
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            result = Math.min(result, DP[N][i]);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ1149.solution();
    }
}
/*
문제 분석
1. 정보
    - RGB 거리에는 N개의 집 존재
    - 집을 빨강, 초록, 파랑 중 하나의 색으로 칠해야 함.
    - 다음과 같은 규칙 존재
        - 1번 집의 색은 2번 집의 색과 같지 않아야 함.
        - N번 집의 색은 N - 1번 집의 색과 같지 않아야 함.
        - i(2 <= i <= N - 1)번 집의 색은 i - 1, i + 1 번 집의 색과 같지 않아야 함.
    - 즉, 이웃한 집과 색이 달라야 한다.
    - 또한, 빨강, 초록, 파랑으로 칠하는 비용이 각각 주어짐.

2. 목표
    - 모든 집을 칠하는 비용의 최솟값 출력

3. 제약 조건
    - N : 2 <= N <= 1000
    - 집을 칠하는 비용 : 1 <= 비용 <= 1000

풀이
1. 알고리즘
    - DP 사용 DP[N][3]
        - dp[i][j] : i번째 집을 j색으로 칠할때의 비용의 최솟값
            - dp[1][0] : 1번째 집을 빨간색으로 칠했을 때
            - dp[1][1] : 1번째 집을 초록색으로 칠했을 때
            - dp[1][2] : 1번째 집을 파란색으로 칠했을 때
        - dp[2][0] = Math.min(dp[1][1], dp[1][2]) + 0번째 색으로 2번집을 채우는데 필요한 비용
        - 즉, dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + house[i][j];

2. 탐색 과정
    - 집을 빨강, 초록, 파랑으로 색칠하는데 필요한 비용을 담을 배열 house[N + 1][3] 생성 및 해당 배열에 값 저장
    - DP[N + 1][3] 생성 후 위 알고리즘 규칙을 활용해 DP 알고리즘 수행
    - 이후 모든 집을 칠하는 비용의 최솟값, 즉 DP[N][0], DP[N][1], DP[N][2] 값 중 최솟값 구하여 출력
 */
