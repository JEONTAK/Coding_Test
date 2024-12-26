package Programmers.Codekata;

public class _12_평균구하기 {

    class Solution {
        public double solution(int[] arr) {
            double answer = 0;
            for(int cur : arr){
                answer += cur;
            }
            return answer / arr.length;
        }
    }

}
