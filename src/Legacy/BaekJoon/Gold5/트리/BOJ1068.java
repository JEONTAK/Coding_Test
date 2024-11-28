package BaekJoon.Gold5.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1068 {

    static int N, D, R;
    static int[] node;
    static boolean[] visited;
    static int result = 0;


    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        node = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            node[i] = Integer.parseInt(st.nextToken());
            if (node[i] == -1) {
                R = i;
            }
        }
        D = Integer.parseInt(br.readLine());
        delete(D);
        compute(R);
        System.out.println(result);
    }

    static void delete(int cur) {
        node[cur] = -2;
        for (int i = 0; i < N; i++) {
            if (node[i] == cur) {
                delete(i);
            }
        }
    }

    static void compute(int cur){
        boolean flag = true;
        visited[cur] = true;
        if (node[cur] != -2) {
            for (int i = 0; i < N; i++) {
                if (node[i] == cur && !visited[i]) {
                    compute(i);
                    flag = false;
                }
            }
            if (flag) {
                result++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1068.solution();
    }
}
