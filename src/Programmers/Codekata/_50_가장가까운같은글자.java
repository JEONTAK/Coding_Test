package Programmers.Codekata;

import java.util.*;

public class _50_가장가까운같은글자 {

    class Solution {
        public int[] solution(String s) {
            int[] answer = new int[s.length()];
            int[] idx = new int[26];
            Arrays.fill(idx, -1);
            for(int i = 0 ; i < s.length(); i++){
                char cur = s.charAt(i);
                if(idx[cur - 'a'] == -1){
                    answer[i] = idx[cur - 'a'];
                    idx[cur - 'a'] = i;
                }else{
                    answer[i] = i - idx[cur - 'a'];
                    idx[cur - 'a'] = i;
                }
            }
            return answer;
        }
    }
}
