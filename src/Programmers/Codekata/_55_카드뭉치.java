package Programmers.Codekata;

public class _55_카드뭉치 {
    class Solution {
        public String solution(String[] cards1, String[] cards2, String[] goal) {
            String answer = "Yes";
            int oneIdx = 0;
            int twoIdx = 0;

            for (String s : goal) {
                if (cards1[oneIdx].equals(s)) {
                    oneIdx = Math.min(oneIdx + 1, cards1.length - 1);
                } else if (cards2[twoIdx].equals(s)) {
                    twoIdx = Math.min(twoIdx + 1, cards2.length - 1);
                } else {
                    answer = "No";
                    break;
                }
            }
            return answer;
        }
    }
}
