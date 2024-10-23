package BaekJoon.Gold2.교환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1039 {

    static class Number{
        int num;
        int cnt;

        public Number(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    static int K;
    static int N;
    static int max = -1;
    static boolean[][] visited;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        compute();
        System.out.println(max);
    }

    static void compute() {
        Queue<Number> q = new LinkedList<>();
        visited = new boolean[1000001][K + 1];

        q.add(new Number(N, 0));
        while(!q.isEmpty()) {
            Number cur = q.poll();
            if(cur.cnt == K){
                max = Math.max(max, cur.num);
                continue;
            }

            int len = String.valueOf(cur.num).length();

            for(int i = 0 ; i < len -1 ; i++){
                for(int j = i + 1 ; j < len ; j++){
                    int next = swap(cur.num, i, j);

                    if (next != -1 && !visited[next][cur.cnt + 1]) {
                        q.add(new Number(next, cur.cnt + 1));
                        visited[next][cur.cnt + 1] = true;
                    }
                }
            }
        }
    }

    static int swap(int n, int x, int y) {
        char[] num = String.valueOf(n).toCharArray();
        if (x == 0 && num[y] == '0') {
            return -1;
        }

        char temp = num[x];
        num[x] = num[y];
        num[y] = temp;
        return Integer.parseInt(String.valueOf(num));
    }

    public static void main(String[] args) throws IOException {
        BOJ1039.solution();
    }
}
