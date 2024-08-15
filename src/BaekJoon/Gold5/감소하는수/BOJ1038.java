package BaekJoon.Gold5.감소하는수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ1038 {

    static int N;
    static ArrayList<Long> list = new ArrayList<>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N <= 10) {
            System.out.println(N);
            return;
        }
        for (int i = 0; i < 10; i++) {
            compute(i, 1);
        }
        if (N >= list.size()) {
            System.out.println(-1);
            return;
        }
        Collections.sort(list);
        System.out.println(list.get(N));
    }

    static void compute(long n, int v) {
        if(v > 10) return;
        list.add(n);
        for (int i = 0; i < n % 10; i++) {
            compute((n * 10) + i, v + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1038.solution();
    }
}
