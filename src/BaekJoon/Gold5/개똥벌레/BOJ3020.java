package BaekJoon.Gold5.개똥벌레;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3020 {

    static int N, H;
    static int[] odd, even;
    static int result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        odd = new int[N / 2];
        even = new int[N / 2];
        for (int i = 0; i < N / 2; i++) {
            odd[i] = Integer.parseInt(br.readLine());
            even[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(odd);
        Arrays.sort(even);
        int min = N;
        for (int i = 1; i < H + 1; i++) {
            int obs = compute(0, N / 2, i, odd) + compute(0, N / 2, H - i + 1, even);
            if (min == obs) {
                result++;
            }
            if (min > obs) {
                min = obs;
                result = 1;
            }
        }
        System.out.println(min + " " + result);
    }

    static int compute(int left, int right, int h, int[] obs) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (obs[mid] >= h) {
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return obs.length -  right;
    }

    public static void main(String[] args) throws IOException {
        BOJ3020.solution();
    }
}
