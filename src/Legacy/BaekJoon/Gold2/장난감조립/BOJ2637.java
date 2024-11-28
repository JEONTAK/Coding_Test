package BaekJoon.Gold2.장난감조립;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2637 {

    static class Node{
        int idx;
        long need;

        public Node(int idx, long need) {
            this.idx = idx;
            this.need = need;
        }
    }

    static int N, M;
    static ArrayList<Node>[] list;
    static long[] needs;
    static int[] degree;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        degree = new int[N + 1];
        for(int i = 1 ; i <= N ; i++){
            list[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            list[x].add(new Node(y, k));
            degree[y]++;
        }

        needs = new long[N + 1];
        compute(N);

        for(int i = 1 ; i <= N ; i++){
            if(list[i].isEmpty()){
                System.out.println(i + " " + needs[i]);
            }
        }
    }

    static void compute(int start) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 1));
        needs[start] = 1;
        while(!q.isEmpty()){
            Node cur = q.poll();

            for (Node next : list[cur.idx]) {
                needs[next.idx] += needs[cur.idx] * next.need;
                degree[next.idx]--;
                if(degree[next.idx] == 0){
                    q.add(new Node(next.idx, needs[next.idx]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ2637.solution();
    }
}

//위상 졍렬 및 BFS를 사용하어 풀었다..
//먼저 Node라는 class를 생성하고 배열형식을 저장하였다.
//해당 부품에서 필요한 부품 idx와 부품의 개수 need를 저장하도록 하였다.
//또한 degree 배열을 생성하여 필요한 부품에 + 1을 해주도록 하였다.
//큐에는 완제품인 N과 해당 부품의 개수인 1을 넣어 큐를 돌렸다.
//해당 부품에서 필요한 부품 및 개수를 list에서 받아오고, needs라는 배열에 현재 부품의 개수 * 필요한 부품의 개수의 값을 더해주었다.
//또한 degree[필요한 부품]을 - 1 해주어 만약 해당 값이 0이 된다면, 필요한 부품의 모든 개수를 알았기 때문에 큐에 저장해 해당 부품을 만들 수 있도록 계산해준다.
