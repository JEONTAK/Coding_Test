package BaekJoon.Gold2.회의준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2610 {

    static class Node{
        int e;
        int d;

        public Node(int e, int d) {
            this.e = e;
            this.d = d;
        }
    }

    static int N, M;
    static ArrayList<Integer>[] list;
    static HashMap<Integer, Integer> map = new HashMap<>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }

        for(int i = 1; i <= N ; i++){
            compute(i);
        }

        System.out.println(map.size());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry.getKey());
        }
        while(!pq.isEmpty()){
            System.out.println(pq.poll());
        }
    }

    static void compute(int start){
        Queue<Node> q = new LinkedList<>();
        ArrayList<Integer> p = new ArrayList<>();
        int max = 0;
        boolean[] visited = new boolean[N + 1];
        q.add(new Node(start, 0));
        visited[start] = true;
        while(!q.isEmpty()){
            Node cur = q.poll();
            p.add(cur.e);
            max = Math.max(max, cur.d);

            for (Integer next : list[cur.e]) {
                if(!visited[next]){
                    visited[next] = true;
                    q.add(new Node(next, cur.d + 1));
                }
            }
        }

        boolean flag = true;
        for (Integer cur : p) {
            if(map.containsKey(cur)){
                flag = false;
                if(map.get(cur) > max){
                    map.remove(cur);
                    map.put(start, max);
                    return;
                }
            }
        }
        if(flag){
            map.put(start, max);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ2610.solution();
    }
}

//해당 문제는 1번부터 N번까지, 큐를 사용하여 그래프 전체를 도는데 필요한 시간 및 그래프에 포함 되어있는 노드의 idx를 구하고,
//HashMap을 만들어 노드 번호, 해당 노드가 포함된 그래프를 도는데 필요한 시간을 저장한다.
//만약 그레프에 노드가 포함되어 있고, 현재 그래프를 도는데 필요한 시간이 해당 노드를 돌았을때 필요한 시간보다 작다면,
//HashMap에서 해당 노드가 들어있는 값을 지우고, 현재 노드, 현재 필요한 시간을 저장해 업데이트 해준다.
//1번 부터 N번까지 반복하면 HashMap에는 연결되어 있는 그래프에서 가장 시간이 적은 노드의 번호들만 들어있게 된다.
//해당 키값들을 우선순위 큐를 통해 오름차순으로 출력해주면 된다.