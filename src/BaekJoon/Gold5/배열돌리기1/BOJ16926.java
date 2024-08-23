package BaekJoon.Gold5.배열돌리기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16926 {

    static int N, M, R;
    static int[][] arr, temp;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < R; i++) {
            compute();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void compute(){
        temp = new int[N][M];
        visited = new boolean[N][M];
        int sX = 0, sY = 0, d = 0;

        while(true){
            int cX = sX;
            int cY = sY;
            visited[cX][cY] = true;
            while (true) {
                int nx = cX + dx[d];
                int ny = cY + dy[d];
                if (!isAvailable(nx, ny)) {
                    d = (d + 1) % 4;
                    continue;
                }
                if(nx == sX && ny == sY){
                    temp[nx][ny] = arr[cX][cY];
                    d = (d + 1) % 4;
                    break;
                }
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    temp[nx][ny] = arr[cX][cY];
                    cX = nx;
                    cY = ny;
                }else{
                    d = (d + 1) % 4;
                }
            }
            sX++;
            sY++;
            if (visited[sX][sY]) {
                break;
            }
        }
        for (int i = 0; i < N; i++) {
            arr[i] = temp[i].clone();
        }
    }

    static boolean isAvailable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BOJ16926.solution();
    }
}
