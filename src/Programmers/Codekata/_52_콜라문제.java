package Programmers.Codekata;

public class _52_콜라문제 {
    class Solution {
        public int solution(int a, int b, int n) {
            int answer = 0;
            while (n >= a) {
                int canChange = n / a;
                int yetChange = n % a;
                n = canChange * b + yetChange;
                answer += canChange * b;
            }

            return answer;
        }
    }
}
