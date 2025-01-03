package Programmers.Codekata;

import java.util.stream.*;

public class _31_수박수박수박수박수박수 {

    class Solution_for {
        public String solution(int n) {
            String answer = "";
            for(int i = 0 ; i < n ; i++){
                if(i % 2 == 0){
                    answer += "수";
                }else{
                    answer += "박";
                }
            }

            return answer;
        }
    }

    class Solution_stream {
        public String solution(int n) {
            return IntStream.range(0,n)
                    .mapToObj(i -> i % 2 == 0 ? "수" : "박")
                    .collect(Collectors.joining());
        }
    }

}
