package Programmers.Codekata;

public class _123_하노이의탑 {

    class Solution {

        int[][] answer;
        int idx = 0;

        public int[][] solution(int n) {
            answer = new int[(int) (Math.pow(2, n) - 1)][2];
            hanoi(n, 1, 2, 3);
            return answer;
        }

        public void hanoi(int cnt, int left, int mid, int right) {
            if (cnt == 1) {
                answer[idx][0] = left;
                answer[idx][1] = right;
                idx++;
                return;
            }

            hanoi(cnt - 1, left, right, mid);
            answer[idx][0] = left;
            answer[idx][1] = right;
            idx++;
            hanoi(cnt - 1, mid, left, right);
        }
    }

}

/*
문제 분석
1. 정보
    - 하노이 탑의 규칙은 다음과 같음.
        - 세 개의 기둥과 이 기둥에 꽂을 수 있는 크기가 다양한 원판이 존재
        - 퍼즐을 시작하기 전에는 한 기둥에 원판이 작은 것이 위에 있도록 순서대로 쌓여 있음
        - 한 번에 하나의 원판만 옮길 수 있음
        - 큰 원판이 작은 원판 위에 있어서는 안됨

2. 목표
    - 위 규칙을 만족하면서 1번 기둥에서 3번기둥으로 온전히 원판을 옮길 때의 최소 횟수를 return

3. 제약 조건
    - 1 <= n <= 15

풀이
1. 아이디어
    - 이동 규칙 찾기
        - N = 1 일때
            - 1 -> 3
        - N = 2 일때
            - 1 -> 2
            - 1 -> 3
            - 2 -> 3
        - N = 3 일때
            - 작은 2개 원판을 1 -> 2 : n - 1개 원판을 1 -> 2로 옮기는 재귀 호출
            - 가장 큰 원판을 1 -> 3
            - 작은 2개 원판을 2 -> 3 : n - 1개 원판을 2 -> 3로 옮기는 재귀 호출
     - 즉
        hanoi(n , left, mid, right)
            if n == 1:
                left에서 right로 이동
                return
            hanoi(n - 1, left, right, mid)
            left에서 right로 이동
            hanoi(n - 1, mid, left, right)
    - 총 이동 횟수 찾기
        - 위에서 구한 수식을 활용하면
            - T(n) = T(n - 1) + 1 + T(n - 1)
            - T(n) = 2*T(n - 1) + 1
            - T(2) = T(1) + 1 + T(1)
            - ... n이 1일때까지 이어짐
            - 즉 T(n) = 2* (2 * T(n - 2) + 1)
            - T(n) = 2^n-1 + 2^n-2 + ... + 1
            - T(n) = 2^n - 1이 나온다.
*/