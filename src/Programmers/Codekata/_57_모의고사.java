package Programmers.Codekata;

public class _57_모의고사 {

    class Solution {
        public int[] solution(int[] answers) {
            int[][] supo = {{1,2,3,4,5}, {2,1,2,3,2,4,2,5},{3,3,1,1,2,2,4,4,5,5}};
            int[] result = new int[3];
            int max = 0;
            for(int i = 0 ; i < answers.length ; i++){
                result[0] += supo[0][i % 5] == answers[i] ? 1 : 0;
                result[1] += supo[1][i % 8] == answers[i] ? 1 : 0;
                result[2] += supo[2][i % 10] == answers[i] ? 1 : 0;
                max = Math.max(Math.max(Math.max(max,result[0]),result[1]),result[2]);
            }
            int len = 0;
            for(int cur : result){
                if(max == cur)len++;
            }

            int[] answer = new int[len];
            int idx = 0;
            for(int i = 0 ; i < 3; i ++){
                if(result[i] == max){
                    answer[idx++] = i + 1;
                }
            }

            return answer;
        }
    }

}
