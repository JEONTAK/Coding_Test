package BaekJoon.Gold1.놀이공원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1561 {

    static Long N;
    static int M;
    static int[] arr;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (N <= M) {
            System.out.println(N);
            return;
        }

        long result = compute();
        long child = getChild(result - 1);
        for(int i = 1 ; i <= M ; i++){
            if (result % arr[i] == 0) {
                child++;
            }
            if (child == N) {
                System.out.println(i);
                break;
            }
        }
    }

    static long getChild(long t){
        long childN = M;
        for(int i = 1 ; i <= M ; i++){
            childN += t / arr[i];
        }
        return childN;
    }

    static long compute(){
        long left = 0;
        long right = N * 30;
        while (left <= right) {
            long mid = (left + right) / 2;
            long childN = getChild(mid);
            if(childN < N){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BOJ1561.solution();
    }
}

