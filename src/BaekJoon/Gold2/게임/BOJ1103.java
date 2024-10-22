package BaekJoon.Gold2.게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1103 {

    static int N, M;
    static int[][] map, dp;
    static boolean[][] visited;
    static boolean flag = false;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int max = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                if(temp.charAt(j) == 'H') {
                    map[i][j] = 0;
                }else{
                    map[i][j] = temp.charAt(j) - '0';
                }
            }
        }

        visited[0][0] = true;
        compute(0, 0, 1);
        System.out.println(flag ? -1 : max);
    }

    static void compute(int x, int y, int cnt) {
        dp[x][y] = cnt;
        max = Math.max(max, cnt);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * map[x][y];
            int ny = y + dy[i] * map[x][y];
            if (isAvailable(nx, ny)) {
                if (cnt < dp[nx][ny]) {
                    continue;
                }
                if (visited[nx][ny]) {
                    flag = true;
                    return;
                }
                visited[nx][ny] = true;
                compute(nx, ny, cnt + 1);
                visited[nx][ny] = false;
            }
        }
    }

    static boolean isAvailable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M && map[x][y] != 0;
    }

    public static void main(String[] args) throws IOException {
        BOJ1103.solution();
    }
}

//처음 생각한 해결방법은 DFS를 사용해서 푸는 것이다.
//시작점이 0,0으로 고정되어 있으므로, 해당 좌표에서 문제의 조건 순서대로 진행하여 dp 배열에 저장하는 방식으로 구현했다.
//하지만 시간초과가 났는데, 가장 주된 이유는 4 ^ (50 * 50)의 경우의 수가 존재하므로 시간초과가 날수 밖에 없다.
//따라서 현재 진행한 횟수인 cnt와 dp값을 비교하여 중복 진행을 줄이는게 핵심이다.
//만약 현재 진행한 횟수인 cnt보다 진행할 다음 위치의 cnt인 dp[nx][ny]가 크다면, 기존에 진행한 코드에서 이미 해당 위치를 탐색했을때 더 큰 경우의 수를 찾았다는 의미이기 때문에,
//더 이상 탐색할 필요가 없어진다. 따라서 해당 경우에는 탐색을 진행하지 않음으로서 중복을 줄이게 된다.
