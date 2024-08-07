package BaekJoon.Gold5.로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503 {

    static int N, M;
    static int[][] map;
    //0:북 1:동 2:남 3:서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int sX, sY, sD;
    static int clean = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        sX = Integer.parseInt(st.nextToken());
        sY = Integer.parseInt(st.nextToken());
        sD = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        compute(sX, sY, sD);
    }

    static void compute(int x, int y, int d){
        while(true){
            if (map[x][y] == 0) {
                map[x][y] = 2;
                clean++;
            }

            if (isClean(x, y)) {
                int nx = x + dx[(d + 2) % 4];
                int ny = y + dy[(d + 2) % 4];
                if (map[nx][ny] != 1) {
                    x = nx;
                    y = ny;
                }else{
                    System.out.println(clean);
                    System.exit(0);
                }
            }else{
                for (int i = 3; i >= 0 ; i--) {
                    int nx = x + dx[(d + i) % 4];
                    int ny = y + dy[(d + i) % 4];
                    if (map[nx][ny] == 0) {
                        x = nx;
                        y = ny;
                        d = (d + i) % 4;
                        break;
                    }
                }
            }
        }
    }

    static boolean isClean(int x, int y){
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (map[nx][ny] == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BOJ14503.solution();
    }
}
