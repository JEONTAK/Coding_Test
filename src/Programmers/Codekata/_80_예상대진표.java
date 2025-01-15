package Programmers.Codekata;

public class _80_예상대진표 {

    class Solution {
        public int solution(int n, int a, int b) {
            int totalRound = 0;
            int idx = 1;
            int cur = 2;
            while (true) {
                if (cur == n) {
                    totalRound = idx;
                    break;
                }
                cur *= 2;
                idx++;
            }

            int left = 1;
            int right = n;
            idx = 0;
            int answer = 0;
            while (true) {
                int mid = (left + right) / 2;

                if ((a <= mid) && (b <= mid)) {
                    right = mid;
                    idx++;
                } else if ((a > mid) && (b > mid)) {
                    left = mid;
                    idx++;
                } else {
                    answer = totalRound - idx;
                    break;
                }
            }
            return answer;
        }
    }
}

/*
문제 분석
1. 정보
    - 게임 대회에는 N명이 참가하고, 토너먼트 형식으로 진행 됨
    - N명의 참가자는 각각 1번부터 N번을 차례대로 배정 받음
        - 1 <-> 2, 3 <-> 4 식으로 게임을 진행
        - 게임에 이기면 다음 라운드에 진출
            - 다음 라운드에서도 위와 동일하게 1번부터 N / 2번을 배정 받고 경기
    - 이때, 처음 라운드에서 A번을 가진 참가자는 B번 참가자와 몇 번째 라운드에서 만나는지 궁금
2. 목표
    - A번을 가진 참가자와 B번을 가진 참가자가 몇 번째 라운드에서 만나는지 return하는 함수

3. 제약 조건
    - 2^1 <= N <= 2^20
    - A,B <= N

풀이
1. 아이디어
    - 이분 탐색 활용하여 구함
        - 처음은 전체 범위
            - mid = (left + right) / 2
                - 만약 A와 B가 mid를 기준으로 다른 곳에 있다면, 해당 라운드에 만나게 됨
                    - 즉, 전체 라운드에서 현재까지 구한 라운드 값을 빼준 값이 정답
                - 만약 A와 B가 mid를 기준으로 같은 곳에 있다면, 계속 탐색
    - 전체 라운드 개수 : N을 2의 지수승으로 표현했을 때 지수 값
*/