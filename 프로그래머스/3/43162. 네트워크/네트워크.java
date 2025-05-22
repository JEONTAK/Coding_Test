import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                setNetwork(i, computers, visited, n);
                answer++;
            }
        }
        
        return answer;
    }
    
    private void setNetwork(int start, int[][] computers, boolean[] visited, int n) {
        visited[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            for(int next = 0; next < n; next++) {
                if(cur != next && !visited[next] && computers[cur][next] == 1) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
    }
}

/*
네트워크가 직접 연결되어 있지 않고 간접적으로 연결 되어 있어도 하나의 네트워크로 간주
-> boolean[] visited 배열을 만들어서 모든 컴퓨터에 방문이 완료될때까지 수행
하나의 네트워크 완성될때 마다 1씩 증가

i와 j가 연결 되어 있다 -> computers[i][j] == 1

0 ~ n 까지 visited 를 체크해서 false면 네트워크 구성
네트워크는 Queue를 사용해서 구성
큐가 비면 answer++ 하고 다시 visited를 체크

모든 visited 완료되면 
answer return
*/