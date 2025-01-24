package Programmers.Codekata;

public class _112_행렬테두리회전하기 {

    class Solution {

        int[][] arr;

        public int[] solution(int rows, int columns, int[][] queries) {
            arr = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    arr[i][j] = (i * columns) + j + 1;
                }
            }
            int[] answer = new int[queries.length];

            for (int i = 0; i < queries.length; i++) {
                answer[i] = compute(queries[i]);
            }
            return answer;
        }

        private int compute(int[] query) {
            int x1 = query[0] - 1;
            int y1 = query[1] - 1;
            int x2 = query[2] - 1;
            int y2 = query[3] - 1;
            int min = arr[x1][y1];
            int tmp = arr[x1][y1];

            for (int i = x1; i < x2; i++) {
                min = Math.min(min, arr[i][y1]);
                arr[i][y1] = arr[i + 1][y1];
            }

            for (int i = y1; i < y2; i++) {
                min = Math.min(min, arr[x2][i]);
                arr[x2][i] = arr[x2][i + 1];
            }

            for (int i = x2; i > x1; i--) {
                min = Math.min(min, arr[i][y2]);
                arr[i][y2] = arr[i - 1][y2];
            }

            for (int i = y2; i > y1 + 1; i--) {
                min = Math.min(min, arr[x1][i]);
                arr[x1][i] = arr[x1][i - 1];
            }
            min = Math.min(min, arr[x1][y1 + 1]);
            arr[x1][y1 + 1] = tmp;
            return min;
        }
    }

}

/*
문제 분석
1. 정보
    - rows * columns 크기인 행렬이 있다.
    - 행렬에는 1부터 rows * columns 까지의 숫자가 한 줄씩 순서대로 적혀 있다.
    - 해당 행렬에서 직사각형 모양의 범위를 여러번 선택해, 테두리 부분에 있는 숫자들을 시계방향으로 회전시키려 한다.
    - (x1, y1, x2, y2)
        - x1행 y1열 부터 x2행 y2열 까지의 영역에 해당하는 직사각형의 테두리에 있는 숫자들을 한 칸씩 시계 방향으로 회전
2. 목표
    - 행렬의 세로길이 rows, 가로길이 columns, 회전 목록 queries 가 주어질 때, 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 배열에 담아 return
3. 제약 조건
    - 2 <= rows <= 100
    - 2 <= columns <= 100
    - 1 <= queries <= 10000
        - 1 <= x1 < x2 <= rows, 1 <= y1 < y2 <= columns

풀이
1. 아이디어
    - 시뮬레이션
        - 해당 범위를 사용해 직접 배열을 업데이트
        - 업데이트하는 과정에서 가장 작은 값을 찾은 후 배열에 넣음
    - 먼저 rows와 columns로 하는 배열을 생성
        - arr[i][j] = i * columns + j + 1
    - queries의 길이가 answer의 길이
        - answer[i] = compute(queries[i]) 통해 값 구함.
    - compute(queries[i])
        - x1 = queries[0] - 1;
        - y1 = queries[1] - 1;
        - x2 = queries[2] - 1;
        - y2 = queries[3] - 1;
        - 시작을 x1,y1에서 시작
            - 시작점이다
                - y + 1이 y2가 될때 까지
                    - x, y + 1 자리에 x, y값 넣음
                - 이후 x + 1이 x2가 될때 까지
                    - x + 1, y 자리에 x, y 값 넣음
                - 이후 y - 1이 y1가 될때 까지
                    - x, y - 1 자리에 x, y 값 넣음
                - 이후 x - 1 이 x1이 될때 까지
                    - x - 1, y 자리에 x,y 값 넣음
            - 종료 하면서 업데이트 동안 받은 숫자의 최솟값 return
*/
