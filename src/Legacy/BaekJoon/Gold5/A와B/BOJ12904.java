package BaekJoon.Gold5.A와B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12904 {

    static StringBuilder S, T;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = new StringBuilder(br.readLine());
        T = new StringBuilder(br.readLine());

        while (S.length() < T.length()) {
            if (T.charAt(T.length() - 1) == 'A') {
                T.deleteCharAt(T.length() - 1);
            }else{
                T.deleteCharAt(T.length() - 1);
                T.reverse();
            }
        }
        if (T.toString().equals(S.toString())) {
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ12904.solution();
    }
}
