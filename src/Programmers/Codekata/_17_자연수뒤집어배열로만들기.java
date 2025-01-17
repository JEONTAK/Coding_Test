package Programmers.Codekata;

import java.util.ArrayList;
import java.util.List;

public class _17_자연수뒤집어배열로만들기 {

    class Solution {
        public int[] solution(long n) {
            List<Integer> list = new ArrayList<>();

            while(n > 0){
                list.add((int)(n % 10));
                n /= 10;
            }

            int[] answer = new int[list.size()];

            for(int i = 0 ; i < list.size() ; i++){
                answer[i] = list.get(i);
            }

            return answer;
        }
    }
}