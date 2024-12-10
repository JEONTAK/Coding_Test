package BaekJoon.Gold3.욕심쟁이판다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1937 {

    static int N, result = 0;
    static int[][] map, dp;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.max(result, dfs(i, j));
            }
        }
        System.out.println(result);
    }

    static int dfs(int x, int y) {
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isAvailable(nx, ny) && map[nx][ny] > map[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
            }
        }

        return dp[x][y];
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ1937.solution();
    }
}
/*
문제 분석
1. 정보
    - N * N 크기의 대나무 숲(1 <= N <= 500)
    - 판다는 해당 지역의 대나무를 모두 먹어 치우면 다음과 같이 이동
        - 상 하 좌 우 중 한 곳으로 이동
        - 단, 이전 지역보다 대나무가 많이 있는 곳으로만 이동 가능
    - 사육사는 판다를 N * N 크기의 대나무 숲안에 어디든 놓을 수 있음

2. 목표
    - 특정 지억에 판다를 놓았을 경우, 판다가 최대한 많은 칸을 이동하는 칸의 수의 최댓값을 출력

3. 제약 조건
    - 판다는 상 하 좌 우로 이동할 수 있지만, 이전 지역보다 대나무가 더 많은 곳으로만 이동할 수 있음

풀이
1. 알고리즘
    - DP 활용
        - dp[i][j] : (i,j)에서 시작 했을때 이동 가능한 최대 칸 수
    - DFS
        - DFS 사용하여 가능한 모든 이동 경로 탐색

2. 탐색 과정
    1. DFS를 활용하여 이동 할 수 있는 모든 칸을 탐색함
    2. 이동할떄, 해당 좌표의 dp[x][y]값이 존재 한다면, 해당 값을 메모이제이션 하여 연산을 줄임
    3. 모든 칸에서 DFS를 수행해고, 최대값을 갱신함.
 */