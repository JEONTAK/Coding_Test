package BaekJoon.Gold5.빗물;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719 {

    static int H, W, result = 0;
    static int[][] map;
    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int h = Integer.parseInt(st.nextToken());
            for (int j = 0; j < h; j++) {
                map[j][i] = 1;
            }
        }
        for(int i = 0 ; i < H ; i++){
            for(int j = 0 ; j < W ; j++){
                if (map[i][j] == 0) {
                    compute(i, j);
                }
            }
        }
        for(int i = 0 ; i < H ; i++){
            for(int j = 0 ; j < W ; j++){
                if (map[i][j] == 2) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    static void compute(int x, int y) {
        boolean l = false, r = false;
        for (int i = y; i >= 0; i--) {
            if (map[x][i] >= 1) {
                l = true;
                break;
            }
        }
        for (int i = y; i < W; i++) {
            if(map[x][i] >= 1){
                r = true;
                break;
            }
        }
        if (l && r) {
            map[x][y] = 2;
        }
    }


    public static void main(String[] args) throws IOException {
        BOJ14719.solution();
    }
}
