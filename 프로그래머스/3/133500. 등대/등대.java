import java.util.*;

class Solution {
    
    List<Integer>[] list;
    int[][] dp;
    public int solution(int n, int[][] lighthouse) {
        list = new ArrayList[n + 1];
        dp = new int[n + 1][2];
        
        for(int i = 1 ; i <= n ; i++) { 
            list[i] = new ArrayList<>();
        }
        
        for(int[] light : lighthouse) {
            int s = light[0];
            int e = light[1];
            list[s].add(e);
            list[e].add(s);
        }
        compute(1, 0);
        
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    public void compute(int child, int parent) {
        dp[child][0] = 0;
        dp[child][1] = 1;
        
        for(int next : list[child]) {
            if(next == parent) continue;
            
            compute(next, child);
            dp[child][0] += dp[next][1];
            dp[child][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }
    
}

/*
그래프 만들기

가장 많이 연결 되어 있는 순으로 정렬
1. 해당 부분 켰을 경우,
2. 해당 부분 껏을 경우로 나누어 계산

*/