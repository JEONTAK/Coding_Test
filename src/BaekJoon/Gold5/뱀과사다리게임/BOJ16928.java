package BaekJoon.Gold5.뱀과사다리게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928 {

    static class Node{
        int x;
        int t;

        public Node(int x, int t) {
            this.x = x;
            this.t = t;
        }
    }

    static int N, M;
    static int[] map = new int[101];
    static boolean[] visited = new boolean[101];
    static int result = 200;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= 100; i++) {
            map[i] = i;
        }
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            map[s] = e;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 0));
        visited[1] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == 100) {
                result = Math.min(result, cur.t);
                continue;
            }
            if(cur.t > result) continue;
            for (int i = 1; i <= 6; i++) {
                int next = cur.x + i;
                if (next <= 100) {
                    next = map[cur.x + i];
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(new Node(next, cur.t + 1));
                    }
                }
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ16928.solution();
    }
}
