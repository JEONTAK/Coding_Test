package Legacy.BaekJoon.Gold5.전깃줄;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2565 {

    static class Node implements Comparable<Node>{
        int s;
        int e;

        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node o) {
            return this.s - o.s;
        }
    }

    static int N;
    static ArrayList<Node> list = new ArrayList<>();
    static int[] dp;
    static int max = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new Node(s, e));
        }
        Collections.sort(list);
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            max = Math.max(compute(i), max);
        }
        System.out.println(N - max);
    }

    static int compute(int idx) {
        if (dp[idx] == 0) {
            dp[idx] = 1;
            for (int i = idx + 1; i < N; i++) {
                if (list.get(idx).e < list.get(i).e) {
                    dp[idx] = Math.max(dp[idx], compute(i) + 1);
                }
            }
        }
        return dp[idx];
    }

    public static void main(String[] args) throws IOException {
        BOJ2565.solution();
    }
}
