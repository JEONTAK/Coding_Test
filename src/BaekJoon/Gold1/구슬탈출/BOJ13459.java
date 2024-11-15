package BaekJoon.Gold1.구슬탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13459 {

    static class Marble{
        int x;
        int y;
        int dist;

        public Marble(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static class Node{

        int rx;
        int ry;
        int bx;
        int by;

        public Node(int rx, int ry, int bx, int by) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
        }
    }

    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][][][]visited;
    static Node glass = new Node(0, 0, 0, 0);
    static Node cur;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for(int i = 0 ; i < N ; i++){
            String temp = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'B') {
                    glass.bx = i;
                    glass.by = j;
                }else if(map[i][j] == 'R'){
                    glass.rx = i;
                    glass.ry = j;
                }
            }
        }

        Queue<Node> q = new LinkedList<>();
        visited = new boolean[N][M][N][M];
        q.add(glass);
        visited[glass.rx][glass.ry][glass.bx][glass.by] = true;
        int cnt = 1;
        while(!q.isEmpty()){
            for(int i = 0 ; i < q.size() ; i++){
                Node node = q.poll();
                for(int d = 0 ; d < 4 ; d++){
                    Marble red = move(node.rx, node.ry, 0, d);
                    Marble blue = move(node.bx, node.by,0, d);

                    if(map[blue.x][blue.y] == 'O') continue;
                    if(map[red.x][red.y] == 'O'){
                        System.out.println(1);
                        System.exit(0);
                    }

                    if(red.x == blue.x && red.y == blue.y){
                        if (red.dist > blue.dist) {
                            red.x -= dx[d];
                            red.y -= dy[d];
                        }else{
                            blue.x -= dx[d];
                            blue.y -= dy[d];
                        }
                    }

                    if(!visited[red.x][red.y][blue.x][blue.y]){
                        visited[red.x][red.y][blue.x][blue.y] = true;
                        q.add(new Node(red.x, red.y, blue.x, blue.y));
                    }
                }
            }
            if(++cnt > 10)break;
        }
        System.out.println(0);
    }

    private static Marble move(int x, int y, int dist, int d) {
        int nx = x;
        int ny = y;
        while (map[nx][ny] != 'O' && map[nx + dx[d]][ny + dy[d]] != '#') {
            nx += dx[d];
            ny += dy[d];
            dist++;
        }

        return new Marble(nx, ny, dist);
    }


    public static void main(String[] args) throws IOException {
        BOJ13459.solution();
    }
}
