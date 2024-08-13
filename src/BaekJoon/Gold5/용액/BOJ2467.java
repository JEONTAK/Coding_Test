package BaekJoon.Gold5.용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2467 {

    static int N;
    static int[] water;
    static int left, right;
    static int rL,rR;
    static int min = Integer.MAX_VALUE;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        water = new int[N];
        for (int i = 0; i < N; i++) {
            water[i] = Integer.parseInt(st.nextToken());
        }
        left = 0;
        right = N - 1;
        while (left < right) {
            int cur = water[left] + water[right];
            if (min > Math.abs(cur)) {
                rL = left;
                rR = right;
                min = Math.abs(cur);
                if (min == 0) {
                    break;
                }
            }
            if (cur < 0) {
                left++;
            }else{
                right--;
            }
        }
        System.out.println(water[rL] + " " + water[rR]);
    }

    public static void main(String[] args) throws IOException {
        BOJ2467.solution();
    }
}
