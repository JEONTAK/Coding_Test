package Programmers.Codekata;

public class _152_가장긴팰린드롬 {

    class Solution {
        public int solution(String s) {
            int answer = 1;
            boolean[][] dp = new boolean[s.length()][s.length()];
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = true;
            }

            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = true;
                    answer = 2;
                }
            }

            for (int len = 3; len <= s.length(); len++) {
                for (int i = 0; i < s.length() - len + 1; i++) {
                    int j = i + len - 1;
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        answer = len;
                    }
                }
            }
            return answer;
        }
    }
}

/*
문제 분석
1. 정보
    - 앞뒤를 뒤집어도 똑같은 문자열을 팰린드롬이라고 한다.
2. 목표
    - 문자열 s가 주어질 때, s의 부분문자열 중 가장 긴 팰린드롬의 길이를 return
3. 제약 조건
    - 문자열 s의 길이 : 2500 이하의 자연수
    - 문자열 s는 알파벳 소문자로만 구성


풀이
1. 아이디어
    - DP로 푸는 방법을 생각해 보았다.
    - S 부분 문자열의 시작점을 s, 끝점을 e라고 가정하면 dp[s][e]에 팰린드롬 여부를 저장
    - dp[s][s] -> 항상 true
    - dp[s][s + 1] -> s와 s + 1의 문자가 같다면 true
    - 길이 3 이상 부터는
        - s와 e의 문자가 같고, dp[s + 1][e - 1] = true 이면 팰린드롬 이므로, dp[s][e] = true
*/
