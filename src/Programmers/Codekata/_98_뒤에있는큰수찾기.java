package Programmers.Codekata;

import java.util.Stack;

public class _98_뒤에있는큰수찾기 {

    class Solution {
        public int[] solution(int[] numbers) {
            Stack<Integer> s = new Stack<>();
            int[] answer = new int[numbers.length];

            for (int i = numbers.length - 1; i >= 0; i--) {

                while (!s.isEmpty() && s.peek() <= numbers[i]) {
                    s.pop();
                }

                answer[i] = s.isEmpty() ? -1 : s.peek();

                s.push(numbers[i]);
            }

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 정수로 이루어진 배열 numbers가 존재
    - 배열의 각 원소들에 대하여 자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수를 뒷 큰수라 함

2. 목표
    - 정수 배열 numbers가 주어질 때, 모든 원소에 대한 뒷 큰수들을 차례로 담은 배열을 return

3. 제약 조건
     - 4 <= numbers 길이 <= 1000000
        - 1 <= numbers[i] <= 1000000

풀이
1. 아이디어
    - Stack 활용
        - numbers의 값을 뒤에서 부터 차례대로 하나씩 stack에 집어 넣음
            - 만약 스택에서 현재 숫자보다 크거나 같은 숫자가 있으면 해당 숫자를 제거
            - 스택이 비어있다면 뒷 큰수가 없으므로 -1 저장
            - 비어있지 않다면 peek()를 통해 뒷 큰수를 뽑아 저장
*/
