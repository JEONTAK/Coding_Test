package Programmers.Codekata;

public class _10_배열의평균값 {

    class Solution {
        public double solution(int[] numbers) {
            double answer = 0;
            for(int cur : numbers){
                answer += cur;
            }
            return answer / numbers.length;
        }
    }

}
