package BaekJoon.Gold1.로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4991 {

    static class Dust{
        int x;
        int y;
        int d;

        public Dust(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Dust(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static class Node{
        int e;
        int d;

        public Node(int e, int d) {
            this.e = e;
            this.d = d;
        }
    }

    static int W, H;
    static char[][] map;
    static boolean[] visited;
    static int remain, result, sIdx;
    static ArrayList<Dust> dust;
    static ArrayList<Node>[] list;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        while(W != 0 && H != 0){
            map = new char[H][W];
            dust = new ArrayList<>();
            result = Integer.MAX_VALUE;
            remain = 0;
            for(int i = 0 ; i < H ; i++){
                String temp = br.readLine();
                for(int j = 0 ; j < W ; j++){
                    map[i][j] = temp.charAt(j);
                    if(map[i][j] == 'o'){
                        dust.add(new Dust(i, j));
                        sIdx = dust.size() - 1;
                    } else if (map[i][j] == '*') {
                        dust.add(new Dust(i, j));
                        remain++;
                    }
                }
            }

            list = new ArrayList[dust.size()];
            for(int i = 0 ; i < dust.size(); i++){
                list[i] = new ArrayList<>();
            }
            boolean flag = true;
            for(int i = 0 ; i < dust.size() ; i++){
                for(int j = 0 ; j < dust.size() ; j++){
                    if(i != j){
                        int dist = getDist(dust.get(i), dust.get(j));
                        if (dist == -1) {
                            flag = false;
                            break;
                        }else{
                            list[i].add(new Node(j,dist));
                        }
                    }
                }
            }
            if(!flag){
                sb.append(-1 + "\n");
                st = new StringTokenizer(br.readLine());
                W = Integer.parseInt(st.nextToken());
                H = Integer.parseInt(st.nextToken());
                continue;
            }
            visited = new boolean[dust.size()];
            visited[sIdx] = true;
            compute(sIdx, 0, 0);


            sb.append(result == Integer.MAX_VALUE ? -1 : result).append("\n");
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb.toString());
    }

    private static int getDist(Dust start, Dust end) {
        int d = Integer.MAX_VALUE;
        Queue<Dust> q = new LinkedList<>();
        boolean[][] check = new boolean[H][W];
        q.add(new Dust(start.x, start.y, 0));
        check[start.x][start.y] = true;
        while(!q.isEmpty()){
            Dust cur = q.poll();
            if(cur.x == end.x && cur.y == end.y){
                d = Math.min(d, cur.d);
                continue;
            }
            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isAvailable(nx, ny) && map[nx][ny] != 'x' && !check[nx][ny]) {
                    check[nx][ny] = true;
                    q.add(new Dust(nx, ny, cur.d + 1));
                }
            }
        }
        return d == Integer.MAX_VALUE ? -1 : d;
    }

    private static void compute(int sIdx, int cnt, int d) {
        if (cnt == remain) {
            result = Math.min(result, d);
            return;
        }

        for (Node next : list[sIdx]) {
            if (!visited[next.e]) {
                visited[next.e] = true;
                compute(next.e, cnt + 1,d + next.d);
                visited[next.e] = false;
            }
        }
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < H && y >= 0 && y < W;
    }

    public static void main(String[] args) throws IOException {
        BOJ4991.solution();
    }
}

//모든 먼지 및 로봇 청소기의 위치 정보를 사용하여 그래프화 시키고 해당 그래프를 이용해 모든 점을 지난 최단거리를 구하도록 하였다.
//먼저 먼지 및 로봇 청소기의 위치 정보를 list 배열로 저장한다.
//먼지 및 로봇 청소기 list를 사용하여 본인을 제외한 나머지 먼지 및 로봋 청소기와의 거리를 재 그래프 형식의 list 배열로 저장한다.
//만약 가구에 막혀 거리를 잴 수 없다면 flag를 false처리해주고, flag가 false인 경우에 -1을 sb에 추가해주고 다음 W, H로 넘어간다.
//해당 배열을 사용하여 시작점은 로봇 청소기로 잡고, 모든 먼지에 도달할 때까지 의 거리를 구한후, 해당 거리들의 최솟값을 구한다.
//해당 값을 StringBuilder에 저장하여 0 0 이 들어오면 출력해준다.