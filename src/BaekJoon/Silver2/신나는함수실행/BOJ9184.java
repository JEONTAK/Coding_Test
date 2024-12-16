package BaekJoon.Silver2.신나는함수실행;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9184 {

    static int[][][] DP = new int[21][21][21];

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                for (int k = 0; k <= 20; k++) {
                    if(i == 0 || j == 0 || k == 0) {
                        DP[i][j][k] = 1;
                    }else{
                        DP[i][j][k] = -1;
                    }
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        while(true) {
            if(a == -1 && b == -1 && c == -1) {
                break;
            }
            sb.append("w(" + a + ", " + b + ", " + c + ") = ").append(compute(a,b,c)).append("\n");
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb.toString());
    }

    static int compute(int a, int b, int c) {
        if(a <= 0 || b <= 0 || c <= 0){
            return 1;
        }

        if(a > 20 || b > 20 || c > 20){
            return compute(20,20,20);
        }

        if (DP[a][b][c] != -1) {
            return DP[a][b][c];
        }

        DP[a][b][c] = 0;

        if(a < b && b < c){
            return DP[a][b][c] = compute(a, b, c - 1) + compute(a, b - 1, c - 1) - compute(a, b - 1, c);
        }

        return DP[a][b][c] = compute(a - 1, b, c) + compute(a - 1, b - 1, c) + compute(a - 1, b, c - 1) - compute(a - 1, b - 1, c - 1);
    }
    public static void main(String[] args) throws IOException {
        BOJ9184.solution();
    }
}
/*
문제 분석
1. 정보
    - 재귀함수 w(a,b,c)
        - a,b,c 중 하나라도 0보다 작거나 같으면 1 return
        - a,b,c 중 하나라도 20보다 크다면 w(20,20,20) return
        - a < b < c 이면, w(a,b,c-1) + w(a,b-1,c-1) - w(a,b-1,c) return
        - 나머지
            - w(a-1,b,c) + w(a-1,b-1,c) + w(a-1,b,c-1) - w(a-1,b-1,c-1) return
2. 목표
    - 세 정수 a,b,c가 주어졌을떄, w(a,b,c)를 출력

3. 제약 조건
    - 입력의 마지막은 -1,-1,-1로 나타남
    - 세 정수가 모두 -1인 경우는 마지막 제외하면 없음
풀이
1. 알고리즘
    - DP
        - DP[][][] 배열을 선언
            -DP[a][b][c] : w(a,b,c)의 결과 값
    - 재귀
        - 재귀 알고리즘 통해 DP 배열 업데이트 할 수 있도록 해줌

 */
