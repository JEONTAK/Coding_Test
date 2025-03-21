package BaekJoon.Silver3.N과M3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15651 {

    static int N, M;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = new int[M];

        for (int i = 1; i <= N; i++) {
            result[0] = i;
            recur(1);
        }
        System.out.println(sb.toString());
    }

    static void recur(int cnt){
        if(cnt == M){
            for (int cur : result) {
                sb.append(cur).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            result[cnt] = i;
            recur(cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ15651.solution();
    }
}

/*
문제 분석

1. 정보
    - 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 수열
        - 1부터 N까지 자연수 중에서 M개를 고른 수열
        - 같은 수를 여러 번 골라도 된다.
2. 목표
    - 위 수열을 중복 없이 모두 출력

3. 제약 조건
    - 1 <= M <= N <= 7
    - 수열은 사전 순으로 증가하는 순서로 출력해야 함.

풀이

1. 알고리즘
    - 백트래킹
        - 1 ~ N 부터 시작하는 백트래킹 수행
            - for(1 ~ N)에서
                - 배열 해당 순서에 저장하고 다음 백트래킹으로 넘어감
            - 만약 M번 돌았다면 배열 출력 후 return
*/
