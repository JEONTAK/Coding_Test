package BaekJoon.Gold2.원판돌리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17822 {

    static class Node{
        int x;
        int y;
        int v;

        public Node(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }

    static int N, M, T;
    static int[][] map;
    static int[] index;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean flag;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        index = new int[N];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0 ; i < T;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int j = 1;
            flag = false;
            while (j * x - 1 < N) {
                if(d == 0) {
                    index[j * x - 1] = (index[j * x - 1] - k + M) % M;
                }else{
                    index[j * x - 1] = (index[j * x - 1] + k) % M;
                }
                j++;
            }
            set();
            visited = new boolean[N][M];
            for(int m = 0 ; m < N ; m++){
                for (int l = 0; l < M; l++) {
                    if (!visited[m][l] && map[m][l] != 0) {
                        compute(m,l);
                    }
                }
            }
            if(!flag){
                getAvg();
            }
        }
        int result = 0;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                result += map[i][j];
            }
        }
        System.out.println(result);
    }

    static void set(){
        int[][] temp = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                temp[i][j] = map[i][j];
            }
        }
        for(int i = 0 ; i < N ; i++){
            for(int j = 0, k = index[i] ; j < M ; j++){
                map[i][j] = temp[i][k];
                k = (k + 1) % M;
            }
        }
        Arrays.fill(index, 0);
    }

    static void getAvg() {
        int sum = 0;
        int cnt = 0;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] != 0){
                    sum += map[i][j];
                    cnt++;
                }
            }
        }
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] != 0){
                    if (map[i][j] * cnt > sum) {
                        map[i][j]--;
                    } else if (map[i][j] * cnt < sum) {
                        map[i][j]++;
                    }
                }
            }
        }
    }

    static void compute(int x, int y) {
        int cnt = 0;
        Queue<Node> q = new LinkedList<>();
        Queue<Node> change = new LinkedList<>();
        q.add(new Node(x, y, map[x][y]));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            change.add(cur);
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = (cur.y + dy[i] + M) % M;
                if (nx >= 0 && nx < N && !visited[nx][ny] && map[nx][ny] == cur.v) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, cur.v));
                }
            }
        }
        if(cnt >= 2){
            flag = true;
            while (!change.isEmpty()) {
                Node cur = change.poll();
                map[cur.x][cur.y] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ17822.solution();
    }
}
