package BaekJoon.Gold5.집합의표현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1717 {

    static int N, M;
    static int[] parent;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (o == 0) {
                union(a, b);
            }else{
                if (find(a) != find(b)) {
                    sb.append("NO\n");
                }else{
                    sb.append("YES\n");
                }
            }
        }
        System.out.println(sb.toString());
    }

    static int find(int x) {
        if(x == parent[x])return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx < ry) {
            parent[ry] = rx;
        }else{
            parent[rx] = ry;
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1717.solution();
    }
}
