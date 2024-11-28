package BaekJoon.Gold2.성냥개비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ3687 {

    static int T, N;
    static String min, max;
    static int[] small = {1, 7, 4, 2, 0, 8};
    static long[] dp = new long[101];

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        Arrays.fill(dp, Long.MAX_VALUE);
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;
        for(int i = 9 ; i <= 100 ; i++){
            for(int j = 2 ; j <= 7 ; j++){
                String temp = String.valueOf(dp[i - j]) + String.valueOf(small[j - 2]);
                dp[i] = Math.min(Long.parseLong(temp), dp[i]);

            }
        }

        while(T-- > 0){
            N = Integer.parseInt(br.readLine());
            min = String.valueOf(dp[N]);
            max = "";
            compute(N);
            sb.append(min + " " + max + "\n");
        }
        System.out.println(sb.toString());
    }

    static void compute(int n){
        int temp = n;
        if(temp % 2 == 0){
            max = getMax(n / 2);
        }else{
            max = "7" + getMax((n - 3) / 2);
        }
    }

    static String getMax(int n){
        StringBuilder b = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            b.append("1");
        }
        return b.toString();
    }

    public static void main(String[] args) throws IOException {
        BOJ3687.solution();
    }
}
