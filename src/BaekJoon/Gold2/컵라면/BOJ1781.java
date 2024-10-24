package BaekJoon.Gold2.컵라면;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1781 {

    static class Problem implements Comparable<Problem> {

        int day;
        int ramen;

        public Problem(int day, int ramen) {
            this.day = day;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Problem o) {
            if(this.day == o.day){
                return o.ramen - this.ramen;
            }
            return o.day - this.day;
        }
    }

    static int N;
    static final int maxDay = 200_000;
    static Problem[] list;
    static long result = 0;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        list = new Problem[N];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            list[i] = new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(list);
        for(int i = maxDay, j = 0; i > 0 ; i--){
            while (j < N && list[j].day >= i) {
                pq.add(list[j++].ramen);
            }
            if(!pq.isEmpty()){
                result += pq.poll();
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ1781.solution();
    }
}

//BOJ1202 보석도둑과 유사한것 같다.
//하지만 정렬방식이 조금다르다.
//보석도둑은 무게 오름차순, 가치 내림차순으로 정렬했다면
//컵라면은 데드라인 내림차순, 라면 개수 내림차순으로 정렬했다.
//그 이유는 다믕과 같다.
//우리는 최대한 많은 컵라면을 얻어야 한다.
//하지만 데드라인이 가장 작은 것부터 차례대로 구하면, 그때마다 일일히 비교해주어야 하기 때문에 시간초과가 날것이다.
//여기서 내림차순으로 구성하여 날짜가 가장 큰 200000일부터 1일까지 내려오면서 비교한다면,
//해당 일수 까지 풀 수 있는 문제의 컵라면 개수를 얻을 수 있게 된다.
//해당 컵라면 개수를 우선순위 큐에 저장하고 사용하게 될텐데,
//날짜 오름차순으로 구성한다면 우선순위 큐를 사용할 수 없겠지만
//내림차순으로 구성한다면 해당 날짜까지 문제를 풀 수 있기 때문에, 우선순위 큐에 넣어놔도 아무런 지장이 없게 된다.
//따라서 우선순위 큐에 컵라면의 개수를 저장하고, 날짜가 하루씩 지날때마다 컵라면 개수 저장, 결과값에 컵라면 개수 중 가장 큰것 추가
//방식으로 1일차까지 구하면 된다.