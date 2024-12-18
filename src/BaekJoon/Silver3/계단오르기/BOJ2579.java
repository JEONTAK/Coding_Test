package BaekJoon.Silver3.계단오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N + 1];
        int[] DP = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            S[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(S[1]);
            return;
        }

        DP[1] = S[1];
        DP[2] = S[1] + S[2];
        for (int i = 3; i <= N; i++) {
            DP[i] = Math.max(DP[i - 2], DP[i - 3] + S[i - 1]) + S[i];
        }
        System.out.println(DP[N]);
    }

    public static void main(String[] args) throws IOException {
        BOJ2579.solution();
    }
}

/*
문제 분석
1. 정보
    - 계단 아래 시작점부터 꼭대기 도착점까지 가는 게임
    - 밟은 계단의 점수의 합이 최종 점수
    - 계단 오르는 규칙
        - 한 번에 한 계단 or 두 계단 씩 오를 수 있음
        - 연속된 세 개의 계단을 모두 밟으면 안된다. (시작점은 포함 X)
        - 마지막 도착 계단은 반드시 밟아야 함

2. 목표
    - 계단 오르기 게임에서 얻을 수 있는 총 점수의 최댓값 출력

3. 제약 조건
    - 계단의 개수 : 1 <= N <= 300
    - 계단에 써있는 점수 : 1 <= S <= 10000

풀이
1. 알고리즘
    - DP
        - dp[i] : i번째 계단을 밟았을때 총 점수의 최대 값

2. 탐색 과정
    - DP
        - 두 가지 경우의 수
            - 2칸 : dp[i - 2] + s[i]
            - 1칸 : dp[i - 1] + s[i]
        - 근데 1칸씩 갈 경우, 3칸 연속 밟을 수는 없음.
            - 1칸을 사용하는 겅우의 수
                - 2칸 + 1칸
                - 1칸 + 2칸
                    - 하지만 순서의 차이만 있고, 1칸이동 전은 반드시 2칸이 나오기 때문에, 두개의 결과 값 차이는 없다.
                - 따라서 2칸 + 1칸 이동을 기준으로 점화식을 구성함
            - 2칸 : dp[i - 2] + s[i]
            - 2칸 + 1칸 : dp[i - 3] + s[i - 1] + s[i]
        - 따라서 점화식은 다음과 같이 구성 됨
            - DP[i] = Math.max(DP[i - 2], DP[i - 3] + S[i - 1]) + S[i];
*/
