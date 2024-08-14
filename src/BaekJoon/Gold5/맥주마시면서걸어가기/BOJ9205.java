package BaekJoon.Gold5.맥주마시면서걸어가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9205 {

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int T, N;
    static Node finish;
    static Node[] list;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static boolean flag;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int a = 0; a < T; a++) {
            N = Integer.parseInt(br.readLine());
            list = new Node[N + 2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            list[0] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                list[i + 1] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            finish = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            list[N + 1] = finish;
            visited = new boolean[N + 2];
            visited[0] = true;
            flag = false;
            compute(0);
            sb.append(flag ? "happy\n" : "sad\n");
        }

        System.out.println(sb.toString());
    }

    static void compute(int idx) {
        if (idx == N + 1) {
            flag = true;
            return;
        }
        for (int i = 0; i < N + 2; i++) {
            if (!visited[i]) {
                int dist = Math.abs(list[idx].x - list[i].x) + Math.abs(list[idx].y - list[i].y);
                if (dist <= 1000) {
                    visited[i] = true;
                    compute(i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ9205.solution();
    }
}
