package BaekJoon.Gold5.Contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1013 {

    static int T;
    static String value, str = "(100+1+|01)+";

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            value = br.readLine();
            if(value.matches(str)) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ1013.solution();
    }
}
