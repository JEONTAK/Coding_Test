package BaekJoon.Gold3.양팔저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2629 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] weight = new int[N];
        int weightSum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            weightSum += weight[i];
        }

        boolean[][] dp = new boolean[N + 1][weightSum * 2 + 1];
        dp[0][weightSum] = true;

        for (int i = 1; i <= N; i++) {
            for (int j = -weightSum; j <= weightSum; j++) {
                if (dp[i - 1][j + weightSum]) {
                    dp[i][j + weightSum] = true; //추 사용 X
                    if(j + weightSum + weight[i - 1] <= weightSum * 2) dp[i][j + weightSum + weight[i - 1]] = true; //추를 구슬 반대편에

                    if(j + weightSum - weight[i - 1] >= 0) dp[i][j + weightSum - weight[i - 1]] = true; //추를 구슬 같은편에
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (cur > weightSum) {
                sb.append("N ");
            }else{
                sb.append(dp[N][cur + weightSum] ? "Y" : "N").append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ2629.solution();
    }
}

/*
문제 분석

1. 정보
    - 추의 무게와, 확인할 구슬들의 무게가 주어짐
    - 추와 구슬을 적절히 배치하여 균형을 이루면 구슬의 무게를 알 수 있음
    - 해당 문제에서는 추의 무게와, 구슬의 무게가 주어짐
    
2. 목표
    - 주어진 추를 사용하여, 구슬의 무게를 확인할 수 있으면 Y, 아니면 N을 출력

3. 제약 조건
    - 추의 개수 <= 30
    - 추의 무게 <= 500
    - 구슬의 개수 <= 7
    - 구슬의 무게 <= 40000

풀이
1. 알고리즘
  
    - DP 알고리즘
        - dp[i][j] : 구슬을 i번째까지 사용했을 때, 무게 j 가능 유무 판별
            - dp[0][0] = true
            - i : 0 ~ N까지
            - j : 0 ~ 구슬의 최대 합까지
            - dp[i][j + weight[i]] : 추를 구슬 반대편에 올릴 경우
            - dp[i][j - weight[i]] : 추를 구슬 같은편에 올릴 경우
            - dp[i][j] : 추를 사용하지 않을 경우
2. 탐색 과정

*/
