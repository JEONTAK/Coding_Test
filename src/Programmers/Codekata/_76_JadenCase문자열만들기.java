package Programmers.Codekata;

public class _76_JadenCase문자열만들기 {

    class Solution {
        public String solution(String s) {
            boolean flag = true;
            char[] tmp = s.toCharArray();
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] == ' ') {
                    flag = true;
                    continue;
                }

                if (flag) {
                    if (tmp[i] >= 'a' && tmp[i] <= 'z') {
                        tmp[i] -= 32;
                    }
                    flag = false;
                } else {
                    if (tmp[i] >= 'A' && tmp[i] <= 'Z') {
                        tmp[i] += 32;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tmp.length; i++) {
                sb.append(tmp[i]);
            }
            return sb.toString();
        }
    }
}
/*
문제 분석
1. 정보
    - JadenCase란 모든 단어의 첫 문자가 대문자이고, 그 외의 알파벳은 소문자인 문자열을 말함
    - 단, 첫 문자가 알파벳이 아닐 때에는 이어지는 알파벳은 소문자로 쓰면 됨

2. 목표
    - JadenCase로 변환한 값을 return

3. 제약 조건
    - 1 <= s의 길이 <= 200
    - s는 알파벳, 숫자, 공백문자로 이루어져 있음
        - 숫자는 단어의 첫 문자로만 나옴
        - 숫자로만 이루어진 단어는 존재 X
        - 공백 문자가 연속해서 나올 수 있음.

풀이
1. 아이디어
    - s의 길이가 짧으므로 0 ~ s의 길이까지 반복문 수행
        - 만약 현재문자가 공백이라면 그대로 진행
        - 만약 이전문자가 공백이었다면
            - 현재 문자가 숫자 or 알파벳 대문자라면 그대로 진행
            - 현재 문자가 알파벳 소문자라면 대문자로 변환
        - 만약 이전문자가 공백이 아니라면
            - 현재 문자가 알파벳 대문자라면 소문자로 변환
    - 변환한 결과 값 return
*/