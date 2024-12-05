package BaekJoon.Gold3.내리막길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2206 {

    static int N, M;
    static int[][] map, dp;
    static int[] dx = {-1,1, 0, 0};
    static int[] dy = {0,0, 1, -1};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int x, int y) {

        if (x == M - 1 && y == N - 1) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;

        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isAvailable(nx, ny) && map[nx][ny] < map[x][y]) {
                dp[x][y] += dfs(nx, ny);
            }
        }
        return dp[x][y];
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ2206.solution();
    }
}

/*
    문제 분석
        1. Map
            - M * N 크기
        2. 이동 규칙
            - 현재 위치에서 상 하 좌 우로 이동 가능
            - 현재 위치의 크기보다 더 작은 지점으로만 이동 가능
            - 시작 위치은 항상 (1,1), 종료 위치는 (M,N)
            - 본인은 시작 위치 (0,0), 종료 위치는 (M-1,N-1)로 설정
        3. 목표
            - 시작 위치 (0,0)에서 종료 위치 (M-1,N-1)까지 이동 규칙에 맞게 이동하는 경로의 개수를 구함

    풀이
        1. DFS + Memoization
            - DFS : 더 낮은 칸 따라 경로 탐색
            - Memoization : 특정 위치에서 종료 위치까지의 가능한 경로의 개수 미리 저장
        2. 탐색 과정
            1. 상 하 좌 우 이동
            2. 종료 조건
                - 현재 위치가 종료 위치(M-1,N-1)이면 경로 개수 1 반환
            3. Memoization
                - 이미 (x,y)의 경로 개수가 계산 되었으면 해당 값 사용
            4. 재귀 호출
                - (x,y)에서 이동 가능한 모든 칸에 대해 재귀 호출
                - 경로 개수 누적해서 반환
 */
