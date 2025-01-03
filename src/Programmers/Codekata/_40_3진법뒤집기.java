package Programmers.Codekata;

import java.util.*;

public class _40_3진법뒤집기 {

    class Solution_me {
        public int solution(int n) {
            ArrayList<Integer> list = new ArrayList<>();
            int answer = 0;

            while(n > 0){
                list.add(n % 3);
                n /= 3;
            }
            int idx = 0;

            for(int i = list.size() - 1 ; i >= 0 ; i--){
                answer += list.get(i) * Math.pow(3,idx++);
            }
            return answer;
        }
    }

    class Solution_opt {
        public int solution(int n) {
            int answer = 0;
            while(n > 0){
                answer = answer * 3 + n % 3;
                n /= 3;
            }
            return answer;
        }
    }
}
