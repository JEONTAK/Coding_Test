package BaekJoon.Gold5.상어초등학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ21608 {

    static class Student{
        int idx;
        int[] like;

        public Student(int idx, int[] like) {
            this.idx = idx;
            this.like = like;
        }
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int b;
        int f;

        public Node(int x, int y, int b, int f) {
            this.x = x;
            this.y = y;
            this.b = b;
            this.f = f;
        }

        @Override
        public int compareTo(Node o) {
            if (this.f == o.f) {
                if (this.b == o.b) {
                    if (this.x == o.x) return this.y - o.y;
                    return this.x - o.x;
                }
                return o.b - this.b;
            }
            return o.f - this.f;
        }
    }

    static int N;
    static int[][] map;
    static ArrayList<Student> list = new ArrayList<>();
    static int result = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int[] like = new int[4];
            for (int j = 0; j < 4; j++) {
                like[j] = Integer.parseInt(st.nextToken());
            }
            list.add(new Student(idx, like));
            compute(idx, like);
        }

        for (int i = 0; i < N * N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k] == list.get(i).idx) {
                        int f = 0;
                        for (int l = 0; l < 4; l++) {
                            int nx = j + dx[l];
                            int ny = k + dy[l];
                            if (isAvailable(nx, ny)) {
                                for (int m = 0; m < 4; m++) {
                                    if (list.get(i).like[m] == map[nx][ny]) {
                                        f++;
                                        break;
                                    }
                                }
                            }
                        }
                        if (f != 0) {
                            result += Math.pow(10, f - 1);
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    static void compute(int idx, int[] like) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    Node node = setPlace(i, j, idx, like);
                    pq.add(node);
                }
            }
        }
        Node cur = pq.poll();
        map[cur.x][cur.y] = idx;
    }

    static Node setPlace(int x, int y, int idx, int[] like) {
        int b = 0;
        int f = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isAvailable(nx, ny)) {
                if(map[nx][ny] == 0){
                    b++;
                    continue;
                }
                for (int j = 0; j < 4; j++) {
                    if (map[nx][ny] == like[j]) {
                        f++;
                        break;
                    }
                }
            }
        }
        Node cur = new Node(x, y, b, f);
        return cur;
    }

    static boolean isAvailable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ21608.solution();
    }
}
