package BaekJoon.Gold3.중량제한;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1939 {

    static class Node{
        int e;
        int w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }

    static int N, M, S, E;
    static ArrayList<Node>[] list;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new Node(e, w));
            list[e].add(new Node(s, w));
        }
        st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        System.out.println(binarySearch());
    }

    static int binarySearch() {
        int low = 1;
        int high = 1_000_000_000;
        int result = 0;
        while(low <= high) {
            int mid = (low + high) / 2;

            if (bfs(mid)) {
                result = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return result;
    }

    static boolean bfs(int weight) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(S);
        visited[S] = true;
        while(!q.isEmpty()) {
            int cur = q.poll();

            if (cur == E) {
                return true;
            }
            for (Node node : list[cur]) {
                if (!visited[node.e] && node.w >= weight) {
                    visited[node.e] = true;
                    q.add(node.e);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BOJ1939.solution();
    }
}

/*
문제 분석
1. 정보
    - N개의 섬 (2 <= N <= 10000)
    - 각 다리마다 중량제한이 있음
    - M개의 다리에 대한 정보(1 <= M <= 100000)
        - A, B (1 <= A, B <= N), C (1 <= C <= 10^9)
        - A와 B섬 사이의 C의 중량제한이 있다는 의미

2. 목표
    - 한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 출력

3. 제약 조건
    - 중량제한을 초과하는 양의 물품은 다리를 지날 수 없음
풀이
1. 알고리즘
    - BFS
        - BFS를 활용해 공장이 있는 두섬을 가는 경로의 중량 제한을 구함
    - 이분탐색
        - 이분탐색을 활용해 중량의 최대값을 구함
            - 현재 중량을 C라고 했을 경우,
                - BFS를 통해 이동 경로의 중량 제한 값을 구함
                - 만약 경로가 가능하다면 C를 증가, 안된다면 C를 감소 시킴


2. 탐색 과정
    - Node Class를 만들어 그래프를 저장
        - 그래프에는 도착 지점, 중량 제한 값을 저장
    - 이분 탐색할 중량 C를 사용해 이분 탐색
        - C는 1 과 10^9 사이의 중간값부터 시작
        - 만약 이동 경로의 중량 제한 값이 C보다 크다면 C를 증가
        - 작다면 C를 감소
        - left, right 값이 같아질 때까지 반복

 */
