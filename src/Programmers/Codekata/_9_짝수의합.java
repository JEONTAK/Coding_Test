package Programmers.Codekata;

public class _9_짝수의합 {

    class Solution {
        public int solution(int n) {
            int sum = 0;
            for(int i = n ; i > 0 ; i--){
                if(i % 2 == 0)sum += i;
            }
            return sum;
        }
    }

}
