package BaekJoon.Gold3.파티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ12387 {

    static class Node{
        int e;
        int d;

        public Node(int e, int d) {
            this.e = e;
            this.d = d;
        }
    }
    static int N, M, X;
    static ArrayList<Node>[] list;
    static int[][] dist;
    static int result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        dist = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[s].add(new Node(e, d));
        }

        bfs();

        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dist[i][X] + dist[X][i]);
        }
        System.out.println(result);
    }

    static void bfs(){
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i <= N; i++) {
            Queue<Node> q = new LinkedList<>();
            q.add(new Node(i, 0));
            dist[i][i] = 0;
            while(!q.isEmpty()){
                Node cur = q.poll();
                for (Node next : list[cur.e]) {
                    if(dist[i][next.e] > dist[i][cur.e] + next.d){
                        dist[i][next.e] = dist[i][cur.e] + next.d;
                        q.add(new Node(next.e, dist[i][next.e]));}
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ12387.solution();
    }
}
/*
문제 분석
1. 정보
    - 마을의 개수 N (1 <= N <= 1000)
    - 각 마을 당 1명의 학생이 살고 있음
    - X번 마을에 모여서 파티 (1 <= X <= N)
    - M개의 단방향 도로 (1 <= M <= 10000)
        - i번째 길 지나는데 Ti의 시간 소비 (1<= Ti <= 100)

2. 목표
    - N명의 학생들 중 오고 가는데 가장 많은 시간 소비하는 학생 구하기

3. 제약 조건
    - 한 도시 A 에서 다른 도시 B로 가는 도로의 개수는 최대 1개

풀이
1. 알고리즘
    - BFS 알고리즘
        - BFS를 통해 각 마을에서 도착 마을까지의 최단 거리 구하기

2. 탐색 과정
    - BFS 알고리즘 사용하여 각 마을에서 도착 마을까지의 최단 거리를 구한다
    - dist[][] 배열을 만들어 각 마을에서 다른 마을까지 가는 최단 거리를 구한다.
    - 마을별 시작 지점 -> 도착 지점 -> 시작 지점의 최단 시간을 계산 한 후 그 중 최대 값을 출력 한다.
 */