package BaekJoon.Gold2.보석도둑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1202 {

    static class Jewel implements Comparable<Jewel>{
        int m;
        int v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) {
            if(this.m == o.m){
                return o.v - this.v;
            }
            return this.m - o.m;
        }
    }

    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    static ArrayList<Jewel> list = new ArrayList<>();
    static int[] bag;
    static int N, K;
    static long result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bag = new int[K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Collections.sort(list);
        Arrays.sort(bag);
        for (int i = 0 , j = 0; i < K; i++) {
            while(j < N && list.get(j).m <= bag[i]){
                pq.offer(list.get(j++).v);
            }

            if(!pq.isEmpty()){
                result += pq.poll();
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ1202.solution();
    }
}

//핵심
//보석에는 무게, 가격이 있음.
//해당 리스트를 무게의 오름차순으로, 무게가 같을 경우엔, 가격의 내림차순으로 배치
//가방에는 무게가 있고, 무게를 오름차순으로 배치
//가방 순서대로 해당 가방에 들어갈 수 있는 보석들을 list에서 가격 값을 pq에 넣어줌.
//여기서 list의 현재 위치를 알고 있어야 이후에 값을 중복해서 집어넣지 않을 수 있다.
//pq는 값을 내림차순으로 뽑게함 -> 가방 무게보다 작은 질량을 가진 보석들 중에서 가장 가격이 비싼 보석을 바로 가져올 수 있게됨.
//해당 방식을 가방 마지막 까지 반복 한 후 결과 출력
