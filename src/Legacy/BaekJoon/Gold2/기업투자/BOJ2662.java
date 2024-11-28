package BaekJoon.Gold2.기업투자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2662 {

    static int N, M;
    static int[][] value, dp, path;
    static int[] result;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        value = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];
        path = new int[N + 1][M + 1];
        result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            for(int j = 1 ; j <= M ; j++){
                value[idx][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ; i <= M ; i++){
            for(int j = 0 ; j <= N ; j++){
                for (int k = N - j; k >= 0; k--) {
                    if (dp[k][i - 1] + value[j][i] > dp[j + k][i]) {
                        dp[j + k][i] = dp[k][i - 1] + value[j][i];
                        path[j + k][i] = j;
                    }
                }
            }
        }

        getPath(N, M);

        StringBuilder sb = new StringBuilder();
        sb.append(dp[N][M] + "\n");
        for (int i = 1; i <= M; i++) {
            sb.append(result[i] + " ");
        }
        System.out.println(sb.toString());
    }

    static void getPath(int n, int m) {
        if(m == 0)return;
        result[m] = path[n][m];
        getPath(n - result[m], m - 1);
    }

    public static void main(String[] args) throws IOException {
        BOJ2662.solution();
    }
}
//DP를 사용하여 구현하였다.
//먼저 최대 이익을 구하기 위한 조건을 정리해보면
//1. 현재 선택한 기업 i에 j만원을 투자했을때 최대 이익값
//2. 현재 기업 전까지 n - j만원을 투자했을때 최대 이익 + 현재 선택한 기업 i에 j만원을 투자했을 때의 이익값
//1과 2중 더 큰 값을 dp 배열에 저장하면 된다.
//->
//dp[투자금액][기업] : 0번 기업부터 현재 기업까지 투자금액을 투자했을때 얻을 수 있는 최대 이익 값
//value[투자금액][기업] : 기업에 투자금액을 투자할 시 얻을 수 있는 이익 값
//-> i = 기업, j : i 기업에 투자할 금액, k : 이전까지 지나온 기업의 투자한 금액의 합.
//dp[j + k][i] = Math.max(dp[j + k][i], dp[k][i - 1] + value[j][k])
//추가로 우리는 각 기업이 투자한 액수를 출력해주어야 한다.
//따라서 path[j + k][i] 에 j값을 저장하여 i 기업이 j + k의 금액중 투자한 금액이 j라는 것을 알 수 있게 해준다.
//위 path를 사용하여,
//result[기업] 배열을 만들어 재귀함수를 통해 각 기업당 투자한 금액을 저장하고 출력한다.
