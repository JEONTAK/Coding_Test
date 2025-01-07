package Programmers.Codekata;

import java.util.*;

public class _47_문자열내마음대로정렬하기 {

    class Solution_me {

        public String[] solution(String[] strings, int n) {
            String[] answer = new String[strings.length];
            for(int i = 0 ; i < strings.length ; i++){
                answer[i] = strings[i].charAt(n) + strings[i];
            }
            Arrays.sort(answer);
            for(int i = 0 ; i < answer.length; i++){
                answer[i] = answer[i].substring(1);
            }
            return answer;
        }
    }

    class solution_other {

        public String[] solution(String[] strings, int n) {
            Arrays.sort(strings, new Comparator<String>(){
                @Override
                public int compare(String s1, String s2){
                    return s1.charAt(n) - s2.charAt(n) != 0 ? s1.charAt(n) - s2.charAt(n) : s1.compareTo(s2);
                }
            });
            return strings;
        }
    }
}
