package BaekJoon.Gold4.연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {

    static class Node{
        int x;
        int y;
        int t;

        public Node(int x, int y, int t){
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    static int N, M;
    static int[][] map;
    static ArrayList<Node> list = new ArrayList<>();
    static int max = 0;
    static int notV = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    list.add(new Node(i, j, 0));
                } else if (map[i][j] == 0) {
                    notV++;
                }
            }
        }
        notV -= 3;
        setWall(0);
        System.out.println(max);
    }

    static void setWall(int cnt) {
        if (cnt == 3) {
            max = Math.max(max, compute());
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    setWall(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static int compute(){
        int count = notV;
        boolean[][] visited = new boolean[N][M];
        Queue<Node> q = new LinkedList<>();
        for (Node cur : list) {
            q.offer(cur);
            visited[cur.x][cur.y] = true;
        }
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isAvailable(nx, ny) && !visited[nx][ny] && map[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    count--;
                    q.add(new Node(nx, ny, cur.t + 1));
                }
            }
        }
        return count;
    }

    static boolean isAvailable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BOJ14502.solution();
    }

}
