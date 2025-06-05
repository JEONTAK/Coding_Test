import java.util.*;

class Solution {
    
    class Node {
        int x;
        int y;
        int cnt;
        
        public Node (int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[101][101];
        
        for(int[] rect : rectangle) {
            int sx = rect[0] * 2;
            int sy = rect[1] * 2;
            int ex = rect[2] * 2;
            int ey = rect[3] * 2;
            for(int i = sx; i <= ex; i++) {
                for(int j = sy; j <= ey; j++) {
                    map[i][j] = 1;
                }
            }
        }
        
        for(int[] rect : rectangle) {
            int sx = rect[0] * 2;
            int sy = rect[1] * 2;
            int ex = rect[2] * 2;
            int ey = rect[3] * 2;
            for(int i = sx + 1; i < ex; i++) {
                for(int j = sy + 1; j < ey; j++) {
                    map[i][j] = 0;
                }
            }
        }
        
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[101][101];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        queue.add(new Node(characterX * 2, characterY * 2, 0));
        visited[characterX * 2][characterY * 2] = true;
        
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            
            if(cur.x == itemX * 2 && cur.y == itemY * 2) {
                return cur.cnt / 2;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(isAvailable(nx,ny) && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny, cur.cnt + 1));
                }
            }
        }
        
        return 0;
    }
    
    private boolean isAvailable(int x, int y) {
        return 0 <= x && x <= 100 && 0 <= y && y <= 100;
    }
}

/*
좌표의 크기는 최대 50 * 50이므로, 2차원 배열을 만들어서 지도의 형태로 변환함
지도는 100 * 100으로 두배로 만들음
두배로 만들어야 거리 계산할때 정확해질 수 있음.
직사각형도 시작점과 끝점을 두배로 만들어 먼저 1로 채움
이후 테두리를 제외한 나머지 부분을 0으로 채움
해당 맵이 완성되면, 해당 맵에서 시작점부터 도착점까지 가는 거리를 구함.
도착점에 도달하면, 해당 위치까지 가는데의 거리 / 2로 거리를 구한 뒤 return



*/