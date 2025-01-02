package Programmers.Codekata;

import java.util.stream.*;

public class _32_내적 {

    class Solution_for {
        public int solution(int[] a, int[] b) {
            int answer = 0;
            for(int i = 0 ; i < a.length ; i++){
                answer += a[i] * b[i];
            }

            return answer;
        }
    }

    class Solution_stream {
        public int solution(int[] a, int[] b) {
            return IntStream.range(0, a.length)
                    .map(i -> a[i] * b[i])
                    .sum();
        }
    }

}
