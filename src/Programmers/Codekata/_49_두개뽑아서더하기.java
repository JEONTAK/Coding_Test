package Programmers.Codekata;

import java.util.*;

public class _49_두개뽑아서더하기 {

    class Solution {
        public int[] solution(int[] numbers) {
            Set<Integer> set = new HashSet<>();

            for(int i = 0 ; i < numbers.length ; i++){
                for(int j = 0 ; j < numbers.length ; j++){
                    if(i != j){
                        set.add(Integer.valueOf(numbers[i] + numbers[j]));
                    }
                }
            }

            int[] answer = new int[set.size()];
            int idx = 0;
            for (int i : set) {
                answer[idx++] = i;
            }
            Arrays.sort(answer);

            return answer;
        }
    }
}
