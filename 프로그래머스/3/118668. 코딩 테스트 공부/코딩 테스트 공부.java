import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = alp;
        int maxCop = cop;
        
        for(int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        if(maxAlp <= alp && maxCop <= cop) {
            return 0;
        }
        
        int[][] dp = new int[maxAlp + 31][maxCop + 31];
        for(int i = 0; i <= maxAlp + 30 ;i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[alp][cop] = 0;
        
        for(int i = alp; i <= maxAlp + 30; i++) {
            for(int j = cop; j <= maxCop + 30; j++) {
                if(dp[i][j] == Integer.MAX_VALUE) continue;
                
                //알고력 공부
                if(i + 1 <= maxAlp + 30) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                
                //코딩력 공부
                if(j + 1 <= maxCop + 30) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                
                //알고리즘 문제 풀이
                for(int[] p : problems) {
                    if(i >= p[0] && j >= p[1]) {
                        int next_alp = Math.min(i + p[2], maxAlp + 30);
                        int next_cop = Math.min(j + p[3], maxCop + 30);
                        dp[next_alp][next_cop] = Math.min(dp[next_alp][next_cop], dp[i][j] + p[4]);
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int a = maxAlp; a <= maxAlp + 30; a++) {
            for (int c = maxCop; c <= maxCop + 30; c++) {
                answer = Math.min(answer, dp[a][c]);
            }
        }
        
        return answer;
    }
}

/*
최단 시간에 모든 문제를 풀 수 있는 알고력, 코딩력을 만드는 문제.
1시간당 1이 오르는 공부를 하거나, 풀 수 있는 알고리즘을 풀어 능력을 올릴 수 있음.
1. 모든 문제를 풀수 있도록 도달하는 최대 알고력, 최대 코딩력을 구함.
만약 현재 알고력, 코딩력이 최대 알고력, 최대 코딩력보다 크다면, return

2. dp[i][j]로 구하기?
i -> 알고력
j -> 코딩력
i와 j는 각각 최대 알고력, 최대 코딩력으로 설정
dp[alp][cop] 부터 시작.
알고력 : alp ~ maxAlp, 코딩력 : cop ~ maxCop 까지
1. 알고력 공부하는 경우
2. 코딩력 공부하는 경우
3. 알고리즘 문제 푸는경우로 해서 최솟값을 구함.



*/