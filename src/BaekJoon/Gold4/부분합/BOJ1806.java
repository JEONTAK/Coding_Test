package BaekJoon.Gold4.부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806 {

    static int N, S;
    static int[] arr;
    static int left = 0, right = 0;
    static int sum, min;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        min = N + 1;
        sum = arr[0];
        while (left <= right && right < N) {
            if (sum < S) {
                sum += arr[++right];
            }else{
                min = Math.min(min, right - left + 1);
                sum -= arr[left++];
            }
        }
        System.out.println(min == N + 1 ? 0 : min);
    }

    public static void main(String[] args) throws IOException {
        BOJ1806.solution();
    }
}
