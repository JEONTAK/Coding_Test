package BaekJoon.Gold3.파일합치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11066 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] file = new int[K];
            int[] sum = new int[K + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                file[i] = Integer.parseInt(st.nextToken());
                sum[i + 1] = sum[i] + file[i];
            }

            int[][] dp = new int[K][K];

            for (int len = 2; len <= K; len++) {
                for (int i = 0; i <= K - len; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        int cost = dp[i][k] + dp[k + 1][j] + (sum[j + 1] - sum[i]);
                        dp[i][j] = Math.min(dp[i][j], cost);
                    }
                }
            }
            System.out.println(dp[0][K - 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ11066.solution();
    }
}

/*
문제 분석
1. 정보
    - 소설을 여러 장으로 나누어 씀
    - 각 장은 각각 다른 파일에 저장
    - 소설의 모든 장을 쓰고 나서, 각 장이 쓰여진 파일을 합쳐 최종적으로 완성본이 들어있는 한 개의 파일을 만듬
        - 이 과정에서
            - 두 개의 파일을 합쳐서 하나의 임시파일을 만듬
            - 임시파일과 하나의 파일을 합쳐서 또 다른 임시파일을 만듬
        - 두개의 파일을 합칠 때 필요한 비용 : 두 파일 크기의 합
        
2. 목표
    - 최종적인 한 개의 파일을 완성하는데 필요한 비용의 총 합 계산

3. 제약 조건
    - 소설을 구성하는 장의 수 K : 3 <= K <= 500
    - 파일의 크기 S : 1 <= S <= 10000

풀이
1. 알고리즘
    - DP 알고리즘
        - DP[i][j] : i번째 파일부터 j번째 파일까지 합치는데 필요한 최소 비용
            - i ~ j까지 파일을 합침
                - 범위를 i ~ k, k + 1 ~ j로 나눌 수 있음
                - 즉 k : i ~ j 까지 한번씩
                    - DP[i][j] = DP[i][k] + DP[k + 1][j] + 비용(i ~ j) 의 최솟값
        - 위 i, j를 정해주어야 함
            - 먼저 합칠 파일의 개수를 정해야 함 -> len : 2 ~ K
            - i : 시작점은 0 부터 K - len -> 0 ~ K - len 
            - j : 시작점 i부터 len개 만큼 갈때 까지 ->  i + len - 1
                - k : 위 i, j가 정해지면 k는 i ~ j 까지 돌면서 dp 값 계산
    - 누적합 사용( 비용(i ~ j)의 계산 쉽게 하기 위해)
        - sum[i] : i번째 파일까지의 비용 합
        - sum[j] - sum[i] : i번째 부터 j - 1번째 까지의 파일 비용의 합
 */