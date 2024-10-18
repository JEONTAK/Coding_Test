package BaekJoon.Gold2.빵집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ3109 {

    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static int result = 0;
    static boolean flag;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        for (int i = 0; i < R; i++) {
            flag = false;
            compute(i, 0);
        }
        System.out.println(result);
    }

    static void compute(int x, int y) {
        if (y == C - 1) {
            result++;
            flag = true;
            visited[x][y] = true;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isAvailable(nx, ny) && !visited[nx][ny] && map[nx][ny] == '.') {
                compute(nx, ny);
                visited[x][y] = true;
                if(flag){
                    return;
                }
            }
        }
    }

    static boolean isAvailable(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) throws IOException {
        BOJ3109.solution();
    }
}

//기존 DFS를 사용하여 파이프라인을 왼쪽부터 오른쪽까지 가게 구성
//단 오른쪽 위 , 중간 , 아래 순서로 우선순위룰 둬야함. -> 맨 위부터 시작하기 때문에, 최대한 많은 파이프를 연결하려면 위쪽에서 시작한 파이프는 최대한 위쪽에서 붙어서 가야함.
//파이프 연결이 성공했으면 해당 위치 방문을 true로 바꿈. 또한 flag를 만들어 파이프를 연결했다는 의미로 사용.
//flag == true -> 파이프가 연결 되었으므로, 해당 행에서는 더이상 볼 필요가 없음. 따라서 이전 compute로 돌아가는 과정에서 방문을 true로 설정하고 초기까지 돌아감.
//flag = false로 설정하고 다음 행을 실행 -> 반복함.

