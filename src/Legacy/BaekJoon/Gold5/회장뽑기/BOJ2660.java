package BaekJoon.Gold5.회장뽑기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2660 {

    static class Node{
        int e;
        int d;

        public Node(int e, int d) {
            this.e = e;
            this.d = d;
        }
    }

    static int N;
    static ArrayList<Integer>[] list;
    static int[][] dist;
    static int[] result;
    static int min = Integer.MAX_VALUE;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        dist = new int[N + 1][N + 1];
        result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        while(s != -1 && e != -1) {
            list[s].add(e);
            list[e].add(s);
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            compute(i);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                result[i] = Math.max(result[i], dist[i][j]);
            }
        }
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            min = Math.min(min, result[i]);
        }
        for (int i = 1; i <= N; i++) {
            if (min == result[i]) {
                cnt++;
                sb.append(i + " ");
            }
        }
        System.out.println(min + " " + cnt);
        System.out.println(sb.toString());
    }

    static void compute(int s) {
        boolean[] visited = new boolean[N + 1];
        visited[s] = true;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(s, 0));
        while(!q.isEmpty()) {
            Node cur = q.poll();

            for (int next : list[cur.e]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[s][next] = cur.d + 1;
                    q.add(new Node(next, cur.d + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ2660.solution();
    }
}
