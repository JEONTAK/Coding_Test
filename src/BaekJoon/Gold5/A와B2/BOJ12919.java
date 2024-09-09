package BaekJoon.Gold5.A와B2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12919 {

    static StringBuilder s1, s2;
    static int result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = new StringBuilder(br.readLine());
        s2 = new StringBuilder(br.readLine());
        compute(s1, s2);
        System.out.println(result);
    }

    static void compute(StringBuilder s1, StringBuilder s2) {
        if(s1.length() == s2.length()) {
            if (s1.toString().equals(s2.toString())) {
                result = 1;
            }
            return;
        }
        if (s2.charAt(0) == 'B') {
            StringBuilder s3 = new StringBuilder(s2);
            s3.reverse();
            s3.deleteCharAt(s3.length() - 1);
            compute(s1, s3);
        }
        if (s2.charAt(s2.length() - 1 ) == 'A') {
            s2.deleteCharAt(s2.length() - 1);
            compute(s1,s2);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ12919.solution();
    }
}
