package BaekJoon.Gold2.칵테일;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1033 {

    static class Node{
        int to;
        int p;
        int q;

        public Node(int to, int p, int q) {
            this.to = to;
            this.p = p;
            this.q = q;
        }
    }

    static int N;
    static ArrayList<Node>[] list;
    static boolean[] check;
    static long LCM = 1;
    static long[] result;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N];
        result = new long[N];
        for(int i = 0 ; i < N ; i++){
            list[i] = new ArrayList<>();
        }
        check = new boolean[N];
        for(int i = 1 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, p, q));
            list[b].add(new Node(a, q, p));
            LCM *= (p * q / gcd(p, q));
        }
        result[0] = LCM;
        compute(0);

        long GCD = result[0];
        for(int i = 1 ; i < N ; i++){
            GCD = gcd(GCD, result[i]);
        }
        for(int i = 0 ; i < N ; i++){
            System.out.print((result[i] / GCD) + " ");
        }
    }

    static void compute(int node){
        check[node] = true;
        for (Node next : list[node]) {
            if (!check[next.to]) {
                result[next.to] = result[node] * next.q / next.p;
                compute(next.to);
            }
        }
    }

    static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BOJ1033.solution();
    }
}
