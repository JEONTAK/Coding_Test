package BaekJoon.Silver3._1로만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1463 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] DP = new int[N + 1];
        Arrays.fill(DP, Integer.MAX_VALUE);
        DP[N] = 0;
        for (int i = N; i >= 1; i--) {
            if (i % 3 == 0) {
                DP[i / 3] = Math.min(DP[i / 3], DP[i] + 1);
            }
            if (i % 2 == 0) {
                DP[i / 2] = Math.min(DP[i / 2], DP[i] + 1);
            }
            DP[i - 1] = Math.min(DP[i - 1], DP[i] + 1);
        }
        System.out.println(DP[1]);
    }

    public static void main(String[] args) throws IOException {
        BOJ1463.solution();
    }
}

/*
문제 분석
1. 정보
    - 정수 X에 사용할 수 있는 연산
        - X가 3으로 나누어 떨어지면, 3으로 나눔
        - X가 2로 나누어 떨어지면, 2로 나눔
        - 1을 뻄
        
2. 목표
    - 정수 N이 주어졌을 때, 위 연산을 적절히 사용해 1을 만드는 연산 횟수의 최솟값 출력

3. 제약 조건
    - N : 1 <= N <= 10^6

풀이
1. 알고리즘
    - DP
        - DP[i] : 숫자 i를 만드는데 필요한 연산 횟수의 최솟값
        - 위 3가지 연산 사용해 DP[1]값 업데이트
        - DP 배열을 Integer.MAX_VALUE로 초기화

2. 탐색 과정
    - DP
        - DP[i] : 숫자 i를 만드는데 필요한 연산 횟수의 최솟값
        - 만약 i % 3 == 0
            - DP[i / 3] = Math.min(DP[i / 3], DP[i] + 1)
        - 만약 i % 2 == 0
            - DP[i / 2] = Math.min(DP[i / 2], DP[i] + 1)
        - DP[i - 1] = Math.min(DP[i - 1] , DP[i] + 1)
    - DP[1]의 값을 출력
*/
