package BaekJoon.Gold2.가장긴증가하는부분수열3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12738 {

    static int N;
    static int[] arr, result;
    static int len = 1;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        result = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        result[0] = arr[0];
        for(int i = 1 ; i < N ; i++){
            int cur = arr[i];
            if (result[len - 1] < cur) {
                len++;
                result[len - 1] = cur;
            }else{
                int low = 0;
                int high = len;
                while(low < high){
                    int mid = (low + high)/2;
                    if (result[mid] < cur){
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
        BOJ12738.solution();
    }
}
//이전 가장 긴 증가하는 부분 수열 2와 동일하다.
//배열의 값의 범위만 -10억 ~ 10억으로 바뀌었는데, int 자료형을 써도 커버가 가능하기 때문에 코드진행이 동일하다.