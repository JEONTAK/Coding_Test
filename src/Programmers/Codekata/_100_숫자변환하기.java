package Programmers.Codekata;

import java.util.Arrays;

public class _100_숫자변환하기 {

    class Solution {
        public int solution(int x, int y, int n) {
            int[] dp = new int[y + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[x] = 0;
            for (int i = x; i < y; i++) {
                if (dp[i] == Integer.MAX_VALUE) {
                    continue;
                }

                if (i + n <= y) {
                    dp[i + n] = Math.min(dp[i] + 1, dp[i + n]);
                }

                if (i * 2 <= y) {
                    dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2]);
                }

                if (i * 3 <= y) {
                    dp[i * 3] = Math.min(dp[i] + 1, dp[i * 3]);
                }
            }
            return dp[y] == Integer.MAX_VALUE ? -1 : dp[y];
        }
    }

}

/*
문제 분석
1. 정보
    - 자연수 x를 y로 변환하려고 한다. 사용할 수 있는 연산은 다음과 같다.
    - x에 n을 더한다
    - x에 2를 곱한다
    - x에 3을 곱한다

2. 목표
    - 자연수 x,y,n이 주어질 때, x를 y로 변환하기 위해 필요한 최소 연산 횟수를 return
3. 제약 조건
    - 1 <= x <= y <= 1000000
    - 1 <= n < y

풀이
1. 아이디어
    - BFS 사용
        - dp[i] : i번째 수를 만드는데 필요한 최소 연산 횟수
        - i : x ~ y - n까지
            - 만약 dp[i] = Integer.MAX_VALUE, continue;
            - 아니라면
                - 만약 i + n이 y보다 작다면
                    - dp[i + n] = Math.min(dp[i] + 1, dp[i + n])
                - 만약 i * 2가 y보다 작다면
                    - dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2])
                - 만약 i * 3가 y보다 작다면
                    - dp[i * 3] = Math.min(dp[i] + 1, dp[i * 3])
         - dp[y]를 return
 */

