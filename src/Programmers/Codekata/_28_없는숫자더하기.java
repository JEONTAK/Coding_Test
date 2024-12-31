package Programmers.Codekata;

import java.util.stream.*;

public class _28_없는숫자더하기 {

    class Solution_for {
        public int solution(int[] numbers) {
            int answer = 0;
            boolean[] check = new boolean[10];
            for(int cur : numbers){
                check[cur] = true;
            }
            for(int i = 0 ; i < 10 ; i++){
                answer += check[i] ? 0 : i;
            }
            return answer;
        }
    }

    class Solution_stream {
        public int solution(int[] numbers) {
            boolean[] check = new boolean[10];
            for(int cur : numbers){
                check[cur] = true;
            }
            return IntStream.range(0, check.length)
                    .map(i -> check[i] ? 0 : i)
                    .sum();
        }
    }
}
