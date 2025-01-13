package Programmers.Codekata;

public class _67_둘만의암호 {


    class Solution {
        public static String solution(String s, String skip, int index) {
            StringBuilder sb = new StringBuilder();
            boolean[] check = new boolean[26];
            for (int i = 0; i < skip.length(); i++) {
                check[skip.charAt(i) - 'a'] = true;
            }

            for(int i = 0 ; i < s.length(); i++){
                int cnt = index;
                int cur = s.charAt(i) - 'a';
                while(cnt > 0){
                    if(!check[(cur + 1) % 26]){
                        cnt--;

                    }
                    cur = (cur + 1) % 26;
                }
                sb.append((char)(cur + 'a'));
            }
            return sb.toString();
        }
    }
}
/*
문제 분석
1. 정보
    - 두 문자열 S, skip, 자연수 index가 주어질 때, 아래 규칙에 따라 문자열은 만듬
        - 문자열 S의 각 알파벳을 index만큼 뒤의 알파벳으로 바꿔줌
        - index 만큼의 뒤의 알파벳이 z를 넘어갈 경우 다시 a로 돌아감
        - skip에 있는 알파벳은 제외하고 건너뜀
2. 목표
    - 위 내용이 주어질 때, 규칙대로 s를 변환한 결과를 return
3. 제약 조건
    - 5 <= s <= 50
    - 1 <= skip <= 10
    - s와 skip은 소문자로만 이루어짐
        - skip에 포함되는 알파벳은 s에 없음
    - 1 <= index <= 20

풀이
1. 아이디어
    - 최대 50 * 20 -> 1000 바로 index를 더해주지 않고 하나씩 풀어나가도 가능
    - 먼저 boolean[]배열 선언해 해당 알파벳이 스킵되는지 유무 설정
        - 스킵 된다면 index - 1 하지 않고 넘어감
    - index = 0 이 될때가 변환된 암호

*/
