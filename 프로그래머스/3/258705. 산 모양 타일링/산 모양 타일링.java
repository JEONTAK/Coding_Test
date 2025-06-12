import java.util.*;

class Solution {
    
    private final int MOD = 10007;
    
    public int solution(int n, int[] tops) {
        int answer = 0;
        int[] dpShare = new int[n];
        int[] dpOnly = new int[n];
        
        //첫번쨰 top 존재할 경우
        if(tops[0] == 1) {
            dpShare[0] = 4;
            dpOnly[0] = 3;
        } else {
            dpShare[0] = 3;
            dpOnly[0] = 2;
        }
        
        for(int i = 1; i < n; i++) {
            
            if(tops[i] == 1) {
                dpShare[i] = (dpShare[i - 1] * 3 + dpOnly[i - 1]) % MOD;
                dpOnly[i] = (dpShare[i - 1] * 2 + dpOnly[i - 1]) % MOD;
            } else {
                dpShare[i] = (dpShare[i - 1] * 2 + dpOnly[i - 1]) % MOD;
                dpOnly[i] = (dpShare[i - 1] * 1 + dpOnly[i - 1]) % MOD;
            }
        }
        
        return dpShare[n - 1];
    }
}

/*

dp공유[i] = dp공유[i - 1] * 3 + dp제외[i - 1]
dp제외[i] = dp공유[i - 1] * 2 + dp제외[i - 1]
dp공유[i] = dp공유[i - 1] * 2 + dp제외[i - 1]
dp제외[i] = dp공유[i - 1] * 1 + dp제외[i - 1]

*/