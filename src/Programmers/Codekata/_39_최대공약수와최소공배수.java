package Programmers.Codekata;

public class _39_최대공약수와최소공배수 {

    class Solution {
        public int[] solution(int n, int m) {
            int[] answer = new int[2];

            answer[0] = GCD(m,n);
            answer[1] = n * m / answer[0];
            return answer;
        }

        public int GCD(int a, int b){
            if(b == 0) return a;
            else return GCD(b, a % b);
        }
    }
}
