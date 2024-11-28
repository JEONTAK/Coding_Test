package BaekJoon.Gold2.반도체설계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2352 {

    static int N;
    static int[] line, result;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        line = new int[N];
        result = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            line[i] = Integer.parseInt(st.nextToken());
        }
        result[0] = line[0];
        int len = 1;
        for(int i = 1 ; i < N ; i++){
            int cur = line[i];
            if (result[len - 1] < cur) {
                len++;
                result[len - 1] = cur;
            }else{
                int low = 0;
                int high = len - 1;
                while(low < high){
                    int mid = (low + high) / 2;
                    if(result[mid] < cur){
                        low = mid + 1;
                    }else{
                        high = mid;
                    }
                }
                result[low] = cur;
            }
        }
        System.out.println(len);
    }

    public static void main(String[] args) throws IOException {
        BOJ2352.solution();
    }
}

//가장 긴 증가하는 부분수열 문제와 동일하다.
