package BaekJoon.Gold2.네트워크복구;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2211 {

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
    static int[] dist, path;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        for(int i = 1 ; i <= N ; i++){
            list[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[s].add(new Node(e, d));
            list[e].add(new Node(s, d));
        }

        dist = new int[N + 1];
        path = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visited[cur.e]) continue;
            visited[cur.e] = true;

            for (Node next : list[cur.e]) {
                if(!visited[next.e] && dist[next.e] > dist[cur.e] + next.d){
                    dist[next.e] = dist[cur.e] + next.d;
                    pq.add(new Node(next.e, dist[next.e]));
                    path[next.e] = cur.e;
                }
            }
        }

        System.out.println(N - 1);
        for(int i = 2 ; i <= N ; i++){
            System.out.println(i + " " + path[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ2211.solution();
    }
}
//기본적인 다익스트라 알고리즘으로 풀었다
//하지만 추가적으로 우리는 경로를 저장하여 출력해야한다.
//해당 구현을 본인은 path라는 int형 배열을 만들어,
//dist가 갱신될때마다 path[다음 위치]에 현재 위치를 저장해 주었다.
//이렇게 하면 해당 위치에 가기 위해 들려야 하는 곳을 알수 있고, 따라서 경로를 알수 있게 된다.
