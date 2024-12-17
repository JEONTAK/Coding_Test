package BaekJoon.Gold3.최소비용구하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11779 {


    static class Node implements Comparable<Node>{
        int city;
        int cost;

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, M;
    static ArrayList<Node>[] list;
    static int[] path, dist;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        path = new int[N + 1];
        dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            path[i] = i;
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Node(e, c));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        DFS(s, e);

        System.out.println(dist[e]);
        ArrayList<Integer> resultPath = new ArrayList<>();
        int cur = e;
        while (cur != s) {
            resultPath.add(cur);
            cur = path[cur];
        }
        resultPath.add(cur);
        Collections.reverse(resultPath);
        System.out.println(resultPath.size());
        for (Integer i : resultPath) {
            System.out.print(i + " ");
        }
    }

    static void DFS(int s, int e) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.city == e) {
                return;
            }

            if(visited[cur.city]) continue;
            visited[cur.city] = true;

            for (Node next : list[cur.city]) {
                if (!visited[next.city] && dist[next.city] > dist[cur.city] + next.cost) {
                    dist[next.city] = dist[cur.city] + next.cost;
                    path[next.city] = cur.city;
                    pq.add(new Node(next.city, dist[next.city]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ11779.solution();
    }
}

/*
문제 분석
1. 정보
    - N개의 도시
    - M개의 버스
        - 버스는 출발 도시의 번호, 도착 도시의 번호, 버스 비용이 주어짐

2. 목표
    - A도시에서 출발하여 B도시로 가는데 필요한 최소비용
    - 가는데 필요한 도시의 개수
    - 가는 경로를 차례대로 출력
    
3. 제약 조건
    - N : 1 <= N <= 1000
    - M : 1 <= M <= 100000

풀이
1. 알고리즘
    - DFS 사용하여 비용을 기준으로 적은 도시부터 차례대로 계산
        - Node class 생성 : 도시 번호, 비용 저장
        - dist[] 배열 사용 : 해당 도시까지 가는데 필요한 최소 비용 저장

2. 탐색 과정
    - DFS
        - Node class 생성 : 도시 번호, 비용 저장
        - dist[] 배열 생성 : 해당 도시까지 가는데 필요한 최소 비용 저장 위함.
        - ArrayList[] 배열 생성해 각 도시에서 탈 수 있는 버스와 해당 버스의 비용을 저장
        - 위 배열을 사용해 시작지점에서 DFS 알고리즘 실행
        - 만약 아직 방문하지 않았고, 다음 목적지까지의 최소비용(dist[다음])이 현재 목적지까지의 최소 비용(dist[현재]) + 다음 목적지까지 가는데 필요한 비용(cost)보다 크다면, 값 업데이트하고 큐에 추가
        - 추가로, path[] 배열을 생성해 path[다음 도시] = 현재 도시 값을 저장

    - 결과 생성
        - 경로 결과값 저장하기 위한 ArrayList 생성
        - 해당 ArrayList에 도착 지점 부터 차례대로 값을 저장
            - 값 저장 이후에는 해당 값을 path[현재 값]으로 업데이트 해주어 경로 생성
            - 시작 지점까지 경로가 생성되면, 해당 값 reverse 하여 시작 -> 도착 지점까지의 경로 생성
        - dist[도착], ArrayList 크기, 해당 경로를 차례대로 출력
 */
