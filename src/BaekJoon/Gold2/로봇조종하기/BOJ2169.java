package BaekJoon.Gold2.로봇조종하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2169 {

    static int N, M;
    static int[][] map, dp, temp;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N][M];
        temp = new int[2][M];
        dp[0][0] = map[0][0];
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + map[0][i];
        }

        for(int i = 1 ; i < N ; i++){
            //i번쨰 행 임시 배열
            temp[0][0] = dp[i - 1][0] + map[i][0];
            //왼쪽과 위쪽 비교해서 더 큰값 + 현재 위치 값 저장
            for(int j = 1 ; j < M ; j++){
                temp[0][j] = Math.max(temp[0][j - 1], dp[i - 1][j]) + map[i][j];
            }
            temp[1][M - 1] = dp[i - 1][M - 1] + map[i][M - 1];
            //오른쪽과 위쪽 비교해서 더 큰값 + 현재 위치 값 저장
            for(int j = M - 2 ; j >= 0 ; j--){
                temp[1][j] = Math.max(temp[1][j + 1], dp[i - 1][j]) + map[i][j];
            }

            //왼쪽에서 온 값 vs 오른쪽에서 온 값 중 최대값 저장
            for(int j = 0 ; j < M ; j++){
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
        }
        System.out.println(dp[N - 1][M - 1]);
    }

    public static void main(String[] args) throws IOException {
        BOJ2169.solution();
    }
}

//문제 풀이 방법
//dp를 사용해 풀어야 하는 문제였다.
//첫번째 줄은 왼 -> 오 방향으로만 진행 가능하기 때문에, 해당 방향만 계산하여 dp배열에 넣어준다.
//두번쨰 줄부터는 왼쪽, 위, 오른쪽 총 3방향에서 오는 것의 최대값을 구해야한다.
//하지만 오른쪽 방향에서 오는 것은 갱신되지 않은 상태이기때문에 활용할 수 없다.
//따라서 해당 위치의 최댓값을 구하려면, 왼쪽 -> 오른쪽 진행시 구해지는 최댓값과
//오른쪽 -> 왼쪽으로 진행시 구해지는 최댓값을 비교하여, 둘중 더 큰 값을 dp배열에 저장해주면 된다.