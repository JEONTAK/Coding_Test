package BaekJoon.Gold3.웜홀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1865 {

    static class Node{
        int e;
        int d;

        public Node(int e, int d) {
            this.e = e;
            this.d = d;
        }
    }

    static int T, N, M, W;
    static ArrayList<Node>[] list;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            list = new ArrayList[N + 1];
            for(int i = 1 ; i <= N ; i++){
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                list[s].add(new Node(e, d));
                list[e].add(new Node(s, d));
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                list[s].add(new Node(e, -1 * d));
            }

            sb.append(hasNCycle() ? "YES" : "NO").append("\n");
        }
        
        System.out.println(sb.toString());
    }

    private static boolean hasNCycle() {
        int[] dist = new int[N + 1];
        dist[1] = 0;
        boolean update = false;
        for (int i = 1; i < N; i++) {
            update = false;

            for (int j = 1; j <= N; j++) {
                for (Node node : list[j]) {
                    if(dist[node.e] > dist[j] + node.d){
                        dist[node.e] = dist[j] + node.d;
                        update = true;
                    }
                }
            }

            if (!update) {
                break;
            }
        }

        if (update) {
            for (int i = 1; i <= N; i++) {
                for (Node node : list[i]) {
                    if(dist[node.e] > dist[i] + node.d){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BOJ1865.solution();
    }
}

/*
문제 분석
1. 정보
    - TC개의 TC (1 <= TC <= 5)
    - N개의 지점 (1 <= N <= 500)
    - M개의 도로 (1 <= M <= 2500)
    - W개의 웜홀 (1 <= W <= 200)

    - 도로 정보
        - S, E : 연결된 지점의 번호
        - T : 도로 통해 이동하는 데 걸리는 시간
    - 웜홀 정보
        - S : 시작 지점
        - E : 도착 지점
        - T : 줄어드는 시간
2. 목표
    - 만약 시간이 줄어들면서 출발 위치로 돌아오는 것이 가능하면 YES
    - 불가능하면 NO를 출력한다.

3. 제약 조건
    - 일반 도로는 양방향 통행 가능
    - 웜홀은 S에서 E로만 이동 가능
풀이
1. 벨만-포드 알고리즘
    - 음수 가중치 간성을 포함한 그래프에서 최단 경로를 찾는 알고리즘
    - 음수 사이클이 존재하면, 무한히 비용이 줄어드는 경로 생김
    - 즉 음수 사이클이 있는지 찾는것이 목표

2. 탐색 과정
    1. 그래프 생성
        - N개의 지점과 M + W개의 간선 정보 입력
        - 도로는 양방향, 웜홀은 단방향
    2. 벨만 포드 수행
        - 모든 지점을 시작점으로 간주하여 음수 사이클 탐지
        - 한 번이라도 음수 사이클이 발견되면 TC 결과는 YES

3. 종료 조건
    - 한 번이라도 음수 사이클 발견되면 해당 TC 결과는 YES
    - 없다면 NO

주의할 점
    - 해당 문제는 모든 지점에서 음수사이클을 판별하면 시간 초과가 남
    - 따라서 하나의 지점을 시작점으로 잡고 음수 사이클을 탐지한 후 TC 결과를 반환함
 */