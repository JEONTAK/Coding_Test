package Programmers.Codekata;

public class _45_시저암호 {
    class Solution {
        public String solution(String s, int n) {
            String answer = "";
            for(int i = 0 ; i < s.length() ; i++){
                if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                    answer += (char)(((s.charAt(i) - 'A') + n) % 26 + 'A');
                }else if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                    answer += (char)(((s.charAt(i) - 'a') + n) % 26 + 'a');
                }else{
                    answer += s.charAt(i);
                }
            }
            return answer;
        }
    }
}
