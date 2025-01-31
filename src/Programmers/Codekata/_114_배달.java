package Programmers.Codekata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _114_배달 {

    class Solution {

        class Node implements Comparable<Node>{
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

        public int solution(int N, int[][] road, int K) {
            List<Node>[] list = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
            }

            for (int[] info : road) {
                int s = info[0];
                int e = info[1];
                int d = info[2];
                list[s].add(new Node(e, d));
                list[e].add(new Node(s, d));
            }

            int answer = 0;
            boolean[] visited = new boolean[N + 1];
            int[] dist = new int[N + 1];
            Arrays.fill(dist, 500001);

            PriorityQueue<Node> pq = new PriorityQueue<>();

            pq.add(new Node(1, 0));
            dist[1] = 0;
            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if (visited[cur.e]) {
                    continue;
                }
                visited[cur.e] = true;

                for (Node next : list[cur.e]) {
                    if (!visited[next.e] && dist[next.e] > dist[cur.e] + next.d) {
                        dist[next.e] = dist[cur.e] + next.d;
                        pq.add(new Node(next.e, dist[next.e]));
                    }
                }
            }

            for (int i = 1; i <= N; i++) {
                System.out.println(dist[i]);
            }

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - N개의 마을로 이루어진 나라가 있음.
    - 각 마을에는 1부터 N까지의 번호가 각각 하나씩 부여되어 있다.
    - 각 마을은 양방향으로 통행할 수 있는 도로로 연결되어 있는데, 서로 다른 마을 간에 이동할 때는 해당 도로를 지나야 함.
    - 현재 1번 마을에 있는 음식점에서 각 마을로 음식 배달을 하려고 함.
    - 각 마을로 부터 음식 주문을 받으려고 하는데, N개의 마을 중에서 K 시간 이하로 배달이 가능한 마을에서만 주문을 받으려고 함.
2. 목표
    - 마을의 개수 N, 각 마을을 연결하는 도로의 정보 road, 음식 배달이 가능한 시간 K가 매개 변수로 주어질 때, 음식 주문을 받을 수 있는 마을의 개수를 return
3. 제약 조건
    - 1 <= N <= 50
    - 1 <= road의 길이 <= 2000
    - 1 <= K <= 500000
    
풀이
1. 아이디어
    - Priority Queue 및 DFS를 활용하여 시작 지점에서 i 지점까지 가는데 최소 시간을 구함.
    - 만약 최소 시간이 K보다 크다면, 해당 마을은 배달 불가함.
    - 따라서 결과 값에서 빼줌
*/
