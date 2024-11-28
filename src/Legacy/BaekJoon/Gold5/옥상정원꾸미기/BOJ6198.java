package BaekJoon.Gold5.옥상정원꾸미기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ6198 {

    static int N, height;
    static long result = 0;
    static Stack<Integer> s = new Stack<>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            height = Integer.parseInt(br.readLine());
            while (!s.isEmpty()) {
                if (s.peek() <= height) {
                    s.pop();
                }else break;
            }
            result += s.size();
            s.push(height);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ6198.solution();
    }
}
