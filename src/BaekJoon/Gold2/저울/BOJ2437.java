package BaekJoon.Gold2.저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2437 {

    static int N;
    static int[] weight;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(weight);

        int sum = 0;
        for(int i = 0 ; i < N ; i++){
            if(sum + 1 < weight[i]){
                break;
            }
            sum += weight[i];
        }
        System.out.println(sum + 1);
    }

    public static void main(String[] args) throws IOException {
        BOJ2437.solution();
    }
}
//해결 방법은 우선 무게추를 크기순으로 정렬한 후, 누적합을 구해 해당 누적합 + 1보다 다음 무게추의 크기가 크다면 누적합 + 1이 측정할수 없는 무게의 최솟값이 된다.
//예를 들어, 1 1 2 3 10이 주어졌을 경우
//무게추가 1 1을 사용할 경우 누적합은 2이고 1과 2를 만들 수 있다.
//2까지 사용할 경우 누적합은 4이고, 1 2 3 4 를 다 만들 수 있다.
//3까지 사용할 경우 누적합은 7이고 1 ~ 7까지는 다 만들 수 있다.
//하지만 7 + 1 < 10 이므로 8은 만들 수 없는 수가 되는 것이다.
