package BaekJoon.Silver1.포도주시식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2156 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] P = new int[N + 1];
        int[] DP = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            P[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(P[1]);
            return;
        }
        DP[1] = P[1];
        DP[2] = P[1] + P[2];

        for(int i = 3 ; i <= N ; i++) {
            DP[i] = Math.max(Math.max(DP[i - 2], DP[i - 3] + P[i - 1]) + P[i], DP[i - 1]);
        }

        System.out.println(DP[N]);
    }

    public static void main(String[] args) throws IOException {
        BOJ2156.solution();
    }
}

/*
문제 분석
1. 정보
    - 다양한 포도주가 들어있는 포도주 잔이 일렬로 놓여 있다.
    - 포도주를 시식하는데, 두가지 규칙이 존재
        - 포도주 잔을 선택 시, 해당 포도주는 모두 마시고 원래 위치에 다시 놓아야 함
        - 연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
    - 각 포도주 잔에는 특정 양이 들어 있음.
2. 목표
    - 최대한 많은 양의 포도주를 마셨을때의 양의 최댓값을 출력

3. 제약 조건
    - N : 1 <= N <= 10000
    - 포도주의 양 P : 0 <= P <= 1000

풀이
1. 알고리즘
    - DP 사용
        - DP[i] : i번째 포도주를 마셨을 때, 마신 포도주 양의 최댓값

2. 탐색 과정
    - DP
        - P[i] : i번쨰 포도주의 양
        - DP[i] : i번째 포도주를 마셨을 때, 마신 포도주 양의 최댓값
            - i : 0 ~ N
                - 3잔을 연속으로는 마시는 것이 불가능
                    - 현재 칸의 잔을 먹지 않음
                        - DP[i - 1];
                    - 2칸 건너서 먹는 경우(ex 0 2 먹음)
                        - DP[i - 2] + P[i]
                    - 2칸 건너서 먹고 1칸 건너서 먹는 경우(ex 0 2 3 먹음)
                        - DP[i - 3] + DP[i - 1] + P[i]
                - DP[i] = Math.max(Dp[i - 1], Math.max(DP[i - 2], DP[i - 3] + P[i - 1]) + P[i]);
        - i를 N까지 구한 뒤 DP[N] 출력
*/