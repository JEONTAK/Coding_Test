package Programmers.Codekata;

public class _41_이상한문자만들기 {
    class Solution {
        public String solution(String s) {
            String answer = "";
            s = s.toLowerCase();
            String[] split = s.split("");
            int idx = 0;
            for(int i = 0 ; i < split.length ; i++){
                if(split[i].equals(" ")){
                    idx = 0;
                    answer += split[i];
                }else{
                    if(idx % 2 == 0){
                        answer += split[i].toUpperCase();
                        idx++;
                    }else{
                        answer += split[i];
                        idx++;
                    }
                }
            }
            return answer;
        }
    }
}


