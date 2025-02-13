package Programmers.Codekata;

public class _131_NQueen {

    class Solution {

        int answer = 0;
        int[] queen;

        public int solution(int n) {
            queen = new int[n];

            for (int i = 0; i < n; i++) {
                queen[0] = i;
                compute(1, n);
            }

            return answer;
        }

        private void compute(int idx, int n) {
            if (idx == n) {
                answer++;
                return;
            }

            for (int i = 0; i < n; i++) {
                queen[idx] = i;
                if (isAvailable(idx)) {
                    compute(idx + 1, n);
                }
            }
        }

        private boolean isAvailable(int idx) {
            for (int i = 0; i < idx; i++) {
                if (queen[idx] == queen[i] || Math.abs(queen[idx] - queen[i]) == Math.abs(idx - i)) {
                    return false;
                }
            }
            return true;
        }
    }

}

/*
문제 분석
1. 정보
    - 가로 세로 길이가 n인 정사각형으로 된 체스판이 존재
    - 체스판 위의 n개의 퀸이 서로를 공격할 수 없도록 배치하고 싶음
2. 목표
    - n개의 퀸이 조건에 만족하도록 배치하는 방법의 수를 return

3. 제약 조건
    - 퀸은 가로, 세로, 대각선으로 이동 가능
    - 1 <= N <= 12

풀이
1. 아이디어
    - 퀸은 가로 세로 대각선으로 이동가능
    - 따라서 한 줄에는 하나의 퀸만 배치 가능
        - queen[] 배열을 만들어 i번째 줄에 몇번째 행에 들어가는지 저장
        - queen[0]을 0부터 n - 1까지 차례대로 저장하며 compute(1) 시작
        - compute(idx)
            - 만약 idx == n - 1
                - 모든 퀸이 자리에 놓였으므로 answer++;
                - return;
            - i : 0 ~ n - 1 순회
                - (idx , i)에 퀸 놓는 경우 시도
                - 만약 놓을 수 있다면, queen[idx]에 i 저장 후 compute(idx + 1);
*/
