package Programmers.Codekata;

public class _29_제일작은수제거하기 {

    class Solution {
        public int[] solution(int[] arr) {
            int min = Integer.MAX_VALUE;
            for(int cur : arr){
                min = Math.min(cur, min);
            }

            if(arr.length == 1){
                int[] answer = {-1};
                return answer;
            }else{
                int idx = 0;
                int[] answer = new int[arr.length - 1];
                for(int cur : arr){
                    if(cur != min){
                        answer[idx++] = cur;
                    }
                }
                return answer;
            }
        }
    }
}
