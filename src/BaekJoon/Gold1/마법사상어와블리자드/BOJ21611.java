package BaekJoon.Gold1.마법사상어와블리자드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ21611 {

    static class Ball{
        int type;
        int cnt;

        public Ball(int type, int cnt) {
            this.type = type;
            this.cnt = cnt;
        }
    }
    static class Node{
        int x;
        int y;
        int idx;

        public Node(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }

    static int N, M;
    static int[][][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] delta = {{0,0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int sx, sy;
    static Node[] list;
    static boolean[][] visited;
    static int ball[] = new int[4];


    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int [N][N][2];
        sx = N / 2;
        sy = N / 2;
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j][1] = Integer.parseInt(st.nextToken());
            }
        }
        list = new Node[N * N];
        visited = new boolean[N][N];
        setList();

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            //d -> 1 : (-1,0) / 2 : (1,0) / 3 : (0,-1) / 4 : (0,1)
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            for(int j = 1 ; j <= s ; j++){
                int nx = (N / 2) + delta[d][0] * j;
                int ny = (N / 2) + delta[d][1] * j;
                list[map[nx][ny][0]] = new Node(nx, ny, -1);
                map[nx][ny][1] = -1;
            }
            removeNull();
            compute();
            makeNewMap();
        }
        int result = 0;
        for(int i = 1 ; i <= 3 ; i++){
            result += i * ball[i];
        }
        System.out.println(result);
    }

    static void removeNull(){
        for(int j = 0 ; j < N * N; j++){
            if (list[j].idx == -1) {
                for(int k = j ; k < N * N - 1 ; k++){
                    map[list[k].x][list[k].y][1] = list[k].idx = list[k + 1].idx;
                }
                list[N * N - 1].idx = 0;
                map[list[N * N - 1].x][list[N * N - 1].y][1] = 0;
                j--;
            }
        }
    }

    static void makeNewMap(){
        int idx = 1;
        int curType = list[idx++].idx;
        int curCnt = 1;
        ArrayList<Ball> ballList = new ArrayList<Ball>();
        while (true) {
            if (idx >= N * N || curType == 0) {
                break;
            }
            if (list[idx].idx == curType) {
                curCnt++;
                idx++;
            }else{
                ballList.add(new Ball(curType, curCnt));
                curCnt = 1;
                curType = list[idx++].idx;
            }
        }
        idx = 1;
        for (Ball ball : ballList) {
            if(idx >= N * N)break;
            list[idx].idx = ball.cnt;
            map[list[idx].x][list[idx].y][1] = ball.cnt;
            idx++;
            list[idx].idx = ball.type;
            map[list[idx].x][list[idx].y][1] = ball.type;
            idx++;
        }
    }

    static void compute(){
        boolean flag = false;
        int idx = 1;
        int curType = list[idx++].idx;
        int curCnt = 1;
        while (true) {
            if (idx >= N * N || curType == 0) {
                if (flag) {
                    removeNull();
                    idx = 1;
                    curType = list[idx++].idx;
                    curCnt = 1;
                    flag = false;
                }else{
                    break;
                }
            }
            if (list[idx].idx == curType) {
                curCnt++;
                idx++;
            }else{
                if (curCnt >= 4) {
                    flag = true;
                    ball[curType]+= curCnt;
                    for(int i = idx - 1 ; i >= idx - curCnt ; i--){
                        list[i].idx = -1;
                    }
                    curType = list[idx++].idx;
                    curCnt = 1;
                }else{
                    curType = list[idx++].idx;
                    curCnt = 1;
                }
            }
        }
    }

    static void setList(){
        int idx = N * N - 1;
        int d = 0;
        int cx = 0, cy = 0;
        map[cx][cy][0] = idx;
        list[idx--] = new Node(cx, cy, map[cx][cy][1]);
        visited[cx][cy] = true;
        while(true){
            if (idx == -1) {
                break;
            }
            cx = cx + dx[d];
            cy = cy + dy[d];
            if(!isAvailable(cx, cy) || visited[cx][cy]){
                cx = cx - dx[d];
                cy = cy - dy[d];
                d = (d + 1) % 4;
            }else{
                visited[cx][cy] = true;
                map[cx][cy][0] = idx;
                list[idx--] = new Node(cx, cy, map[cx][cy][1]);
            }
        }
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void print(){
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                System.out.print(map[i][j][1] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BOJ21611.solution();
    }
}


//순서대로 구현하면되는 시뮬레이션 구현 문제였다.
//주의할 점은 구슬을 터트리고 당긴 후 터트리는 것이 아니라, 한번에 전체 구슬 중 터트릴 수 있는 것들을 터트리고 구슬들을 당긴 후, 터트릴 수 있는 구슬들을 터트리는 것을 반복해야한다.
//또한 Node[] list 라는 구슬 배열을 만들고 해당 배열에는 x , y , 구슬 번호 값을 저장해주었고,
//map[][][]에는 map[x][y][0]에는 해당 구슬의 idx값(list의 idx값), map[x][y][1]에는 해당 구슬의 번호 값을 저장해주었다.