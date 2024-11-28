package BaekJoon.Gold2.줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7570 {

    static int N;
    static int[] dp;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp = new int[N + 1];
        for(int i = 0 ; i < N ; i++){
            int cur = Integer.parseInt(st.nextToken());
            dp[cur] = dp[cur - 1] + 1;
        }

        Arrays.sort(dp);
        System.out.println(N - dp[N]);
    }

    public static void main(String[] args) throws IOException {
        BOJ7570.solution();
    }
}

//생각하기 어려운 문제다.
//일단 예시를 보자면,
//5 4 2 1 3 이 주어졌다고 가정하자.
//해당 배열에서는 2 3 은 연속적인 배열이라고 할 수 있다.
//즉, 2와 3은 움직이지 않아도 된다는 뜻이다.
//따라서 5개중 2 3을 제외한 나머지는 움직여야 한다는 뜻이므로 전체 N에서 2를 뺀 값이 정답이 될것이다.
//그렇다면 해당 값은 어떻게 구해야 할까?
//5 4 2 1 3이 순서대로 들어온다고 가정했을떄,
//dp[5]는 dp[4]에 1을 더해준다.
//dp[4]는 dp[3] + 1을 해준다
//마찬가지로 모든 수에 대하여 dp[cur] = dp[cur - 1] + 1을 해주면
//dp 배열은 다음과 같이 나온다
//0 1 2 3 4 5
//0 1 1 2 1 1
//위 dp배열의 뜻은 현재 숫자 cur이 나왔을때, cur - 1을 포함하고, 증가하는 수열의 길이를 담은것이라 생각하면 된다.
//따라서 숫자 3은 2의 길이를 가지는 증가하는 수열이므로,
//해당 수열의 길이인 2개를 제외한 나머지는 정렬을 해주어야 된다는 뜻이 된다.
//따라서 dp 배열을 정렬하고, N 에서 dp[N]을 빼면 우리가 원하는 결과값이 나오게 된다.