package BaekJoon.Gold5.하노이탑이동순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11729 {

    static int N, C;
    static StringBuilder sb = new StringBuilder();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        C = (int) (Math.pow(2, N) - 1);
        sb.append(C + "\n");
        compute(N, 1, 2, 3);
        System.out.println(sb.toString());
    }

    static void compute(int cnt, int l, int m, int r) {
        if (cnt == 1) {
            sb.append(l + " " + r + "\n");
            return;
        }
        compute(cnt - 1, l, r, m);
        sb.append(l + " " + r + "\n");
        compute(cnt - 1, m, l, r);
    }

    public static void main(String[] args) throws IOException {
        BOJ11729.solution();
    }
}
