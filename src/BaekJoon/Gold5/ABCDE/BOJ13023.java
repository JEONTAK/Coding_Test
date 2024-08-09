package BaekJoon.Gold5.ABCDE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ13023 {

    static int N, M;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            compute(i, 0);
        }
        System.out.println(0);
    }

    static void compute(int idx, int cnt) {
        if (cnt >= 4) {
            System.out.println(1);
            System.exit(0);
        }
        visited[idx] = true;
        for(int next : list[idx]) {
            if (!visited[next]) {
                compute(next, cnt + 1);
            }
        }
        visited[idx] =false;
    }

    public static void main(String[] args) throws IOException {
        BOJ13023.solution();
    }
}
