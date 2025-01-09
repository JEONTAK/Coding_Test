package Programmers.Codekata;

public class _60_기사단원의무기 {

    class Solution {

        public int solution(int number, int limit, int power) {
            int answer = 0;
            int[] divisor = new int[number + 1];
            for(int i = 1 ; i <= number; i++){
                for(int j = i ; j <= number ; j += i){
                    divisor[j]++;
                }
            }

            for(int i = 1 ; i <= number ; i++){
                answer += divisor[i] > limit ? power : divisor[i];
            }

            return answer;
        }
    }

}
