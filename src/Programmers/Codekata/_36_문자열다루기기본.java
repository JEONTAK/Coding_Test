package Programmers.Codekata;

import java.util.stream.*;

public class _36_문자열다루기기본 {

    class Solution_for {
        public boolean solution(String s) {
            for(int i = 0 ; i < s.length() ; i++){
                if(s.charAt(i) < 48 || s.charAt(i) > 57){
                    return false;
                }
            }
            s = s.toLowerCase();
            return s.length() == 4 || s.length() == 6;
        }
    }

    class Solution_stream {
        public boolean solution(String s) {

            if(IntStream.range(0, s.length())
                    .allMatch(i -> s.charAt(i) >= '0' && s.charAt(i) <= '9')){
                return s.length() == 4 || s.length() == 6;
            }
            return false;
        }
    }
}
