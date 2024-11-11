package BaekJoon.Gold1.소수의곱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2014 {

    static int K, N;
    static long[] prime;
    static PriorityQueue<Long> pq = new PriorityQueue<>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        prime = new long[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < K ; i++){
            prime[i] = Integer.parseInt(st.nextToken());
            pq.add(prime[i]);
        }
        long result = 0;

        while(N -- > 0){
            result = pq.poll();

            for(int i = 0 ; i < K ; i++){
                if ((result * prime[i]) >= (long) 2 << 30) {
                    break;
                }
                pq.offer(result * prime[i]);

                if(result % prime[i] == 0){
                    break;
                }
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ2014.solution();
    }
}

