package Programmers.Codekata;

public class _78_피보나치수 {

    class Solution {
        public int solution(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
            }
            return dp[n];
        }
    }

}

/*
문제 분석
1. 정보
    - 피보나치 수는 F(0) = 0, F(1) = 1일때, 1이상의 N에 대하여 F(N) = F(N - 1) + F(N - 2)가 적용되는 수

2. 목표
    - 2이상의 N이 입럭되었을 때, N번째 피보나치 수를 1234567로 나눈 나머지를 return
3. 제약 조건
    - 2 <= N <= 100,000


풀이
1. 아이디어
    - dp 배열 사용
        - dp[N + 1]을 생성해 해당 숫자의 피보나치 수 값을 구함
        - dp[n]은 (dp[n - 1] + dp[n - 2]) % 1234567 가 됨
*/
