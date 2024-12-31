package Programmers.Codekata;

public class _30_가운데글자가져오기 {

    class Solution {
        public String solution(String s) {
            String answer = "";
            int mid = (s.length() - 1) / 2;
            answer = s.length() % 2 != 0 ? s.substring(mid, mid + 1) : s.substring(mid, mid + 2);
            return answer;
        }
    }

}
