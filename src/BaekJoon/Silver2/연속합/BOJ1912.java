package BaekJoon.Silver2.연속합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[N];
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int current_sum = seq[0];
        int max_sum = seq[0];

        for (int i = 1; i < N; i++) {
            current_sum = Math.max(current_sum + seq[i], seq[i]);
            max_sum = Math.max(current_sum, max_sum);
        }

        System.out.println(max_sum);
    }

    public static void main(String[] args) throws IOException {
        BOJ1912.solution();
    }
}

/*
문제 분석
1. 정보
    - N개의 정수로 이루어진 임의의 수열
        - 수열에서 연속된 몇 개의 수를 선택
        - 단, 수는 한 개 이상 선택해야 함.
2. 목표
    - 연속된 몇 개의 수를 선택하여 구할 수 있는 합 중 가낭 큰 합 구하여 출력

3. 제약 조건
    - N : 1 <= N <= 100000
    - 수 : -1000 <= 수 <= 1000

풀이
1. 알고리즘
    - 카데인
        - seq[i] : i번째 수열의 수
        - 현재 연속된 부분합 : current_sum
            - 현재 원소를 포함한 연속 부분합을 계산
            - current_sum = max(현재 수, current_sum + 현재 수)
            - 즉, 새로 시작하는 값과, 현재 수를 더하는 값을 비교하여 더 큰 것으로 업데이트
        - 최대 부분합 : max_sum
            - 현재까지의 최대 부분 합을 저장.
            - max_sum = Math.max(max_sum, current_sum);

*/