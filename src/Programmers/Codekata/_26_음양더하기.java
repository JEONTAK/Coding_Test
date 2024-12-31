package Programmers.Codekata;

import java.util.stream.*;

public class _26_음양더하기 {

    class Solution_for {

        public int solution(int[] absolutes, boolean[] signs) {
            int answer = 0;
            for(int i = 0 ; i < absolutes.length ; i++){
                answer += signs[i] ? absolutes[i] : -1 * absolutes[i];
            }
            return answer;
        }

    }

    class Solution_stream {

        public int solution(int[] absolutes, boolean[] signs) {
            return IntStream.range(0, absolutes.length)
                    .map(i -> signs[i] ? absolutes[i] : -absolutes[i])
                    .sum();
        }
    }
}
