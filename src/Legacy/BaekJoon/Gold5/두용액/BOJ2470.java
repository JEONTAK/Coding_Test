package BaekJoon.Gold5.두용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {

    static int N;
    static int[] water;
    static int min = Integer.MAX_VALUE;
    static int rL, rR;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        water = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            water[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(water);
        int left = 0, right = N - 1;
        while (left < right) {
            int cur = water[left] + water[right];
            if (min > Math.abs(cur)) {
                min = Math.abs(cur);
                rL = left;
                rR = right;
            }
            if (cur < 0) {
                left++;
            } else if (cur == 0) {
                break;
            }else{
                right--;
            }
        }
        System.out.println(water[rL] + " " + water[rR]);
    }

    public static void main(String[] args) throws IOException {
        BOJ2470.solution();
    }
}
