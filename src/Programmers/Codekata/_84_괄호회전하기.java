package Programmers.Codekata;

import java.util.Stack;

public class _84_괄호회전하기 {

     class Solution {
        public int solution(String s) {
            int answer = 0;
            for (int i = 0; i < s.length(); i++) {
                int idx = (i + 1) % s.length();
                Stack<Character> stack = new Stack<>();
                stack.push(s.charAt(i));
                while (idx != i) {
                    stack.push(s.charAt(idx));

                    if (stack.size() >= 2) {
                        char right = stack.get(stack.size() - 1);
                        char left = stack.get(stack.size() - 2);
                        if (left == '(' && right == ')') {
                            stack.pop();
                            stack.pop();
                        }else if(left == '{' && right == '}'){
                            stack.pop();
                            stack.pop();
                        }else if(left == '[' && right == ']'){
                            stack.pop();
                            stack.pop();
                        }
                    }

                    idx = (idx + 1) % s.length();
                }

                if (stack.size() >= 2) {
                    char right = stack.get(stack.size() - 1);
                    char left = stack.get(stack.size() - 2);
                    if (left == '(' && right == ')') {
                        stack.pop();
                        stack.pop();
                    } else if (left == '{' && right == '}') {
                        stack.pop();
                        stack.pop();
                    } else if (left == '[' && right == ']') {
                        stack.pop();
                        stack.pop();
                    }
                }

                if (stack.isEmpty()) {
                    answer++;
                }
            }
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 다음 규칙을 지키는 문자열을 올바른 괄호 문자열이라고 정의
        - (), [], {} 는 모두 올바른 괄호 문자열
        - [()] 도 올바른 괄호 문자열이 될 수 있음
        - {}([]) 도 올바른 괄호 문자열이 될 수 있음
2. 목표
    - 대괄호 중괄호 소괄호로 이루어진 문자열 S가 매개변수로 주어짐
    - S를 왼쪽으로 X 칸 만큼 회전시켰을 때, S가 올바른 괄호 문자열이 되게 하는 X의 개수를 return
3. 제약 조건
    - 1 <= s의 길이 <= 1000

풀이
1. 아이디어
    - 최대 길이는 1000
        - 1000번 * 1000의 길이 = 100만
        - for문 사용해도 시간초과 안날 것이라 생각
    - for문을 사용해 시작 지점을 정함
        - while문을 사용해 다시 시작지점이 될때 까지 stack에 해당 값을 집어 넣음
            - 이때, stack에 집어 넣으면서, 이전 괄호와 현재 넣은 괄호가 짝이라면 stack에서 제거
        - while문 이후에 stack에서 모두 제거를 했는데도 값이 남아 있다면, 만들 수 없는 것.
        - stack이 비어있다면 만들 수 있으므로 1 추가
*/