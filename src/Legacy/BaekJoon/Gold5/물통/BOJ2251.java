package BaekJoon.Gold5.물통;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2251 {

    static int A, B, C;
    static TreeSet<Integer> result = new TreeSet<>();
    static boolean[][] check = new boolean[201][201];

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        compute(0, 0, C);
        for (Integer r : result) {
            System.out.print(r + " ");
        }
    }

    static void compute(int a, int b, int c) {
        if(check[a][b]) return;

        if (a == 0) result.add(c);

        check[a][b] = true;

        if (a + b > B) {
            compute((a + b) - B, B, c);
        }else{
            compute(0, a + b, c);
        }

        if (a + b > A) {
            compute(A, (a + b) - A, c);
        }else{
            compute(a + b, 0, c);
        }

        if (a + c > A) {
            compute(A, b, (a + c) - A);
        }else{
            compute(a + c, b, 0);
        }

        if (b + c > B) {
            compute(a, B, (b + c) - B);
        }else{
            compute(a, b + c, 0);
        }

        compute(a, 0, b + c);
        compute(0, b, a + c);
    }

    public static void main(String[] args) throws IOException {
        BOJ2251.solution();
    }
}
