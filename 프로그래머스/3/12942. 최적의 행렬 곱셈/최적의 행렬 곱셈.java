import java.util.*;

class Solution {
    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        
        
        for(int len = 2; len <= n; len++) {
            for(int i = 0 ; i <= n - len; i++) {
                int j = i + len - 1;
                for(int k = i ; k < j ; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + matrix_sizes[i][0] * matrix_sizes[k][1] * matrix_sizes[j][1]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}

/*
행렬이 A(a * b), B(b * c) 일 경우
A * B -> a * b * c임.

A(a * b), B(b * c), C(c * d)일 경우
(A * B) * C -> a * b * c + a * c * d 번과
A * (B * C) -> b * c * d + a * b * d 번으로 나누어질 수 있음.
두 값중 작은 값으로 가능.

dp[][]로 계산

dp[i][j] -> i번쨰 ~ j번째까지 행렬을 곱하였을때의 최소 값.

행렬의 크기 -> 3 ~ 행렬의 개수
i -> 0 ~ 행렬의 개수 - 행렬의 크기 까지
j -> i + 행렬의 크기 까지
k -> i ~ j 까지
dp[i][j] = Math.min(dp[i][k] + dp[k][j] + i * j * k, dp[i][j]);

*/