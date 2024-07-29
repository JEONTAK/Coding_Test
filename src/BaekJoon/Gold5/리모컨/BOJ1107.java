package BaekJoon.Gold5.리모컨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1107 {

    static int N, M;
    static boolean[] num = new boolean[10];
    static int cnt, channel = 100;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        Arrays.fill(num, true);
        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int cur = Integer.parseInt(st.nextToken());
                num[cur] = false;
            }
        }
        cnt = Math.abs(N - channel);
        if (cnt == 0) {
            System.out.println(cnt);
            return;
        }
        compute(0, 0);
        System.out.println(cnt);
    }

    static void compute(int cur, int digit) {
        for (int i = 0; i <= 9; i++) {
            if (num[i]) {
                int newC = cur * 10 + i;
                cnt = Math.min(cnt, Math.abs(N - newC) + String.valueOf(newC).length());
                if (digit < 6) {
                    compute(newC, digit + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1107.solution();
    }
}
