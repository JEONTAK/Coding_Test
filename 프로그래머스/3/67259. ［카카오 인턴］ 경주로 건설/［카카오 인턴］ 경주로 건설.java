import java.util.*;

class Solution {
    
    class Location implements Comparable<Location>{
        int x;
        int y;
        int dir;
        int cost;
        
        public Location (int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Location o) {
            return this.cost - o.cost;
        }
    }
    
    //상 하 좌 우
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] board) {
        int n = board.length;
        int[][][] cost = new int[n][n][4];
        for(int i = 0; i < cost.length; i++) {
            for(int j = 0; j < cost[i].length; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }
        
        cost[0][0][1] = 0;
        cost[0][0][3] = 0;
        
        PriorityQueue<Location> queue = new PriorityQueue<>();

        queue.add(new Location(0, 0, 1, 0));
        queue.add(new Location(0, 0, 3, 0));
        
        while(!queue.isEmpty()) {
            Location cur = queue.poll();
            
            if(cur.cost > cost[cur.x][cur.y][cur.dir]) continue;
            
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(isAvailable(nx, ny, n) && board[nx][ny] != 1) {
                    int newCost =  cur.cost + 100;
                    if(cur.dir != i) {
                        newCost += 500;
                    }
                    
                    if(cost[nx][ny][i] > newCost) {
                        cost[nx][ny][i] = newCost;
                        queue.add(new Location(nx, ny, i, newCost));
                    }
                }
            }
        }
        
        int minCost = Integer.MAX_VALUE;
        
        for(int i = 0; i < 4; i++) {
            minCost = Math.min(minCost, cost[n - 1][n - 1][i]);
        }   
        
        return minCost;
    }
    
    
    private boolean isAvailable (int nx, int ny, int n) {
        return 0 <= nx && nx < n && 0 <= ny && ny < n;
    }
}

/*

재귀 사용
해당 칸에서 상 하 좌 우로 갈 수 있음.

cost[][][]를 생성해 해당 위치와 방향에 대하여 도로를 만드는데 필요한 최소 비용을 저장
PQ를 사용하여 최소 cost 값부터 계산

*/