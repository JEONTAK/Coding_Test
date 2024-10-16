package BaekJoon.Gold2.트리의지름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1167 {

    static class Node{
        int e;
        int d;

        public Node(int e, int d) {
            this.e = e;
            this.d = d;
        }
    }

    static int V;
    static ArrayList<Node>[] list;
    static boolean[] visited;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        list = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            while(true){
                int e = Integer.parseInt(st.nextToken());
                if (e == -1) {
                    break;
                }
                int d = Integer.parseInt(st.nextToken());
                list[s].add(new Node(e, d));
            }
        }
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist = compute(dist, 1);
        int start = 1;
        int max = 0;
        for (int i = 1; i <= V; i++) {
            if (dist[i] > max) {
                start = i;
                max = dist[i];
            }
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist = compute(dist, start);
        max = 0;
        for(int i = 1; i <= V; i++) {
            max = Math.max(max, dist[i]);
        }
        System.out.println(max);
    }

    static int[] compute(int[] dist, int start){
        visited = new boolean[V + 1];
        dist[start] = 0;
        visited[start] = true;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for(Node next : list[cur.e]){
                if(!visited[next.e] && dist[next.e] > dist[cur.e] + next.d){
                    visited[next.e] = true;
                    dist[next.e] = dist[cur.e] + next.d;
                    q.add(new Node(next.e, dist[next.e]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BOJ1167.solution();
    }
}

//핵심 포인트 : DFS를 두번하는 것.
//첫번째 DFS에서는 아무 정점을 시작점으로 잡고, 가장 멀리 있는 정점이 어디있는지 구한다.
//여기서 가장 멀리 있는 정점은. 트리내에서 무조건 해당 정점을 지나가는 구조임.
//따라서 두번째 DFS에서는 가장 멀리있는 정점을 시작점으로, 해당 정점에서 가장 멀리있는 정점의 거리를 구하면
//구한 거리가 해당 트리의 지름이 됨.
