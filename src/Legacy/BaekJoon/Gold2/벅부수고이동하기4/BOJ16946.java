package BaekJoon.Gold2.벅부수고이동하기4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16946 {

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] map, sum, result;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] visited;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        sum = new int[N][M];
        result = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }
        int idx = 1;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if (map[i][j] == 0 && visited[i][j] == 0) {
                    compute(i, j, idx++);
                }
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] == 1){
                    HashMap<Integer, Integer> m = new HashMap<>();
                    for(int k = 0 ; k < 4 ; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (isAvailable(nx, ny) && !m.containsKey(visited[nx][ny])) {
                            m.put(visited[nx][ny], sum[nx][ny]);
                        }
                    }
                    for (Integer value : m.values()) {
                        result[i][j] += value;
                    }
                    result[i][j] += 1;
                    result[i][j] = result[i][j] % 10;
                }
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }

    static void compute(int x, int y, int idx) {
        int cnt = 0;
        Queue<Node> q = new LinkedList<>();
        Queue<Node> setSum = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = idx;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            setSum.add(cur);
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isAvailable(nx, ny) && visited[nx][ny] == 0 && map[nx][ny] == 0) {
                    visited[nx][ny] = idx;
                    q.add(new Node(nx, ny));
                }
            }
        }

        while (!setSum.isEmpty()) {
            Node cur = setSum.poll();
            sum[cur.x][cur.y] = cnt;
        }
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BOJ16946.solution();
    }
}

//처음 시도한 방법
//0부터 N, 0부터 M까지 모든 좌표를 순서대로 탐색한다.
//만약 특정 좌표 (x,y)가 1이면, 해당 좌표에서 상, 하, 좌, 우로 움직여서 0이 이어진 곳을 다 더해 result에 저장한다.
//저장한 result 값을 출력한다.
//하지만 해당 방법은 1000 * 1000 * 4 * ... 이므로 시간초과가 난다.
//따라서 다음으로 시도할 방법은 다음과 같다.
//먼저 맵을 저장하고, 해당 맵에서 0들로 이어진 좌표의 합을 해당 좌표에 넣는다.
//추가로, 0들로 이어진 좌표의 뭉치를 idx를 사용하여 다른 배열에 저장해 놓는다.
//이후 0,0부터 N,M 까지 순서대로 해당 좌표의 값이 1인경우 상, 하, 좌, 우의 값들을 Hashmap으로 받는데,
//위에서 저장한 idx를사용하여 <idx, 0의 합> 쌍으로 저장한다.
//만약 idx가 겹친다면 건너뛰고 Hashmap에 저장된 값만 더해준다.
//이후1 을 더해주고 10을 나눠준다.
