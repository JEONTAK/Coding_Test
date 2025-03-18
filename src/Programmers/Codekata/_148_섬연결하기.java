package Programmers.Codekata;

import java.util.Arrays;

public class _148_섬연결하기 {

    class Solution {

        int[] parent;

        private int find(int x){
            if(parent[x] == x)return x;
            return parent[x] = find(parent[x]);
        }

        private void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx != ry) {
                parent[ry] = rx;
            }
        }

        public int solution(int n, int[][] costs) {
            Arrays.sort(costs, (o1, o2) -> {
                return o1[2] - o2[2];
            } );
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            int answer = 0;
            int cnt = 0;

            for (int[] cost : costs) {
                int s = cost[0];
                int e = cost[1];
                int d = cost[2];
                if (find(s) != find(e)) {
                    union(s, e);
                    answer += d;
                    cnt++;
                    if (cnt == n - 1) {
                        break;
                    }
                }
            }
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때, 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return
    - 다리를 여러번 건너더라도 도달할 수만 있으면 통행 가능
2. 목표
    - n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때, 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return
3. 제약 조건
    - 1 <= 섬의 개수 n <= 100
    - costs의 길이 <= ((n - 1) * n ) / 2
    - 임의의 i에 대해 costs[i][0]와 costs[i][1]에는 다리가 연결되는 두 섬의 번호가 들어있고, costs[i][2]에는 이 두 섬을 연결하는 다리를 건설할 때 드는 비용
    - 같은 연결은 두 번 주어지지 않음. 또한 순서가 바뀌더라도 같은 연결로 봄
    - 즉 0과 1 사이를 연결하는 비용이 주어졌을 때, 1과 0의 비용이 주어지지 않음.
    - 모든 섬 사이의 다리 건설 비용이 주어지지 않음. 이 경우, 두 섬 사이의 건설이 불가능
    - 연결할 수 없는 섬은 주어지지 않음.
풀이
1. 아이디어
    - 들어온 costs를 costs[i][2] 오름차순으로 정렬함
    - Union-Find를 사용하여 최소 값으로 연결
    - 정렬한 모든 costs를 탐색
        - 아직 연결되지 않았다면 -> find(s) != find(e) 라면
            - union(s,e) 해주고 answer에 d 추가함
            - cnt++ 해주어 cnt가 n-1과 같아지면 종료 할 수 있게끔 구성
*/
