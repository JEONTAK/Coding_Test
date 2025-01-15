package Programmers.Codekata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class test {

    class Solution {
        static ArrayList<ArrayList<Node>> graph;
        static int[] dist;
        static class Node implements Comparable<Node> {
            int number;
            int cost;
            public Node(int number, int cost) {
                this.number = number;
                this.cost = cost;
            }
            @Override
            public int compareTo(Node node) {
                return this.cost - node.cost;
            }

        }
        public int solution(int N, int[][] road, int K) {
            graph = new ArrayList<>();
            dist = new int[N+1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            for(int i=0; i<=N; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i=0; i<road.length; i++) {
                graph.get(road[i][0]).add(new Node(road[i][1], road[i][2]));
                graph.get(road[i][1]).add(new Node(road[i][0], road[i][2]));
            }

            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.add(new Node(1, 0));
            dist[1] = 0;
            while(!queue.isEmpty()) {
                Node cur = queue.poll();
                if(dist[cur.number] < cur.cost) continue;
                for(Node next : graph.get(cur.number)) {
                    if(dist[next.number] > dist[cur.number]+next.cost) {
                        dist[next.number] = dist[cur.number]+next.cost;
                        queue.add(new Node(next.number, dist[next.number]));
                    }
                }
            }

            int answer = 0;
            for(int i=1; i<=N; i++) {
                if(dist[i]<=K) answer++;
            }

            return answer;
        }
    }
}
