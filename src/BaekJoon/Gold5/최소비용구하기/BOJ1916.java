package BaekJoon.Gold5.최소비용구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916 {

    static class Node implements Comparable<Node>{
        int e;
        int d;

        public Node(int e, int d) {
            this.e = e;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }

    static int N, M;
    static ArrayList<Node>[] list;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[s].add(new Node(e, d));
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        int [] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Node(s, 0));
        dist[s] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.e == e) {
                System.out.println(cur.d);
                break;
            }
            if(visited[cur.e]) continue;
            visited[cur.e] = true;

            for (Node next : list[cur.e]) {
                if(!visited[next.e] && dist[next.e] > dist[cur.e] + next.d) {
                    dist[next.e] = dist[cur.e] + next.d;
                    pq.offer(new Node(next.e, dist[next.e]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1916.solution();
    }
}
