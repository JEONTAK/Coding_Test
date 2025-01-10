package Programmers.Codekata;

import java.util.*;

public class _61_로또의최고순위와최저순위 {

    class Solution {
        public int[] solution(int[] lottos, int[] win_nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(6,1);
            map.put(5,2);
            map.put(4,3);
            map.put(3,4);
            map.put(2,5);
            map.put(1,6);
            map.put(0,6);

            int[] answer = new int[2];
            int correct = 0;
            int zero = 0;
            for(int i = 0 ; i < 6 ; i++){
                if(lottos[i] == 0){
                    zero++;
                    continue;
                }
                for(int j = 0 ; j < 6 ; j++){
                    if(lottos[i] == win_nums[j]){
                        correct++;
                        break;
                    }
                }
            }
            answer[0] = map.get(correct + zero);
            answer[1] = map.get(correct);
            return answer;
        }
    }

}
