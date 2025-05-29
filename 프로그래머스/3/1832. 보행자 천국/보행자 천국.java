class Solution {
    int MOD = 20170805;
    
    public int solution(int m, int n, int[][] cityMap) {
        //0 = 위, 1 = 왼쪽에서 오는 경우
        int[][][] dp = new int[m + 1][n + 1][2];
        dp[0][0][0] = 1;
        
        //첫 행 초기화
        for(int i = 1 ; i < n ; i++) {
            if(cityMap[0][i] == 1) continue;
            if(cityMap[0][i - 1] == 1) continue;
            if(cityMap[0][i - 1] == 2) {
                dp[0][i][1] = dp[0][i - 1][1] % MOD;
            } else {
                dp[0][i][1] = (dp[0][i - 1][0] + dp[0][i - 1][1]) % MOD;
            }
        }
        
        // 첫 열 초기화
        for (int i = 1; i < m; i++) {
            if (cityMap[i][0] == 1) continue;
            if (cityMap[i - 1][0] == 1) continue;
            if (cityMap[i - 1][0] == 2) {
                dp[i][0][0] = dp[i-1][0][0] % MOD;
            } else {
                dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1]) % MOD;
            }
        }
        
        // DP 계산
        for(int i = 1 ; i < m ; i++) {
            for(int j = 1 ; j < n ; j++) {
                if(cityMap[i][j] == 1) continue;
                
                //위에서 오는 경우
                if(cityMap[i - 1][j] != 1) {
                    if(cityMap[i - 1][j] == 2) {
                        dp[i][j][0] = dp[i - 1][j][0] % MOD;
                    } else {
                        dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                    }
                }
                
                if(cityMap[i][j - 1] != 1) {
                    if(cityMap[i][j - 1] == 2) {
                        dp[i][j][1] = dp[i][j - 1][1] % MOD;
                    } else {
                        dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                    }
                }
            }
        }
        
        int answer = (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
        return answer;
    }
}

/*
dp사용해야할듯?
오른쪽 또는 아래쪽으로만 이동 가능.
dp[i][j][dir]로 만들기


*/