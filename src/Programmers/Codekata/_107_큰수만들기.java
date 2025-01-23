package Programmers.Codekata;

import java.util.ArrayDeque;
import java.util.Deque;

public class _107_큰수만들기 {

    class Solution {
        public String solution(String number, int k) {
            Deque<Character> s = new ArrayDeque<>();
            for (int i = 0; i < number.length(); i++) {
                while (!s.isEmpty() && s.peek() < number.charAt(i) && k > 0) {
                    s.pop();
                    k--;
                }
                s.push(number.charAt(i));
            }

            StringBuilder sb = new StringBuilder();

            while (!s.isEmpty()) {
                sb.append(s.pop());
            }
            sb.reverse();
            if (k > 0) {
                sb = new StringBuilder(sb.substring(0, sb.toString().length() - k));
            }
            return sb.toString();
        }
    }

}

/*
문제 분석
1. 정보
    - 어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하여라.
2. 목표
    - 문자열 형식으로 숫자 number와 제거할 수의 개수 k가 주어질 때, 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return
3. 제약 조건
    - 2 <= number 자리 수 <= 1000000
    - 1 <= k < number 자리 수

풀이
1. 아이디어
    - 그리디 알고리즘 및 스택 사용
        - 스택에 가장 왼쪽 수부터 차례 대로 집어 넣음
            - 만약 스택이 비어있지 않다면
                - 현재 집어 넣을 값과 s.peek()값을 비교
                    - 집어 넣을 값이 더 크다면, s.pop()후 해당 값 push
                    - k--;
ex) 4177252841
- 4 / 4
- 1 / 1 4 (3)
- 7 / 7 4
- 7 / 7 7 4
- 2 / 2 7 7 4
- 5 / 5 7 7 4 (2)
- 2 / 2 5 7 7 4
- 8 / 8 5 7 7 4 (1)
- 4 / 4 8 5 7 7 4
- 1 / 1 4 8 5 7 7 4
k = 1 -> 1개 더 제거
-> 스택에 있는 가장 마지막 정수 제거 후 return
*/
