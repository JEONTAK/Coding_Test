package BaekJoon.Gold5.수고르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2230 {

    static int N;
    static long M;
    static long[] seq;
    static long result = Long.MAX_VALUE - 1;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        seq = new long[N];
        for (int i = 0; i < N; i++) {
            seq[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(seq);
        int left = 0;
        int right = 0;
        while (left < N) {
            if(Math.abs(seq[left] - seq[right]) < M) {
                left++;
                continue;
            }
            if(Math.abs(seq[left] - seq[right]) == M) {
                result = M;
                break;
            }
            result = Math.min(result, Math.abs(seq[left] - seq[right]));
            right++;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ2230.solution();
    }
}
