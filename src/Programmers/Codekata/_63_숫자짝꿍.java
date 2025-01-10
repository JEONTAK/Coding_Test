package Programmers.Codekata;

public class _63_숫자짝꿍 {

    class Solution {
        public String solution(String X, String Y) {
            int[] countX = new int[10];
            int[] countY = new int[10];

            for(char c : X.toCharArray()){
                countX[c - '0']++;
            }

            for(char c : Y.toCharArray()){
                countY[c - '0']++;
            }

            StringBuilder sb = new StringBuilder();
            for(int i = 9 ; i >= 0 ; i--){
                int canPut = Math.min(countX[i], countY[i]);
                sb.append(String.valueOf(i).repeat(canPut));
            }
            if(sb.length() == 0) return "-1";

            if(sb.charAt(0) == '0') return "0";

            return sb.toString();
        }
    }

}
