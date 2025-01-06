package Programmers.Codekata;

public class _43_크기가작은부분 {
    class Solution {
        public int solution(String t, String p) {
            int answer = 0;
            for(int i = 0 ; i <= t.length() - p.length() ; i++){
                long cur = Long.parseLong(t.substring(i,i + p.length()));
                if(cur <= Long.parseLong(p)){
                    answer++;
                }
            }
            return answer;
        }
    }
}
