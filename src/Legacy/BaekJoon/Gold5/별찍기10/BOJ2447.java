package BaekJoon.Gold5.별찍기10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2447 {

    static int N;
    static char[][] map;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], '*');
        }
        compute(0, 0, N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void compute(int x, int y, int size) {
        if (size == 3) {
            map[x + 1][y + 1] = ' ';
            return;
        }
        for (int i = x; i < x + size; i = i + size / 3) {
            for (int j = y; j < y + size; j = j + size / 3) {
                if (i == x + (size / 3) && j == y + (size / 3)) {
                    makeB(i, j, size / 3);
                }else{
                    compute(i, j, size / 3);
                }
            }
        }
    }

    static void makeB(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = ' ';
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ2447.solution();
    }
}
