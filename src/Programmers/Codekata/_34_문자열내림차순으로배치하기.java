package Programmers.Codekata;

import java.util.*;
import java.util.stream.*;

public class _34_문자열내림차순으로배치하기 {

    class Solution_for {
        public String solution(String s) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String answer = "";
            for(int i = arr.length - 1 ; i >= 0 ; i--){
                answer += arr[i];
            }
            return answer;
        }
    }

    class Solution_stream {
        public String solution(String s) {
            /*
            s -> char[]로 변환
            IntStream에서 -> (char) 통해 Character로 변환
            sorted 통해 내림차순으로 정렬
            map 통해 Character -> String으로 변환
            collect 통해 하나의 String으로 합침
             */
            return s.chars()
                    .mapToObj(c -> (char) c)
                    .sorted(Collections.reverseOrder())
                    .map(String::valueOf)
                    .collect(Collectors.joining());
        }
    }

}
