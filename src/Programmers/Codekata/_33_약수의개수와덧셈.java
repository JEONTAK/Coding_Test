package Programmers.Codekata;

import java.util.stream.*;

public class _33_약수의개수와덧셈 {

    class Solution_for {
        public int solution(int left, int right) {
            int answer = 0;
            for(int i = left ; i <= right ; i++){
                int sum = 0;
                for(int j = 1 ; j <= i ; j++){
                    if(i % j == 0){
                        sum++;
                    }
                }
                answer += (sum % 2 == 0 ? i : -i);
            }

            return answer;
        }
    }

    class Solution_stream {
        public int solution(int left, int right) {
            /*
            left ~ right까지
            i로 하나씩 꺼내서
                1 ~ i 까지
                j로 하나씩 꺼내서
                약수이면 count
            count 한 값이 짝수이면 i, 홀수이면 -i
            sum
             */
            return IntStream.range(left, right + 1)
                    .map(i -> {
                        long cnt = IntStream.range(1, i + 1)
                                .filter(j -> i % j == 0)
                                .count();
                        return cnt % 2 == 0 ? i : -i;
                    })
                    .sum();
        }
    }

}
