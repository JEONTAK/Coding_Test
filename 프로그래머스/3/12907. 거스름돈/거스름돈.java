class Solution {
    
    private final int MOD = 1_000_000_007;
    
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        for(int i = 0 ; i < money.length ; i++) {
            int curMoney = money[i];
            if(curMoney > n) continue;
            dp[curMoney]++;
            for(int j = 1 ; j <= n ; j++) {
                if(j - curMoney >= 0) {
                    dp[j] += (dp[j - curMoney]) % MOD;
                }
            }
        }
        
        return dp[n] % MOD;
    }
}

/*

DP 문제

DP[i] = i원을 만드는 경우의 수
i =  money의 종류 개수
j = money[i] -> 


*/