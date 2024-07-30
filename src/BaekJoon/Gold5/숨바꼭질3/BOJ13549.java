package BaekJoon.Gold5.숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13549 {

    static class Node{
        int x;
        int t;

        public Node(int x, int t) {
            this.x = x;
            this.t = t;
        }
    }

    static boolean[] visited = new boolean[100001];
    static int min = Integer.MAX_VALUE;
    static int N, K;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        compute();
        System.out.println(min);
    }

    static void compute() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(N, 0));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            visited[cur.x] = true;
            if (cur.x == K) {
                min = Math.min(min, cur.t);
            }
            if(cur.x * 2 <= 100000 && !visited[cur.x * 2]) {
                q.offer(new Node(cur.x * 2, cur.t));
            }
            if(cur.x + 1 <= 100000 && !visited[cur.x + 1]) {
                q.offer(new Node(cur.x + 1, cur.t + 1));
            }
            if(cur.x - 1 >= 0 && !visited[cur.x - 1]) {
                q.offer(new Node(cur.x - 1, cur.t + 1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ13549.solution();
    }
}
