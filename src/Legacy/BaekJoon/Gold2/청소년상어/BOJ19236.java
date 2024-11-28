package BaekJoon.Gold2.청소년상어;

import java.util.*;
import java.io.*;

public class BOJ19236 {

    static class Fish {
        int x;
        int y;
        int d;
        boolean alive;

        public Fish(int x, int y, int d, boolean alive) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.alive = alive;
        }
    }

    static int[][] map = new int[4][4];
    static Fish[] fish = new Fish[17];
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int max = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0 ; i < 4 ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 4 ; j++){
                int idx = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;
                fish[idx] = new Fish(i, j, d, true);
                map[i][j] = idx;
            }
        }
        fish[map[0][0]].alive = false;
        int idx = map[0][0];
        int d = fish[map[0][0]].d;
        compute(0, 0, d, idx);
        System.out.println(max);
    }

    static void compute(int x, int y, int d, int eat) {
        max = Math.max(max, eat);

        int[][] tempMap = new int[4][4];
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                tempMap[i][j] = map[i][j];
            }
        }

        Fish[] tempFish = new Fish[17];
        for(int i = 1 ; i < 17 ; i++){
            tempFish[i] = new Fish(fish[i].x, fish[i].y, fish[i].d, fish[i].alive);
        }
        moveFish(x, y);

        for(int i = 1 ; i < 4 ; i++){
            int nx = x + (dx[d] * i);
            int ny = y + (dy[d] * i);
            if (isAvailable(nx, ny) && fish[map[nx][ny]].alive) {
                int idx = map[nx][ny];
                int nd = fish[idx].d;
                fish[idx].alive = false;
                compute(nx, ny, nd, eat + idx);
                fish[idx].alive = true;
            }
        }

        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                map[i][j] = tempMap[i][j];
            }
        }

        for(int i = 1 ; i < 17 ; i++){
            fish[i] = new Fish(tempFish[i].x, tempFish[i].y, tempFish[i].d, tempFish[i].alive);
        }
    }

    static void moveFish(int sx, int sy){
        for(int i = 1 ; i < 17 ; i++){
            if(!fish[i].alive){
                continue;
            }
            int nd = fish[i].d;
            for(int j = 0 ; j < 8 ; j++){
                nd = (fish[i].d + j) % 8;
                int nx = fish[i].x + dx[nd];
                int ny = fish[i].y + dy[nd];
                if (isAvailable(nx, ny) && (nx != sx || ny != sy)) {
                    int te = map[fish[i].x][fish[i].y];
                    Fish target = fish[map[nx][ny]];
                    map[fish[i].x][fish[i].y] = map[nx][ny];
                    map[nx][ny] = te;
                    target.x = fish[i].x;
                    target.y = fish[i].y;
                    fish[i].x = nx;
                    fish[i].y = ny;
                    fish[i].d = nd;
                    break;
                }
            }
        }
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }

    public static void main(String[] args) throws IOException {
        BOJ19236.solution();
    }

}