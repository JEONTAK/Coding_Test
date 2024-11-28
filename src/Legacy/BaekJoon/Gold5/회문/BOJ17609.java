package BaekJoon.Gold5.회문;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17609 {

    static int N;
    static StringBuilder sb = new StringBuilder();
    static String str;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            sb.append(compute(0, str.length() - 1, 0)).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int compute(int left, int right, int result) {
        if (result == 2) {
            return 2;
        }
        while (left < right) {
            if (str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            }else{
                return Math.min(compute(left + 1, right, result + 1), compute(left, right - 1, result + 1));
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BOJ17609.solution();
    }
}
