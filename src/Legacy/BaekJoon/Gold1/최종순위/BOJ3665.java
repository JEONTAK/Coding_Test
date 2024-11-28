package BaekJoon.Gold1.최종순위;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ3665 {

    static int T, N, M;
    static int[] rank, degree;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            rank = new int[N + 1];
            degree = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1 ; i <= N ; i++){
                int cur = Integer.parseInt(st.nextToken());
                rank[cur] = i;
                degree[cur] = N - i;
            }
            M = Integer.parseInt(br.readLine());
            for(int i = 0 ; i < M ; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (rank[a] > rank[b]) {
                    degree[a]++;
                    degree[b]--;
                }else if(rank[a] < rank[b]){
                    degree[a]--;
                    degree[b]++;
                }
            }
            HashSet<Integer> set = new HashSet<>();
            for(int i = 1 ; i <= N ; i++){
                set.add(degree[i]);
            }
            if (N != set.size()) {
                sb.append("IMPOSSIBLE\n");
                continue;
            }
            int cur = N - 1;
            while (cur >= 0) {
                for(int i = 1 ; i <= N ; i++){
                    if (degree[i] == cur) {
                        sb.append(i + " ");
                        cur--;
                        break;
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ3665.solution();
    }
}
//음... 위상정렬로 풀었다.
//먼저 rank에는 과거 순위를 넣는다 만약 과거 순위가 5 4 3 2 1이라면, rank에는 5 4 3 2 1이 들어가게 된다.
//이후 degree라는 곳에 전체 팀 - 순위 값을 저장한다.
//위 이유는 1등팀은 결국 나머지 4개의 팀보다 위이기 때문에, 위치 상으로 4로 설정하였다.
//이후 등수가 바뀐 쌍의 수를 a, b라 칭하고 rank[a]와 rank[b]를 비교한다.
//두값을 비교하여 등수가 바뀌었으므로, 더 큰쪽, 즉 순위가 낮은 쪽의 degree는 +1, 순위가 높은 쪽의 degree는 -1 해준다.
//모든 쌍을 계산하고 나서, 만약 degree가 겹친다면 등수를 확실히 정할 수 없다는 의미로 생각하였고, 이 경우 IMPOSSIBLE을 출력되게끔 하였다.
//겹치지 않는다면 cur = 전체 팀의 개수 - 1인 값부더 비교해서 degree가 해당 값과 같으면 해당 idx를 저장하고 cur - 1하면서 전체 순위를 저장하였다.