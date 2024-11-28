package BaekJoon.Gold5.트리와쿼리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15681 {

    static int N, R, Q;
    static int[] parent, size;
    static ArrayList<Integer>[] list, tree;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        size = new int[N + 1];
        list = new ArrayList[N + 1];
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }

        makeTree(R, -1);
        countSubtreeNodes(R);

        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            sb.append(size[query] + "\n");
        }
        System.out.println(sb.toString());
    }

    static void makeTree(int curNode, int parentNode) {
        for (int node : list[curNode]) {
            if (node != parentNode) {
                tree[curNode].add(node);
                parent[node] = curNode;
                makeTree(node, curNode);
            }
        }
    }

    static void countSubtreeNodes(int curNode) {
        size[curNode] = 1;
        for (int node : tree[curNode]) {
            countSubtreeNodes(node);
            size[curNode] += size[node];
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ15681.solution();
    }
}
