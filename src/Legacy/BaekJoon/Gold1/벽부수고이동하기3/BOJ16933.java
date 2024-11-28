package BaekJoon.Gold1.벽부수고이동하기3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16933 {

    static class Node{
        int x;
        int y;
        int d;
        int k;
        boolean isDay;

        public Node(int x, int y, int d, int k, boolean isDay) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
            this.isDay = isDay;
        }
    }

    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static int result = -1;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K + 1];
        for(int i = 0 ; i < N ; i++){
            String temp = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = temp.charAt(j) - '0';
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0, true));
        visited[0][0][0] = true;
        while(!q.isEmpty()){
            Node cur = q.poll();
            if (cur.x == N - 1 && cur.y == M - 1) {
                result = cur.d;
                break;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(!isAvailable(nx, ny)) continue;

                if (map[nx][ny] == 0) {
                    if (!visited[nx][ny][cur.k]) {
                        q.add(new Node(nx, ny, cur.d + 1, cur.k, !cur.isDay));
                        visited[nx][ny][cur.k] = true;
                    }
                }
                else{
                    if (cur.k < K && cur.isDay) {
                        if (!visited[nx][ny][cur.k + 1]) {
                            q.add(new Node(nx, ny, cur.d+ 1 , cur.k + 1, !cur.isDay));
                            visited[nx][ny][cur.k + 1] = true;
                        }
                    } else if (cur.k < K && !cur.isDay) {
                        q.add(new Node(cur.x, cur.y, cur.d + 1, cur.k, !cur.isDay));
                    }
                }
            }
        }
        System.out.println(result);
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BOJ16933.solution();
    }
}
//벽부수고 이동하기 2의 심화버전이다.
//처음에는 한 시도는 다음 위치로 갈떄, 1번 가서 낮과 밤이 바뀌거나, 1번 간 후 1번 제자리에 있게해 낮과 밤이 그대로 이어졌을 경우를 queue에 넣었다.
//하지만 이렇게하니 시간초과과 난다.(당연히 최소 경로를 구하는건데, 굳이 필요하지 않은 연산인 다음 위치로 갈떄 벽이 아닐경우엔 1번 간 후 1번 제자리하는 연산이 불필요하기 때문이다)
//따라서 다음과 같이 시도하였다.
//다음 좌표가 벽이 아닐경우에는, 이동 횟수 + 1 및 낮과 밤이 바뀐 변수를 queue에 추가해준다.
//다음 좌표가 벽일 경우
//  벽울 부술 수 있고, 낮이라면
//      만약 아직 방문하지 않았다면, 이동 횟수 + 1, 벽 부순 횟수 + 1, 낮과 밤 바꿔 queue에 추가
// 벽을 부술 수 있고, 밤이라면
//      제자리에서 하루 밤새야됨 -> 현재 자리, 이동횟수 + 1, 벽 부순횟수 그대로, 낮과 밤 바꿔 queue에 추가
//해당 방식으로 시도하면 아슬아슬하게 통과한다.
