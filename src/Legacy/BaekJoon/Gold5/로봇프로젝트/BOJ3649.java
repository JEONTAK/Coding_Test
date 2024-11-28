package BaekJoon.Gold5.로봇프로젝트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ3649 {

    static final int NM =10_000_000;
    static int X, N;
    static int[] block;
    static int l1, l2, result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String temp = br.readLine();
            if (temp == null) {
                break;
            }
            X = Integer.parseInt(temp);
            N = Integer.parseInt(br.readLine());
            block = new int[N];
            result = -1;
            for (int i = 0; i < N; i++) {
                block[i] = Integer.parseInt(br.readLine());
            }
            int left = 0;
            int right = N - 1;
            Arrays.sort(block);
            while (left < right) {
                int len = block[left] + block[right];
                if (len == X * NM) {
                if (Math.abs(block[left] - block[right]) > result) {
                    result = Math.abs(block[left] - block[right]);
                    l1 = left;
                    l2 = right;
                }
                left++;
                }
                else if (len > X * NM) {
                    right--;
                }
                else{
                    left++;
                }
            }
            if (result == -1) {
                System.out.println("danger");
            }else{
                System.out.println("yes " + block[l1] + " " + block[l2]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ3649.solution();
    }
}
