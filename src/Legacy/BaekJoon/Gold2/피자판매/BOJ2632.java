package BaekJoon.Gold2.피자판매;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ2632 {

    static int S, A, B;
    static int[] pa, pb;
    static int result = 0;
    static HashMap<Integer, Integer> hm = new HashMap<>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        pa = new int[A * 2 + 1];
        pb = new int[B * 2 + 1];
        for (int i = 1; i <= A; i++) {
            pa[i] = pa[A + i] = Integer.parseInt(br.readLine());
        }
        for(int i = 1 ; i <= B; i++){
            pb[i] = pb[B + i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1 ; i <= A * 2 ; i++){
            pa[i] += pa[i - 1];
        }
        for(int i = 1 ; i <= B * 2 ; i++){
            pb[i] += pb[i - 1];
        }

        if(pa[A] == S){
            result++;
        }
        hm.put(pa[A], 1);
        for(int i = 1 ; i <= A ; i++){
            for(int j = i ; j < i + A - 1 ; j++){
                int sum = pa[j] - pa[i - 1];
                if(sum == S)result++;
                if(sum >= S) continue;
                hm.put(sum, hm.getOrDefault(sum, 0) + 1);
            }
        }

        if(pb[B] == S){
            result++;
        }
        result += hm.getOrDefault(S - pb[B], 0);

        for(int i = 1 ; i <= B ; i++){
            for(int j = i ; j < i + B - 1 ; j++){
                int sum = pb[j] - pb[i - 1];
                if(sum == S)result++;

                result += hm.getOrDefault(S - sum, 0);
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ2632.solution();
    }
}