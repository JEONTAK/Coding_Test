package Programmers.Codekata;

public class _108_삼각달팽이 {

    class Solution {
        int[][] arr;
        int[] dx = {1, 0, -1};
        int[] dy = {0, 1, -1};

        public int[] solution(int n) {
            arr = new int[n][n];
            int x = 0;
            int y = 0;
            int idx = 1;
            int dir = 0;
            int total = n * (n + 1) / 2;
            while (idx <= total) {
                boolean flag = false;
                arr[x][y] = idx++;

                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (isNotAvailable(nx, ny, n)) {
                    dir = (dir + 1) % 3;
                    nx = x + dx[dir];
                    ny = y + dy[dir];
                }
                x = nx;
                y = ny;
            }

            int[] answer = new int[total];
            int index = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    answer[index++] = arr[i][j];
                }
            }
            return answer;
        }

        private boolean isNotAvailable(int x, int y, int n) {
            return x < 0 || x >= n || y < 0 || y >= n || arr[x][y] != 0;
        }
    }

}

/*
문제 분석
1. 정보
    - 정수 n이 매개변수로 주어진다.
    - 밑변의 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후 첫 행 부터 마지막 행 까지 모두 순서대로 합친 새로운 배열을 return
2. 목표
    - 밑변의 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후 첫 행 부터 마지막 행 까지 모두 순서대로 합친 새로운 배열을 return
3. 제약 조건
    - 1 <= n <= 1000

풀이
1. 아이디어
     - n * n 배열 선언
        - 1. 아래로 내려감
        - 2. 다 내려가면 오른쪽으로 감(0이 아닌 수가 나오기 전까지)
        - 3. 다 같으면 왼쪽 대각선으로 쭉 올라감(0이 아닌 수가 나오기 전까지)
        - 위 내용 반복 하여 다 채움
     - 해당 배열을 새 int[]배열에 담아 return
*/
