package Programmers.Codekata;

import java.util.*;

public class _56_과일장수 {

    class Solution {
        public int solution(int k, int m, int[] score) {
            int answer = 0;
            Arrays.sort(score);

            for(int i = 0 ; i < score.length / 2 ; i++){
                int tmp = score[i];
                score[i] = score[score.length - i - 1];
                score[score.length - i - 1] = tmp;
            }

            for(int i = 0 ; i < score.length ; i += m){
                if(i + m > score.length){
                    break;
                }
                answer += score[i + m - 1] * m;
            }

            return answer;
        }
    }
}
