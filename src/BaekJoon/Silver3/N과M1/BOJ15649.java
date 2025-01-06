package BaekJoon.Silver3.N과M1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649 {

    static int N, M;
    static boolean[] check;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        check = new boolean[N + 1];
        int[] result = new int[M];
        for (int i = 1; i <= N; i++) {
            check[i] = true;
            result[0] = i;
            recur(result, 1);
            check[i] = false;
        }
    }

    static void recur(int[] result, int cnt) {
        if (cnt == M) {
            for (int cur : result) {
                System.out.print(cur + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!check[i]) {
                check[i] = true;
                result[cnt] = i;
                recur(result, cnt + 1);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ15649.solution();
    }
}
/*
문제 분석

1. 정보
    - 자연수 N과 M이 주어졌을 때, 1부터 N까지 자연수 중에서 M개를 고른 수열
2. 목표
    - 위 수열을 중복 없이 모두 출력

3. 제약 조건
    - 1 <= M <= N <= 8

풀이

1. 알고리즘
    - 백트래킹
        - 백트래킹 방식을 사용해 1부터 방문하여 M개를 방문하면 출력하고 return

*/