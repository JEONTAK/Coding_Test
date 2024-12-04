package BaekJoon.Level3.말이되고픈원숭이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600 {

    static class Node{
        int x;
        int y;
        int d;
        int k;

        public Node(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }

    static int K, H, W, result = -1;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[] hx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] hy = {1, 2, 2, 1, -1, -2, -2, -1};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W][K + 1];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == H - 1 && cur.y == W - 1) {
                result = cur.d;
                break;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isAvailable(nx, ny) && !visited[nx][ny][cur.k] && map[nx][ny] == 0) {
                    visited[nx][ny][cur.k] = true;
                    q.add(new Node(nx, ny, cur.d + 1, cur.k));
                }
            }

            if (cur.k < K) {
                for(int i = 0 ; i < 8 ; i++){
                    int nx = cur.x + hx[i];
                    int ny = cur.y + hy[i];

                    if (isAvailable(nx, ny) && !visited[nx][ny][cur.k + 1] && map[nx][ny] == 0) {
                        visited[nx][ny][cur.k + 1] = true;
                        q.add(new Node(nx, ny, cur.d + 1, cur.k + 1));
                    }
                }
            }
        }
        System.out.println(result);
    }

    static boolean isAvailable(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }

    public static void main(String[] args) throws IOException {
        BOJ1600.solution();
    }
}

/*
문제 분석
    1. 정보
        - Map 크기 : H * W (1 <= W,H <= 200)
        - 말과 같은 형식으로 움직일 수 있는 횟수 : K
        - 미로의 구성
            - 1 : 장애물
            - 0 : 평지
        - 이동 규칙
            - 시작지점 : (0,0)
            - 도착지점 : (H - 1 , W - 1)
            - 원숭이는 상 하 좌 우 움직일 수 있음.
            - 추가로 K번 만큼 체스의 말과 같은 형식으로 움직일 수 있음

    2. 목표
        - 원숭이가 시작지점에서 도착지점까지 이동하는데 걸리는 최소 동작수
        - 만약 시작점에서 도착점까지 갈 수 없는 경우엔 -1을 출력

    3. 제약 조건
        - 장애물(1)은 상 하 좌 우로 이동할 경우 이동 불가
        - 만약 말과 같은 형식으로 이동할 경우 장애물을 넘어갈 수는 있음.(장애물 칸으로 이동은 불가)

풀이
    1. BFS 활용
        - (0,0)에서 시작하여 상 하 좌 우 및 말의 이동 형식으로 이동
        - 방문체크를 3차원으로 진행
            - visited[x][y][k] : (x,y)에 남은 말 이동 횟수 k로 도달했는지 여부

    2. 탐색 과정
        1. 시작점(0,0)을 큐에 삽입하여 BFS 탐색 시작
        2. 이동
            - 일반 이동 : 상 하 좌 우
            - 말 이동 : 체스의 나이트와 같이
                - 말 이동시 k + 1로 상태 갱신
        3. 종료 조건
            - 도착지점(H-1,W-1)에 도달하면 현재까지의 동작 횟수 반환
            - BFS 탐색 종료 시에도 도달하지 못하면 -1 출력

    3. 시간 복잡도
        위치 : H * W
        말 이동 횟수 : K + 1
        총 상태 : H * W * (K + 1)
        O(H*W*K)
 */