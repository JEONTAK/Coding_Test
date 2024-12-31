package Programmers.Codekata;

import java.util.stream.*;

public class _27_핸드폰번호가리기 {

    class Solution_for {
        public String solution(String phone_number) {
            String answer = "";
            for(int i = 0 ; i < phone_number.length() - 4 ; i++){
                answer = answer + "*";
            }
            for(int i = phone_number.length() - 4 ; i < phone_number.length() ; i++){
                answer += phone_number.charAt(i);
            }

            return answer;
        }
    }

    class Solution_stream {
        public String solution(String phone_number) {
            String maskedPart = IntStream.range(0, phone_number.length() - 4)
                    .mapToObj(i -> "*")
                    .collect(Collectors.joining());

            String visiblePart = IntStream.range(phone_number.length() - 4, phone_number.length())
                    .mapToObj(i -> String.valueOf(phone_number.charAt(i)))
                    .collect(Collectors.joining());

            return maskedPart + visiblePart;
        }
    }
}
