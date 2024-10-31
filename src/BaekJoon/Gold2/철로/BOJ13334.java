package BaekJoon.Gold2.철로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ13334 {

    static class Node implements Comparable<Node> {
        int s;
        int e;

        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node o) {
            if(this.e == o.e){
                return this.s - o.s;
            }
            return this.e - o.e;
        }
    }

    static int N, D;
    static ArrayList<Node> list = new ArrayList<Node>();
    static int result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (s > e) {
                list.add(new Node(e, s));
            }else{
                list.add(new Node(s, e));
            }
        }
        D = Integer.parseInt(br.readLine());
        Collections.sort(list);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(list.get(0).s);
        int count = 1;
        for(int i = 1 ; i < list.size() ; i++){
            if (list.get(i).e - list.get(i).s > D) {
                continue;
            }

            while(!pq.isEmpty() && list.get(i).e - pq.peek() > D){
                count--;
                pq.poll();
            }

            count++;
            pq.add(list.get(i).s);
            result = Math.max(result, count);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ13334.solution();
    }
}
//정렬과 우선순위큐를 활용한 문제였다.
//항상 우선순위큐 및 정렬과 관련된 알고리즘을 풀면서 느끼는점은, 어떻게 정렬할것인지가 제일 중요한 것 같다.
//해당 문제에서는 오른쪽 점 오름차순, 왼쪽 점 오름차순 순으로 정렬하였다.
//정렬 이후에,
//값을 하나씩 뽑아 해당 값에서 오른쪽과 왼쪽 값의 차이가 D보다 크면 넘긴다.
//만약 D보다 작거나 같다면, 현재 우선순위 큐에 저장되어 있는 값과 뽑은 값의 오른쪽 값과 비교해 D보다 작거나 같을떄까지 큐에서 제거한다.
//우선순위 큐는 Integer 형식으로, 왼쪽값들이 저장되어 있다.
//D의 범위 안으로 값이 들어왔다면, 현재 값의 왼쪽점을 pq에 저장하고, 현재 큐에 들어있는 크기와 결과 값중 큰 값을 결과값으로 저장한다.

