package BaekJoon.Gold5.톱니바퀴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891 {

    static int[][] crank = new int[4][8];
    static int K;
    static int[] c = new int[4];
    static boolean[] visited;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            String temp = br.readLine();
            for (int j = 0; j < 8; j++) {
                crank[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j) - '0'));
            }
        }
        K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            visited = new boolean[4];
            compute(idx - 1, dir);
        }
        int score = 1;
        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (crank[i][c[i]] == 1) {
                result += score;
            }
            score *= 2;
        }
        System.out.println(result);
    }

    static void compute(int idx, int dir) {
        visited[idx] = true;
        int left = idx - 1;
        int right = idx + 1;
        if (isAvailable(left)) {
            if ((crank[idx][(c[idx] + 6) % 8] + crank[left][(c[left] + 2) % 8]) % 2 == 1) {
                if (!visited[left]) {
                    compute(left, -1 * dir);
                }
            }
        }
        if (isAvailable(right)) {
            if ((crank[idx][(c[idx] + 2) % 8] + crank[right][(c[right] + 6) % 8]) % 2 == 1) {
                if (!visited[right]) {
                    compute(right, -1 * dir);
                }
            }
        }
        c[idx] = (c[idx] + (-1 * dir) + 8) % 8;
    }

    static boolean isAvailable(int idx) {
        return idx >= 0 && idx < 4;
    }

    public static void main(String[] args) throws IOException {
        BOJ14891.solution();
    }
}
