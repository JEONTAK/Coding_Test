package BaekJoon.Gold5.노드사이의거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1240 {

    static class Node{
        int e;
        int d;

        public Node(int e, int d) {
            this.e = e;
            this.d = d;
        }
    }

    static int N, M;
    static ArrayList<Node>[] list;
    static int[][] dist;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[s].add(new Node(e, d));
            list[e].add(new Node(s, d));
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            compute(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(dist[s][e]).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void compute(int s) {
        Queue<Node> pq = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        dist[s][s] = 0;
        pq.offer(new Node(s, 0));
        visited[s] = true;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            for (Node next : list[cur.e]) {
                if (!visited[next.e] && dist[s][next.e] > dist[s][cur.e] + next.d) {
                    visited[next.e] = true;
                    dist[s][next.e] = dist[s][cur.e] + next.d;
                    pq.offer(new Node(next.e, dist[s][next.e]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1240.solution();
    }
}
