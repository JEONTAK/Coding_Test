package Programmers.Codekata;

import java.util.*;

public class _64_체육복 {

    class Solution {
        public int solution(int n, int[] lost, int[] reserve) {
            Arrays.sort(lost);
            int lostP = lost.length;
            int[] rS = new int[n + 2];
            for(int cur : reserve){
                rS[cur]++;
            }

            for(int i = 0 ; i < lost.length ; i++){
                if(rS[lost[i]] > 0){
                    rS[lost[i]]--;
                    lost[i] = -1;
                    lostP--;
                }
            }

            for(int i = 0 ; i < lost.length ; i++){
                if(lost[i] == -1)continue;

                if(rS[lost[i] - 1] > 0){
                    rS[lost[i] - 1]--;
                    lostP--;
                }else if(rS[lost[i] + 1] > 0){
                    rS[lost[i] + 1]--;
                    lostP--;
                }
            }
            return n - lostP;
        }
    }

}
