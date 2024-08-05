package BaekJoon.Gold5.탑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {

    static class Tower{
        int idx;
        int h;

        public Tower(int idx, int h) {
            this.idx = idx;
            this.h = h;
        }
    }

    static int N;
    static Stack<Tower> s = new Stack<>();
    static StringBuilder sb = new StringBuilder();
    static int[] tower;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        tower = new int[N + 1];
        int curH = 0;
        for (int i = 1; i <= N; i++) {
            curH = Integer.parseInt(st.nextToken());
            while (true) {
                if (s.isEmpty()) {
                    s.push(new Tower(i, curH));
                    sb.append("0 ");
                    break;
                }
                Tower t = s.peek();
                if (t.h > curH) {
                    sb.append(t.idx + " ");
                    s.push(new Tower(i, curH));
                    break;
                }else{
                    s.pop();
                }
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ2493.solution();
    }
}
