package BaekJoon.Gold2.가장긴증가하는부분수열2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12015 {

    static int N;
    static int[] arr;
    static int[] result;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        result = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        result[0] = arr[0];
        int len = 1;
        for (int i = 1; i < N; i++) {
            int cur = arr[i];
            if (result[len - 1] < cur) {
                len++;
                result[len - 1] = cur;
            }else{
                int low = 0;
                int high = len;
                while(low < high) {
                    int mid = (low + high) / 2;
                    if (result[mid] < cur) {
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
        BOJ12015.solution();
    }
}

//주어진 수열에서 증가수열의 최대 길이를 구하는 문제
//먼저 현재 값이 증가수열의 마지막 값보다 크다면 증가수열에 그대로 추가해 주면 된다.
//하지만 현재 값이 증가수열의 마지막 값보다 작은경우가 중요하다.
//증가수열의 마지막 값보다 작은 경우에는, 이분 탐색을 통해 현재 값보다 큰 값 중 가장 가까운 값을 찾는다.
//해당 방식을 사용하면 수의 간격이 최소화 되도록 할 수 있다.
//해당 경우가 중요한 이유는 다음 예제와 같다.
//{10, 20, 30, 15, 20, 30, 50, 40, 45 ,60}
//해당 배열에서, 50을 40으로 바꿔주지 않았다면, 45가 들어오지 못해 가장 긴 배열을 구할 수 없다.
//따라서 현재 값보다 큰 값 중 현재 값과 가장 가까운 값과 바꾸게 되면,
//앞선 수와의 간격이 적어지게 되고 더 많은 수가 수열에 들어오도록 할 수 있게 된다.
