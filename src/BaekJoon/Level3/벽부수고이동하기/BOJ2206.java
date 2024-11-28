package BaekJoon.Level3.벽부수고이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {

    static class Node{
        int x;
        int y;
        int b;
        int d;

        public Node(int x, int y, int b, int d) {
            this.x = x;
            this.y = y;
            this.b = b;
            this.d = d;
        }
    }

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static int N, M;


    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int result = Integer.MAX_VALUE;
        int[][] map = new int[N][M];
        boolean[][][] visited = new boolean[N][M][2];

        for(int i = 0 ; i < N ; i++){
            String temp = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = temp.charAt(j) - '0';
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == N - 1 && cur.y == M - 1) {
                result = Math.min(result, cur.d);
                continue;
            }

            if (cur.d >= result) {
                continue;
            }
            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isAvailable(nx, ny) && !visited[nx][ny][cur.b]) {
                    if (cur.b == 0) {
                        if (map[nx][ny] == 1) {
                            visited[nx][ny][1] = true;
                            q.add(new Node(nx, ny, 1, cur.d + 1));
                        }else{
                            visited[nx][ny][0] = true;
                            q.add(new Node(nx, ny, 0, cur.d + 1));
                        }
                    }else{
                        if (map[nx][ny] == 0) {
                            visited[nx][ny][1] = true;
                            q.add(new Node(nx, ny, 1, cur.d + 1));
                        }
                    }
                }
            }
        }

        System.out.println(result == Integer.MAX_VALUE ? -1 : result + 1);
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BOJ2206.solution();
    }
}


/*
    문제 분석
        1. Map
            - 맵은 N * M의 2차원 배열, 0은 이동 가능한 칸, 1은 벽
            - 시작 위치은 항상 (1,1), 종료 위치는 (N,M)
            - 본인은 시작 위치 (0,0), 종료 위치는 (N-1,M-1)로 설정
        2. 이동 조건
            - 상하좌우 인접한 칸으로 이동 가능(dx[], dy[] 배열 통해 생성)
            - 벽은 최대 1개 부술 수 있음(방문 처리 배열을 3차원으로 생성)
        3. 경로
            - 이동 횟수가 최소인 경로 찾기
            - 만약 (N-1,M-1)에 도달하지 못한다면 -1을 출력

    풀이
        1. 최단 경로를 구하는 문제 -> BFS 사용
            - 만약 종료 위치에 도달 했다면, 현재까지의 최단거리와 비교하여 작은값으로 갱신
            - 만약 현재 노드의 움직인 거리가 보유한 최단거리보다 크다면 continue로 넘김
        2. 상태 관리
            - 방문 처리 배열 2차원 + 벽 부셨는지 or 안부셨는지 -> 3차원 배열로 생성
        3. 위치 관리
            - 위치를 담은 내용을 Node Class를 생성해 관리
            - Node 클래스에는 x좌표, y좌표, 벽 부심 유무, 이동 횟수 저장
 */
