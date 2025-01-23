package Programmers.Codekata;

public class _105_쿼드압축후개수세기 {

    class Solution {

        int[] answer = new int[2];

        public int[] solution(int[][] arr) {
            int n = arr[0].length;
            compute(0, 0, n, arr);

            return answer;
        }

        private void compute(int x, int y, int n, int[][] arr) {
            int num = arr[x][y];
            if (isSame(x, y, n, arr)) {
                answer[num]++;
                return;
            }

            compute(x, y, n / 2, arr);
            compute(x, y + n / 2, n / 2, arr);
            compute(x + n / 2, y, n / 2, arr);
            compute(x + n / 2, y + n / 2, n / 2, arr);

        }

        private boolean isSame(int x, int y, int n, int[][] arr) {
            int num = arr[x][y];
            for (int i = x; i < x + n; i++) {
                for (int j = y; j < y + n; j++) {
                    if (arr[i][j] != num) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

}

/*
문제 분석
1. 정보
    - 0과 1로 이루어진 2^n * 2^n 크기의 2차원 정수 배열 arr이 있다고 가정하자.
    - 다음과 같은 규칙으로 쿼드 트리와 같은 방식으로 압축 한다
        - 특정 영역을 S라고 정의한다면
            - 만약 S 내부에 있는 모든 수가 같은 값이라면, S를 해당 수 하나로 압축한다
            - 그렇지 않다면 S를 정확히 4개의 균일한 정사각형 영역으로 쪼갠 뒤, 각 정사각형 영역에 대해 같은 방식의 압축을 시도한다.
2. 목표
    - arr을 압축하였을 때, 배열에 최종적으로 남아 있는 0의 개수와 1의 개수를 배열에 담아 return

3. 제약 조건
    - 1 <= arr 행의 개수 <= 1024
    - arr의 각 행에 있는 모든 값은 0 또는 1

풀이
1. 아이디어
    - 재귀? DFS방식 사용하여 풀이
        - compute(0,0,n) : 0,0 부터 n의 길이를 가진 정사각형 범위를 탐색
            - 만약 해당 범위가 모두 1이거나 0 이면, 해당 값 ++
            - 아니라면,
                - compute(x,y,n/2)
                - compute(x,y + n/2,n/2)
                - compute(x + n/2,y,n/2)
                - compute(x + n/2,y + n/2,n/2)로 나누어서 진행

                0,0,4

                0,0,2
                0,2,2
                2,0,2
                2,2,2
*/
