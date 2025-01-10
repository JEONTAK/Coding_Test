package Programmers.Codekata;

public class _62_옹알이_2 {

    class Solution {
        public int solution(String[] babbling) {
            String[] canSay = {"aya","ye","woo","ma"};
            int answer = 0;

            for(String word : babbling){
                boolean check = true;

                for(String cur : canSay){
                    if(word.contains(cur + cur)){
                        check = false;
                        break;
                    }

                    word = word.replace(cur, " ");
                }

                if(check && word.trim().isEmpty()){
                    answer++;
                }
            }

            return answer;
        }
    }

}
