package BaekJoon.Silver3.파도반수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9461 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long[] dp = new long[101];
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;
        for (int i = 6; i <= 100; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ9461.solution();
    }
}

/*
문제 분석
1. 정보
    - 삼각형이 나선 모양으로 놓여짐
        - 첫 삼각형은 정삼각형이고, 변의 길이는 1.
        - 나선에서 가장 긴 변의 길이를 k라 했을때, 해당 변에 k인 길이의 정삼각형을 추가
        - 해당 수열을 P(N)이라 함.

2. 목표
    - N이 주어졌을때, P(N)을 출력
    
풀이
1. 알고리즘
    - DP 사용
        - 해당 방식에는 규칙이 있음.
            - N번째의 정삼각형의 변의 길이는 N - 1번째의 정삼각형 길이 + N - 5번째 정삼각형의 길이와 같음.
            - 위 규칙을 사용해 DP 배열을 만들어 출력하면 됨
            - dp[n] = dp[n - 1] + dp[n - 5];

2. 주의 할 점
    - dp 배열을 long으로 선언해야 한다.
        - int의 범위를 넘어가기 때문!
 */