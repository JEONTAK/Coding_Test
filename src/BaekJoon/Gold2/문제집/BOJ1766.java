package BaekJoon.Gold2.문제집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1766 {

    static int N, M;
    static int[] degree;
    static ArrayList<Integer>[] list;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        degree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            degree[b]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if(degree[i] == 0){
                pq.add(i);
            }
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();
            sb.append(cur + " ");
            for (int next : list[cur]) {
                degree[next]--;
                if(degree[next] == 0){
                    pq.add(next);
                }
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ1766.solution();
    }
}

//위상정렬과 우선순위 큐를 사용하면 된다.
//먼저 흔히 그래프형식의 배열을 받을 때 사용하는 ArrayList<integer>[] list 형식에 그래프를 저장한다
//-> 해당 배열은 시작점과 이어지는 도착점들을 저장해놓는다
//또한 추가로 degree[] 배열을 만들어 degree[도착점]에 + 1해준다.
//이후에 degree를 -1 하면서 0이되면, 해당 문제를 풀기 전에 필요한 모든 문제를 푼것으로 간주하고, 우선순위 큐에 집어 넣을수 있게 하기 위함이다.
//먼저 degree가 0인 인덱스들을 pq에 집어넣는다.
//pq에서 인덱스를 뽑고, 해당 인덱스와 이어져 있는 도착점들의 degree를 -1 해준다.
//이후 degree가 0일 경우 해당 인덱스도 pq에 넣어준다.
//결과값을 출력해주면 끝!