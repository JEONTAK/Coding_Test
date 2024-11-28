package BaekJoon.Gold2.플로이드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11780 {

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
    static ArrayList<Integer>[][] result;
    static int[][] dist;
    static int[][] n;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        result = new ArrayList[N + 1][N + 1];
        n = new int[N + 1][N + 1];
        for(int i = 1 ; i <= N ; i++){
            list[i] = new ArrayList<>();
            for(int j = 1 ; j <= N ; j++){
                result[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b,d));
        }
        dist = new int[N+1][N+1];
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= N ; j++){
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int i = 1 ; i <= N ; i++){
            compute(i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= N ; j++){
                sb.append((dist[i][j] != Integer.MAX_VALUE ? dist[i][j] + " " : 0 + " "));
            }
            sb.append("\n");
        }
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= N ; j++){
                int size = result[i][j].size();
                if(i == j || dist[i][j] == Integer.MAX_VALUE){
                    sb.append(0 + "\n");
                    continue;
                }
                sb.append((size + 1) +  " ");
                for (int num : result[i][j]) {
                    sb.append(num + " ");
                }
                sb.append(j + "\n");
            }
        }
        System.out.println(sb.toString());

    }


    static void compute(int start){
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        q.add(new Node(start, 0));
        dist[start][start] = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(visited[cur.e]){
                continue;
            }
            visited[cur.e] = true;

            for (Node next : list[cur.e]) {
                if (!visited[next.e] && dist[start][next.e] > dist[start][cur.e] + next.d) {
                    dist[start][next.e] = dist[start][cur.e] + next.d;
                    setRoute(start, cur.e, next.e);
                    q.add(new Node(next.e, dist[start][next.e]));
                }
            }
        }
    }

    static void setRoute(int start, int cur, int next){
        result[start][next].clear();
        for (int r : result[start][cur]) {
            result[start][next].add(r);
        }
        result[start][next].add(cur);
    }

    public static void main(String[] args) throws IOException {
        BOJ11780.solution();
    }
}
//우선순위 큐 및 DFS를 사용하여 각 시작위치에서 도착 지점까지의 최단경로를 구해주었다.
//여기서 만약 최단거리가 갱신되었다면, 경로도 갱신해주어야된다.
//따라서 경로를 갱신하는 함수를 setRoute로 만들어,
//[시작][다음] 배열에 있는 경로를 삭제하고,
//[시작][현재] 경로에 + 현재 위치를 더해주어 다음 경로를 갱신해주었다.

