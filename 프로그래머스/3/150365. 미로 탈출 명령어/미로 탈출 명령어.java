import java.util.*;

class Solution {
    
    class Node {
        int x;
        int y;
        String command;
        int cnt;
        
        public Node (int x, int y, String command, int cnt) {
            this.x = x;
            this.y = y;
            this.command = command;
            this.cnt = cnt;
        }
        
    }
    
    //아래, 왼, 오, 위 순
    private int[] dx = {1, 0, 0, -1};
    private int[] dy = {0, -1, 1, 0};
    private char[] cmd = {'d', 'l', 'r', 'u'};
    private String result = "";
    private int n, m, endX, endY, k;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        //만약 두 거리가 홀수 이고 K가 짝수이면 절대 불가능
        //만약 두 거리가 짝수 이고 K가 홀수이면 절대 불가능
        //-> 두 거리 + K의 합이 짝수가 아니면 절대 불가능
        int range = Math.abs(x - r) + Math.abs(y - c);
        if((range + k) % 2 != 0 || range > k) {
            return "impossible";
        }
        
        this.n = n;
        this.m = m;
        this.endX = r - 1;
        this.endY = c - 1;
        this.k = k;
        
        for(int i = 0 ; i < k ; i++) {
            result += "z";
        }

        compute(new Node(x - 1, y - 1, "", 0));
        
        return result;
    }
    
    public void compute(Node cur) {
        if (result != null && cur.command.compareTo(result) >= 0) {
            return;
        }
        
        int range = Math.abs(cur.x - endX) + Math.abs(cur.y - endY);
        int remain = k - cur.cnt;
        if(remain < range || (remain - range) % 2 != 0) {
            return;
        }
        
        if (cur.x == endX && cur.y == endY && cur.cnt == k) {
            result = cur.command;
            return;
        }
        
        if (cur.cnt >= k) {
            return;
        }
        
        for(int i = 0 ; i < 4 ; i++) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];
            
            if(isAvailable(nx, ny)) {
                compute(new Node(nx, ny, cur.command + cmd[i], cur.cnt + 1));
            }
            
            if (result != null && result.equals(cur.command + cmd[i])) {
                    return;
            }
        }
        
    }
    
    public boolean isAvailable(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}

/*
BFS + Queue를 사용해서 풀이
-> 시간 초과 발생
k는 최대 2500이기 때문

DFS를 사용하여 백트래킹 방식??
-> 시간 초과 발생

만약 최단 거리를 구한다면 2의 배수 형식으로 남을 것
해당 2의 배수를 어떻게 해서 사전순으로 가장 빠른 명령어를 만들 수 있을 지 생각

*/