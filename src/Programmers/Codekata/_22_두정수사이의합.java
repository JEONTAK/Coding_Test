package Programmers.Codekata;

public class _22_두정수사이의합 {
    class Solution {
        public long solution(int a, int b) {
            long answer = 0;
            if(a > b){
                int temp = a;
                a = b;
                b = temp;
            }
            for(int i = a ; i <= b ;i ++){
                answer += i;
            }
            return answer;
        }
    }
}
