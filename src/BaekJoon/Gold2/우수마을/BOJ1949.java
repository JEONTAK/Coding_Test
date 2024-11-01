package BaekJoon.Gold2.우수마을;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1949 {

    static int N;
    static ArrayList<Integer>[] list;
    static int[] people;
    static int[][] dp;
    static boolean[] visited;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N + 1];
        list = new ArrayList[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];
        for(int i = 1 ; i <= N ; i++){
            list[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N ; i++){
            people[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0 ; i < N - 1 ;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }
        for(int i = 1 ; i <= N ; i++){
            Arrays.fill(dp[i], -1);
        }
        int result = Math.max(compute(1, 0), compute(1, 1) + people[1]);
        System.out.println(result);
    }

    static int compute(int cur, int p) {
        if(list[cur].isEmpty()) return 0;
        if(dp[cur][p] != -1) return dp[cur][p];

        visited[cur] = true;
        dp[cur][p] = 0;
        for (Integer next : list[cur]) {
            if(!visited[next]) {
                if (p == 1) {
                    dp[cur][p] += compute(next, 0);
                }else{
                    dp[cur][p] += Math.max(compute(next, 0), compute(next, 1) + people[next]);
                }
            }
        }
        visited[cur] = false;
        return dp[cur][p];
    }

    public static void main(String[] args) throws IOException {
        BOJ1949.solution();
    }
}

//DP와 DFS를 합쳐서 풀면 어떨까 생각해보았다.
//일단 해당 지점을 지났을떄, 안지났을떄를 생각해야하니 dp배열을 [N + 1][2]로 만들어 [N + 1][0] 안지날때, [N + 1][1] 은 지날떄로 하기로 하였다.
//dp 배열은 초기에 -1로 채워 -1인 경우에는 지나지 않았다고 가정한다.
//이제 DFS 함수를 만들어 1부터 시작해 1을 포함하지 않을때인 compute(1,0)과 1을 포함할때인 compute(1,1) + people[1] 중 최대값을 결과값으로 가질 수 있게 만들었다.
//compute함수 안에서는 현재 위치에서 더이상 갈 수 없다면 0을 리턴해주고, 현재 위치를 기존에 지났다면 해당 dp[cur][p]를 리턴해준다.
//두 조건다 충족시키지 못한다면 -> 이 후에 갈 곳이 있고, 처음 지난다면, 방문체크를 해주고, 현재 위치에서 갈 수 있는 다음 위치를 구한다.
//만약 다음위치를 방문한 적이 없다 ->
//만약 현재 위치를 우수마을로 선정했으면 dp[cur][p]에 compute(다음위치,0)을 더해준다.
//만약 선정하지 않았다면, 다음 마을을 선정하거나, 선정하지 않을 수 있다.
//따라서 dp[cur][p]에 compute(다음위치 ,0)과 compute(다음위치, 1) + people[next] 중 큰 값을 추가해준다.
//이후 현재까지의 최대값인 dp[cur][p] 값을 리턴해주어 구현한다.
