package BaekJoon.Gold2.사전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1256 {

    static int N, M;
    static long K;
    static long[][] dp;
    static StringBuilder sb = new StringBuilder();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        dp = new long[N + 1][M + 1];
        compute(N, M, K);

        if(dictLength(N, M) < K){
            System.out.println(-1);
        }else{
            System.out.println(sb.toString());
        }
    }

    static void compute(int n, int m, long k) {
        if (n == 0) {
            for(int i = 0 ; i < m ; i++){
                sb.append("z");
            }
            return;
        }

        if(m == 0){
            for(int i = 0 ; i < n ; i++){
                sb.append("a");
            }
            return;
        }

        long len = dictLength(n - 1, m);
        if(k > len){
            sb.append("z");
            compute(n, m - 1, k - len);
        }
        else{
            sb.append("a");
            compute(n - 1, m, k);
        }
    }

    static long dictLength(int n, int m) {
        if(n == 0 || m == 0){
            return 1;
        }
        if(dp[n][m] != 0){
            return dp[n][m];
        }
        return dp[n][m] = Long.min(dictLength(n - 1, m) + dictLength(n, m - 1), 1_000_000_001);
    }

    public static void main(String[] args) throws IOException {
        BOJ1256.solution();
    }
}

//a 가 N개, z가 M개 있을 경우
//앞에 a 일 경우 -> a N - 1개, z M개 사용한 문자열의 개수
//앞이 z 일 경우 -> a N 개, z M - 1개 사용한 문자열의 개수
//이므로, dp[N][M] = dp[N - 1][M] + dp[N][M - 1];
//
//만약 a == 0 or z == 0 일경우 한가지만 쭉 채워주면 됨
//a로 시작하게 되었을때 만들 수 있는 문자열의 개수 구함 -> dicLength(n - 1, m) = len;
//만약 len < K 일경우 a로 시작하면 K번째 문자열 못만들음
//-> 따라서 z 로 시작해야함 -> compute(n, m - 1, K - len);
//K - len 해주는 이유 -> z로 시작하니까, a로 시작하는 문자열들의 개수를 빼고 넘겨주기
//len >= K 일경우
//a로 시작하게 되므로
//compute(n - 1, m, K)
