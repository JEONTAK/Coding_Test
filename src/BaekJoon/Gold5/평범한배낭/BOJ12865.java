package BaekJoon.Gold5.평범한배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] W = new int[N];
        int[] V = new int[N];
        int[] DP = new int[K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = K; j >= W[i]; j--) {
                DP[j] = Math.max(DP[j], DP[j - W[i]] + V[i]);
            }
        }

        System.out.println(DP[K]);
    }

    public static void main(String[] args) throws IOException {
        BOJ12865.solution();
    }
}

/*
문제 분석
1. 정보
    - N개의 물건
        - 각 물건
            - 무게 W
            - 가치 V
    - K만큼의 무게를 넣을 수 있는 배낭
    
2. 목표
    - 배낭에 넣을 수 있는 물건들의 가치합의 최댓값 출력

3. 제약 조건
    - N : 1 <= N <= 100
    - K : 1 <= K <= 100000
    - W : 1 <= W <= 100000
    - V : 0 <= V <= 1000

풀이
1. 알고리즘
    - DP(배낭 문제)
    - i개의 물건이 있다고 가정
        - i : 0 ~ N(물품 개수)
            - j : K ~ 무게[i]
                 - DP[j] : 배낭 무게가 j일때 물건 가치합의 최댓값
                 - DP[j] = Math.max(DP[j], DP[j - 무게[i]] + 가치[i])

*/
