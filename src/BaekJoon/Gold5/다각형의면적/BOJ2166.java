package BaekJoon.Gold5.다각형의면적;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2166 {

    static int N;
    static double result = 0;
    static long[][] coord;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        coord = new long[N + 1][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coord[i][0] = Long.parseLong(st.nextToken());
            coord[i][1] = Long.parseLong(st.nextToken());
        }
        coord[N][0] = coord[0][0];
        coord[N][1] = coord[0][1];
        for (int i = 0; i < N; i++) {
            result += (coord[i][0] * coord[i + 1][1] - coord[i][1] * coord[i + 1][0]);
        }
        result = 0.5 * Math.abs(result);
        System.out.printf("%.1f", result);
    }

    public static void main(String[] args) throws IOException {
        BOJ2166.solution();
    }
}
