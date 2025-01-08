package Programmers.Codekata;

import java.util.stream.*;

public class _51_푸드파이트대회 {

    class Solution_for {
        public String solution(int[] food) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < food.length; i++) {
                for (int j = 0; j < food[i] / 2; j++) {
                    sb.append(i);
                }
            }
            String answer = sb.toString();
            answer += "0" + sb.reverse().toString();
            return answer;
        }
    }

    class Solution_stream {

        public String solution(int[] food) {
            StringBuilder sb = new StringBuilder();

            IntStream.range(1, food.length)
                    .forEach(i -> sb.append(String.valueOf(i).repeat(food[i] / 2)));
            String answer = sb.toString();
            answer += "0" + sb.reverse().toString();
            return answer;
        }
    }
}
