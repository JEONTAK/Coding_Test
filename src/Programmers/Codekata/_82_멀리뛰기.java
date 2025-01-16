package Programmers.Codekata;

public class _82_멀리뛰기 {

    class Solution {
        public long solution(int n) {
            if (n == 1) {
                return 1;
            }
            long dp[] = new long[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
            }
            return dp[n];
        }
    }

}

/*
문제 분석
1. 정보
    - 효진이는 한번에 1칸 또는 2칸을 뛸 수 있음.
        - 예를 들어, 칸이 4개 있을 경우
            - 1,1,1,1
            - 1,2,1
            - 1,1,2
            - 2,1,1
            - 2,2
            - 총 5가지의 방법으로 도달 가능

2. 목표
    칸의 수 N이 주어질 때, 끝에 도달하는 방법의 수를 1234567로 나눈 나머지를 return

3. 제약 조건
    - 1 <= N <= 2000

풀이
1. 아이디어
    - DP 사용
        - dp[i] : i번째 칸에 도착하는 경우의 수
        - dp[i] = dp[i - 1] + dp[i - 2] 임
        - 마지막에 dp[N]을 출력

*/