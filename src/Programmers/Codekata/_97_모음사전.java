package Programmers.Codekata;

public class _97_모음사전 {

    class Solution {

        int answer = -1;
        int result = 0;
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};

        public int solution(String word) {
            StringBuilder words = new StringBuilder();
            compute(0, word, words);
            return answer;
        }

        private void compute(int idx, String word, StringBuilder words) {
            result++;
            if (word.contentEquals(words)) {
                answer = result - 1;
                return;
            }

            if (idx == 5 || answer != -1) {
                return;
            }

            for (int i = 0; i < vowels.length; i++) {
                words.append(vowels[i]);
                compute(idx + 1, word, words);
                words.deleteCharAt(words.length() - 1);
            }
        }
    }

}

/*
문제 분석
1. 정보
    - 사전에 알파벳 모음 A, E, I, O, U 만을 사용하여 만들 수 있는 , 길이 5 이하의 모든 단어가 수록되어 있음
    - 사전에서 첫 번째 단어는 A 이고, 그 다음은 AA 이며, 마지막 단어는 UUUUU 임

2. 목표
    - 단어 하나가 주어질 때, 해당 단어가 사전에서 몇 번째 단어인지 return
    
3. 제약 조건
    - 1 <= 단어의 길이 <= 5
    - 단어는 알파벳 대문자 A, E, I, O, U로 이루어짐

풀이
1. 아이디어
    - DFS? 백트래킹? 사용
        - 전역 변수로 순서 값을 저장
        - A 부터 U 까지 시작점으로 정하고, 메서드를 통해 다음 모음을 정함
        - 만약 해당 단어가 주어진 단어와 같다면, 해당 순번을 return한다.
       
*/