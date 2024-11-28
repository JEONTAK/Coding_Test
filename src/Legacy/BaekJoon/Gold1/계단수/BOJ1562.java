package BaekJoon.Gold1.계단수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1562 {

    static int N;
    static long[][][] dp;
    static final int MOD = 1_000_000_000;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N <= 9){
            System.out.println(0);
            return;
        }
        dp = new long[N + 1][11][1 << 10];
        for(int i = 1 ; i < 10 ; i++){
            dp[1][i][1<<i] = 1;
        }

        for(int i = 2 ; i <= N ; i++){
            for(int j = 0 ; j < 10 ; j++){
                for(int k = 0 ; k < 1024 ; k++){
                    int bit = k | (1 << j);
                    if (j == 0) {
                        dp[i][j][bit] += dp[i - 1][j + 1][k] % MOD;
                    } else if (j == 9) {
                        dp[i][j][bit] += dp[i - 1][j - 1][k] % MOD;
                    } else{
                        dp[i][j][bit] += dp[i - 1][j + 1][k] % MOD + dp[i - 1][j - 1][k] % MOD;
                    }
                    dp[i][j][bit] %= MOD;
                }
            }
        }

        long result = 0;
        for(int i = 0 ; i < 10 ; i++){
            result += dp[N][i][1023] % MOD;
            result %= MOD;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ1562.solution();
    }
}

//dp[i][j][k]
//i : i자리 숫자
//j : j로 끝나는 숫자
//k : k 마킹된 숫자
//여기서 k 는 10의 자리로 설정하여 9부터 0까지 해당 숫자가 들어가 있다면 해당 비트에 1을 해주는 것으로 취급해준다.
//따라서 k의 크기는 2^10 - 1 -> 1024 - 1 이 되게 된다.
//점화식은 dp[n][num][bit] += dp[n - 1][num + 1][bit] + dp[n - 1][num - 1][bit]이다
//간단히 설명하자면, n번째 자리 숫자가 num으로 끝나는 경우의 합은 -> n - 1번쨰 자리의 숫자가 num - 1로 끝나는 경우의 수 + num + 1로 끝나는 경우의 수를 더하면 될것이다.
//해당 과정을 다 거치고 나서 0부터 9까지 다 들어 있다면 k의 bit값은 1,111,111,111 이 되므로 -> 1023이 된다.
//결국 정답 값은 dp[n번째 자리]에 해당하는 배열중 k값이 1023인 값들만 다 더해주면 된다.