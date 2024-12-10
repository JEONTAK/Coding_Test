package BaekJoon.Gold3.연구소3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17142 {

    static class Node{
        int x;
        int y;
        int t;

        public Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    static int N, M;
    static int[][] map;
    static ArrayList<Node> virus = new ArrayList<>();
    static boolean[] check;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int result = Integer.MAX_VALUE;
    static int remain = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new Node(i, j, 0));
                } else if (map[i][j] == 0) {
                    remain++;
                }
            }
        }
        if (remain == 0) {
            System.out.println(0);
            return;
        }

        ArrayList<Node> selectV = new ArrayList<>();
        check = new boolean[virus.size()];
        bruteforce(0, selectV);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void bruteforce(int idx, ArrayList<Node> selectV) {
        if (selectV.size() == M) {
            bfs(selectV);
            return;
        }

        for(int i = idx ; i < virus.size() ; i++) {
            if (!check[i]) {
                selectV.add(virus.get(i));
                check[i] = true;
                bruteforce(i, selectV);
                check[i] = false;
                selectV.remove(selectV.size() - 1);
            }
        }
    }

    private static void bfs(ArrayList<Node> selectV) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        for (Node node : selectV) {
            q.add(node);
            visited[node.x][node.y] = true;
        }
        int space = remain;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isAvailable(nx, ny) && !visited[nx][ny] && map[nx][ny] != 1) {
                    if (map[nx][ny] == 0) {
                        space--;
                    }
                    if (space == 0) {
                        result = Math.min(result, cur.t + 1);
                        return;
                    }
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, cur.t + 1));
                }
            }
        }
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ17142.solution();
    }
}
/*
문제 분석
1. 정보
    - 빈 칸 : 0
    - 벽 : 1
    - 바이러스(비 활성 상태) : 2
    - M개의 바이러스를 활성 상태로 변경 해야 함 (1 <= M <= 10)
    - Map : N * N (4 <= N <= 50)

2. 목표
    - 여러개의 비활성 상태의 바이러스 중 M개를 골라 연구소에 바이러스를 유출 시켰을 경우, 바이러스가 연구소 전체에 퍼지는 최소 시간 구하기
    - 만약 모든 빈 칸에 바이러스를 퍼트릴 수 없는 경우에는 -1을 출력

3. 제약 조건
    - 여러개의 비활성 상태의 바이러스 중 M개를 골라야함.

풀이
1. 알고리즘
    1. 브루트 포스 알고리즘
        - 브루트 포스 알고리즘을 통해 여러개의 바이러스 중 M개를 고르는 모든 경우의 수를 구한다.
    2. BFS
        - BFS를 통해 고른 M개의 바이러스가 연구소 전체로 퍼지는 최소 시간을 구한다.

2. 탐색 과정
    - 먼저 브루트 포스 알고리즘을 통해 M개의 바이러스를 고른다.
    - 이후 선택된 바이러스를 사용해 연구소 전체로 바이러스가 퍼지는 최소 시간을 구한다.
    - 최소 시간이 존재한다면 그대로 출력하고, 최소 시간이 존재하지 않는다면 -1을 출력한다.
 */