package BaekJoon.Gold4.팰린드롬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10942 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[N + 1];
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = 1;
            if(seq[i] == seq[i - 1])dp[i - 1][i] = 1;
        }

        for (int i = 2; i < N; i++) {
            for (int j = 1; j <= N - i; j++) {
                if (seq[j] == seq[j + i] && dp[j + 1][j + i - 1] == 1) {
                    dp[j][j + i] = 1;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ10942.solution();
    }
}

/*
문제 분석

1. 정보
    - 홍준이가 자연수 N개를 칠판에 적는다.
    - 이후 명우에게 질문을 총 M번한다.
        - 각 질문은 두 정수 S, E로 나타낸다.
        - S번째 수부터 E번째 까지 수가 팰린드롬을 이루는지를 물어보는 뜻
        - 해당 질문에 대해 맞다 or 아니다 대답해야한다.
        - 맞다는 1, 아니다는 0으로 대답

2. 목표
    - M개의 질문에 대한 답을 출력한다.

3. 제약 조건
    - 수열의 크기 N : 1 <= N <= 2000
    - 질문의 개수 M : 1 <= M <= 1000000
    - 수열의 값 <= 100000

풀이

1. 알고리즘
    - DP 알고리즘
        - DP[i][j] : i번째 수부터 j번째 수 까지가 팰린드롬인지 아닌지 저장
            - 1자리수는 반드시 팰린드롬이므로, dp[i][i] = 1로 미리 초기화
            - 2자리수에 대한 팰린드롬을 미리 계산하기 위해 배열 크기를 N + 1로 설정
                - seq[i]와 seq[i - 1]을 비교해 두 값이 같다면 팰린드롬
            - 수열의 길이 : 2 ~ N -> i : 2 ~ N - 1
            - 시작 지점 j : 1 ~ N - i
            - 만약 수열의 양 끝 j, j + i의 값이 같고, j + 1 ~ j + i - 1 까지가 팰린드롬이라면, 해당 수도 팰린드롬

 */
