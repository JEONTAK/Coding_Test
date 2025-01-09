package BaekJoon.Gold4.스도쿠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2580 {

    static final int N = 9;
    static int[][] sudoku = new int[N][N];

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findZero(0, 0);
    }

    static void findZero(int x, int y){
        if(y == N){
            findZero(x + 1, 0);
            return;
        }

        if(x == N){
            StringBuilder sb = new StringBuilder();
            for (int[] row : sudoku) {
                for (int cur : row) {
                    sb.append(cur).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
            System.exit(0);
        }

        if (sudoku[x][y] == 0) {
            for (int i = 1; i <= N; i++) {
                if (isAvailable(x, y, i)) {
                    sudoku[x][y] = i;
                    findZero(x, y + 1);

                }
            }
            sudoku[x][y] = 0;
            return;
        }

        findZero(x, y + 1);
    }

    private static boolean isAvailable(int x, int y, int num) {
        for (int i = 0; i < N; i++) {
            if (sudoku[x][i] == num || sudoku[i][y] == num) {
                return false;
            }
        }

        int row = (x / 3) * 3;
        int col = (y / 3) * 3;

        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (sudoku[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BOJ2580.solution();
    }
}

/*
문제 분석

1. 정보
    - 스도쿠는 가로 세로 각각 9개씩 총 81개의 작은 칸으로 이루어짐
    - 게임 시작 전 일부 칸에는 1 ~ 9까지의 숫자 중 하나가 쓰여있음
    - 나머지 빈칸을 채우는 조건은 다음과 같음
        - 각각의 가로줄과 세로줄에는 1 ~ 9까지의 숫자가 하나씩만 존재해야함
        - 3 X 3 정사각형 안에도 1 ~ 9까지의 숫자가 하나씩만 존재해야함.

2. 목표
    - 모든 빈 칸이 채워진 스도쿠 판의 최종 모습을 아홉 줄에 걸쳐 한 줄에 9개씩 한 칸씩 띄워서 출력

3. 제약 조건
    - 시간 제한 : 292ms

풀이
1. 아이디어
    - 위에서 첫 줄 부터 0을 찾는다.
        - 해당 0에 가로줄을 기준으로 들어갈 수 있는 숫자를 찾는다.
            - 해당 숫자가 세로줄에 없거나,
            - 해당 칸을 포함하는 3 * 3 정사각형 안에 없다면
            - 다음 칸으로 이동
        - N - 1, N - 1 까지 완료하였다면, 모든 칸에 채운것 이므로, 결과를 출력하고 종료한다.

2. 알고리즘
    - findZero(0,0)부터 시작
        - 만약 sudoku[i][j] == 0 이라면,
            - 1 ~ 9까지 값을 넣어보고 들어 갈 수 있는지 확인
            - isAvailable(i,j,num)형식
            - 들어 갈 수 있다면, i, j + 1 부터 시작
        - 만약 j == 9 이라면
            - i + 1, 0 부터 진행
        - 만약 i == 9 라면
            - 모든 숫자를 집어 넣었으므로 출력후 종료

    - isAvailable(int i, int j, int num)
        - 먼저 가로부터 비교
            - sudoku[i][0] ~ [i][8] 까지 중 숫자가 겹친다면 false
        - 세로 비교
            - sudoku[0][j] ~ [8][j] 까지 중 숫자가 겹친다면 false
        - 3 * 3 비교
            - 현재 좌표 : i,j
            - (i / 3) * 3, (j / 3) * 3 이 해당 정사각형 시작 좌표
            - (i / 3) * 3 ~ (i / 3) * 3 + 3
                - (j / 3) * 3 ~ (j / 3) * 3 + 3 까지 중 숫자가 겹친다면 false
        - 위 조건 모두 통과하면 true
*/
