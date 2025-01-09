package Programmers.Codekata;

public class _59_덧칠하기 {

    class Solution {
        public int solution(int n, int m, int[] section) {
            int answer = 0;
            int len = section.length;
            int idx = 0;

            while(idx < len){
                int curIdx = section[idx];
                int curPaintLen = curIdx + m - 1;
                while(idx < len){
                    if(section[idx] <= curPaintLen){
                        idx++;
                    }else{
                        break;
                    }
                }
                answer++;
            }

            return answer;
        }
    }

}
