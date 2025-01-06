package Programmers.Codekata;
import java.util.*;
import java.util.stream.*;

public class _42_삼총사 {

    class Solution_for {
        public int solution(int[] number) {
            int answer = 0;
            for(int i = 0 ; i < number.length - 2 ; i++){
                for(int j = i + 1 ; j < number.length - 1 ; j++){
                    for(int k = j + 1 ; k < number.length ; k++){
                        int sum = number[i] + number[j] + number[k];
                        if(sum == 0){
                            answer++;
                        }
                    }
                }
            }
            return answer;
        }
    }

    class Solution_stream {
        public int solution(int[] number) {
            return (int)IntStream.range(0, number.length - 2)
                    .flatMap(i -> IntStream.range(i + 1, number.length - 1)
                            .flatMap(j -> IntStream.range(j + 1, number.length)
                                    .filter(k -> number[i] + number[j] + number[k] == 0)
                            )
                    )
                    .count();
        }
    }

}
