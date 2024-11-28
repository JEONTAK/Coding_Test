package BaekJoon.Gold5.주사위;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1041 {

    static int N;
    static long[] dice = new long[6];
    static long result = 0;
    static long[] count = new long[3];

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            dice[i] = Long.parseLong(st.nextToken());
        }
        count[0] = (5L * N * N) - (16L * N) + 12;
        count[1] = (8L * N) - 12;
        count[2] = 4L;

        if (N == 1) {
            Arrays.sort(dice);
            for (int i = 0; i < 5; i++) {
                result += dice[i];
            }
            System.out.println(result);
            return;
        }

        long min = dice[0];
        for (int i = 1; i < 6; i++) {
            min = Math.min(min, dice[i]);
        }
        result += count[0] * min;
        min = Long.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (j + i != 5) {
                    min = Math.min(min, dice[i] + dice[j]);
                }
            }
        }
        result += count[1] * min;

        min = 0;
        for (int i = 0; i < 3; i++) {
            min += Math.min(dice[i], dice[5 - i]);
        }
        result += count[2] * min;
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ1041.solution();
    }
}
