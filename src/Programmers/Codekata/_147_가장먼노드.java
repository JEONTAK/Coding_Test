package Programmers.Codekata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _147_가장먼노드 {

    class Solution {

        class Node {
            int e;
            int d;

            public Node(int e, int d) {
                this.e = e;
                this.d = d;
            }
        }

        List<Integer>[] list;

        public int solution(int n, int[][] edge) {

            list = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < edge.length; i++) {
                int s = edge[i][0];
                int e = edge[i][1];
                list[s].add(e);
                list[e].add(s);
            }

            boolean[] visited = new boolean[n + 1];
            int[] dist = new int[n + 1];
            Arrays.fill(dist, 30000);
            Queue<Node> q = new LinkedList<>();
            q.add(new Node(1, 0));
            visited[1] = true;
            dist[1] = 0;
            while (!q.isEmpty()) {
                Node cur = q.poll();
                for (Integer next : list[cur.e]) {
                    if (!visited[next] && dist[next] > cur.d + 1) {
                        dist[next] = cur.d + 1;
                        visited[next] = true;
                        q.add(new Node(next, dist[next]));
                    }
                }
            }

            int answer = 0;
            int max = 0;
            for (int i = 1; i <= n; i++) {
                max = max == 30000 ? max : Math.max(max, dist[i]);
            }

            for (int i : dist) {
                if (i == max) {
                    answer++;
                }
            }

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - n개의 노드가 있는 그래프가 존재
    - 각 노드는 1부터 n까지 번호가 적혀 있음
    - 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 함.
    - 가장 멀리 떨어진 노드란 최단 경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미
2. 목표
    - 노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return
3. 제약 조건
    - 2 <= 노드의 개수 n <= 20000
    - 간선은 양방향이며 총 1개 이상 50000개 이하의 간선이 존재
    - vertex 배열 각 행 [a,b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미

풀이
1. 아이디어
    - BFS와 dist[] 배열을 사용해 풀이
    - Node class를 생성
        - 도착점, 거리를 가짐
    - dist[] 배열 생성 및 간선이 최대 노드의 개수가 최대 20000개 이므로 30000으로 초기화
    - Queue<Node>를 생성
        - Queue에 시작점인 1번 노드 저장
        - dist[1] = 0으로 초기화
        - 큐가 empty 일때 까지
            - Queue에서 Node 꺼냄
                - 해당 노드와 연결되는 모든 노드들에 대하여
                    - 만약 현재 거리 + 1 보다 이미 저장된 노드가 크다면
                        - 거리 업데이트하고 큐에 저장
                        - 방문 처리하여 다시 방문 못하게 함.
        
*/
