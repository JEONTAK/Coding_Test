package BaekJoon.Gold4.NQueen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9663 {

    static int N;
    static int[] queen;
    static int result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queen = new int[N];

        findPlace(0);
        System.out.println(result);
    }

    static void findPlace(int cnt) {
        if (cnt == N) {
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            queen[cnt] = i;

            if (isAvailable(cnt)) {
                findPlace(cnt + 1);
            }
        }
    }

    static boolean isAvailable(int cnt) {
        for (int i = 0; i < cnt; i++) {
            if (queen[cnt] == queen[i] || Math.abs(cnt - i) == Math.abs(queen[cnt] - queen[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BOJ9663.solution();
    }
}
/*
문제 분석

1. 정보
    - 크기가 N * N인 체스판에 퀸 N개를 서로 공격할 수 없게 놓는다.
2. 목표
    - 퀸 N개를 놓는 방법의 수를 출력
3. 제약 조건
    - 1 <= N < 15

풀이
1. 알고리즘
    - 주요 아이디어
        - 퀸은 가로, 세로, 대각선으로 이동 가능
        - 따라서 가로를 기준으로 하나의 가로에는 하나의 퀸만 들어갈 수 있음
        - int[] 배열을 만들어 i번째 가로에 넣을 퀸의 세로 좌표 값을 저장
            - 여기서 만약 특정 세로 좌표에 퀸을 놓으려 할때, 해당 좌표를 가진 퀸이 있다면 놓을 수 없음
            - 또한, 대각선으로 퀸이 만나는 경우에도 놓을 수 없음
            - 위 조건들을 다 피한다면, 퀸을 놓을 수 있음
        - 위 int 배열을 가로 0 부터 가로 N - 1까지 모두 놓으면 경우의 수 1개를 추가
    - 백트래킹
        - findPlace(int cnt)로 메서드 생성
        - 만약 cnt == N 이라면, 모든 퀸을 채운것이므로 result++해줌
        - 0 ~ N - 1까지
            - 현재 퀸의 세로 좌표를 설정하고, 해당 세로 좌표에 퀸을 놓을 수 있는지 체크
            - isAvailable(int cnt)로 체크
                - 현재 cnt 이전까지 퀸을 놓았으므로, 0 ~ cnt - 1 까지 for문 수행
                    - 만약 세로 좌표가 겹치거나 : queen[cnt] == queen[i];
                    - 대각선방향에 퀸이 있거나
                        - 대각선 방향 -> 가로의 차이와 세로의 차이가 같다면, 해당 퀸은 대각선에 있다고 할 수 있음.
                        - 따라서 Math.abs(queen[cnt] - queen[i]) == Math.abs(cnt - i)
                    - 위 두가지 조건 중 하나라도 충족 한다면, 해당 좌표엔 놓을 수 없으므로, false return
                - 모든 이전 퀸과 좌표가 겹치지 않을 경우, true return

*/
