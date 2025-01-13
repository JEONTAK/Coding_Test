package Programmers.Codekata;

import java.util.Stack;

public class _68_햄버거만들기 {

    class Solution {
        public int solution(int[] ingredient) {
            Stack<Integer> stack = new Stack<>();
            int[] seq = {1, 3, 2, 1};
            int answer = 0;
            for (int cur : ingredient) {
                stack.push(cur);

                if (stack.size() >= 4) {
                    boolean flag = true;
                    for (int i = 0; i < 4; i++) {
                        if (stack.get(stack.size() - (i + 1)) != seq[i]) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        answer++;
                        for (int i = 0; i < 4; i++) {
                            stack.pop();
                        }
                    }
                }
            }

            return answer;
        }
    }
}

/*
문제 분석
1. 정보
    - 조리 된 재료를 조리된 순서대로 상수가 아래서 위로 햄버거를 쌓음
    - 정해진 순서는 빵-야채-고기-빵으로 쌓인 햄버거만 포장 가능
    - 재료의 높이는 무시
    - 예로, 재료의 순서가 [야채 빵 빵 야채 고기 빵 야채 고기 빵] 일때
        - 상수는 6번째 재료가 쌓였을 때, 3번째 재료부터 여섯 번째 재료를 이용하여 햄버거를 포장
        - 9번째 재료가 쌓였을 때, 2번째 재료와 7 ~ 9 번째 재료를 이용하여 햄버거를 포장
    - 1 : 빵
    - 2 : 야채
    - 3 : 고기
    - 를 의미
    - 즉 1 2 3 1 순으로 들어올때, 햄버거 포장 가능

2. 목표
    - 포장 가능한 햄버거의 개수를 출력

3. 제약 조건
    - 1 <= 재료의 길이 <= 1,000,000
    - 재료는 1, 2, 3 중 하나의 값

풀이
1. 아이디어
     - Stack 사용
     - 재료를 하나씩 Stack에 추가
     - Stack의 마지막 4개 요소가 1 2 3 1 순서라면
        - 마지막 4개 요소 제거
        - 햄버거 포장++
     - Stack에서 모든 햄버거를 포장 가능할 때 까지 반복
*/