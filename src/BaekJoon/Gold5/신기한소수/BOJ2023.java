package BaekJoon.Gold5.신기한소수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2023 {

    static int N;
    static int[] first = {2,3,5,7}, next = {1, 3, 7, 9};
    static ArrayList<Integer> result = new ArrayList<Integer>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < 4; i++) {
            compute(first[i], 1);
        }
        Collections.sort(result);
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i)).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void compute(int cur, int idx) {
        if (idx == N) {
            result.add(cur);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int n = cur * 10 + next[i];
            if (isPrime(n)) {
                compute(n, idx + 1);
            }
        }
    }

    static boolean isPrime(int num) {
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BOJ2023.solution();
    }
}
