package BaekJoon.Gold2.꼬인전깃줄;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1365 {

    static int N;
    static int[] wire, result;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        wire = new int[N];
        result = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            wire[i] = Integer.parseInt(st.nextToken());
        }
        int len = 1;
        result[0] = wire[0];
        for(int i = 1 ; i < N ; i++){
            int cur = wire[i];
            if (result[len - 1] < cur) {
                len++;
                result[len - 1] = cur;
            }else{
                int left = 0;
                int right = len;
                while(left < right){
                    int mid = (left + right) / 2;
                    if(result[mid] < cur){
                        left = mid + 1;
                    }else{
                        right = mid;
                    }
                }
                result[left] = cur;
            }
        }
        System.out.println(N - len);
    }

    public static void main(String[] args) throws IOException {
        BOJ1365.solution();
    }
}

//증가하는 부분수열과 동일한 알고리즘을 사용하였다.
//전기줄이 꼬이면 안된다는 뜻은 -> 전깃줄이 증가하는 수열의 최대값을 구하고, 해당 수열을 포함한 전깃줄을 제외한 나머지를 자르면 꼬이지 않게 된다는 의미이기 때문에,
//전체 N에서 최장 길이 수열의 길이인 len을 빼주면 결과값이 나오게 된다.