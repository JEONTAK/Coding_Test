package BaekJoon.Gold2.미확인도착지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9370 {

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

    static final int INF = 1_000_000_000;
    static int T, N, M, t, S, G, H;
    static ArrayList<Node>[] list;
    static boolean[] visited;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            list = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                list[i] =  new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                list[s].add(new Node(e, d));
                list[e].add(new Node(s, d));
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int i = 0; i < t; i++) {
                int E = Integer.parseInt(br.readLine());
                long res1 = compute(S, G) + compute(G, H) + compute(H, E);
                long res2 = compute(S, H) + compute(H, G) + compute(G, E);
                long res3 = compute(S, E);
                if (res1 == res3 || res2 == res3) {
                    if (res3 != INF) {
                        pq.add(E);
                    }
                }
            }

            while(!pq.isEmpty()) {
                sb.append(pq.poll() + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int compute(int s, int e) {
        int[] dist = new int[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(s, 0));
        dist[s] = 0;
        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(visited[cur.e])continue;
            visited[cur.e] = true;

            for(Node next : list[cur.e]) {
                if (dist[next.e] > dist[cur.e] + next.d) {
                    dist[next.e] = dist[cur.e] + next.d;
                    q.add(new Node(next.e, dist[next.e]));
                }
            }
        }
        return dist[e] != INF ? dist[e] : -1;
    }

    public static void main(String[] args) throws IOException {
        BOJ9370.solution();
    }
}

//PQ를 이용하여 구현하였다.
//먼저 우리는 시작지점과 도착지점을 알 수 있다.
//따라서 해당 시작지점인 S, 도착지점인 E를 통해 dist[] 배열을 구한다.
//여기서 우리는 G -> H, H -> G를 지나는 경우가 최단거리인지 확인해야한다.
//따라서 S -> G -> H -> E 와 S -> H -> G -> E 의 dist[] 를 각각 구해준다.
//위의 두가지 경우중 S -> E의 최단거리와 같은 값이 있다면, 해당 도착지점으로 갈 수 있는 것이므로, 도착지점을 모아두는 PQ저장해놓는다.
//t개만큼 반복하였다면 pq순서대로 뽑아서 출력해주면 끝
