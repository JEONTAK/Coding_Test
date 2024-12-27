package Programmers.Codekata;

import java.util.*;

public class _20_정수내림차순으로배치하기 {

    class Solution {
        public long solution(long n) {
            ArrayList<Long> list = new ArrayList<>();
            while(n > 0){
                list.add(n % 10);
                n /= 10;
            }

            Collections.sort(list, Comparator.reverseOrder());
            long answer = 0;
            for(long cur : list){
                answer = (answer + cur) * 10;
            }
            answer /= 10;

            return answer;
        }
    }

}
