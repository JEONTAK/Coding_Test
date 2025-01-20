package Programmers.Codekata;


public class _93_피로도 {

    class Solution {

        int answer = 0;
        boolean[] visited;

        public int solution(int k, int[][] dungeons) {
            visited = new boolean[dungeons.length];

            dfs(k, dungeons, 0, 0);
            System.out.println(answer);
            return answer;
        }

        private void dfs(int k, int[][] dungeons, int idx, int cnt) {
            if (dungeons.length == idx) {
                answer = Math.max(answer, cnt);
                return;
            }

            for (int i = 0; i < dungeons.length; i++) {
                if (visited[i]) {
                    continue;
                }

                visited[i] = true;
                if (dungeons[i][0] <= k) {
                    dfs(k - dungeons[i][1], dungeons, idx + 1, cnt + 1);
                } else {
                    dfs(k, dungeons, idx + 1, cnt);
                }
                visited[i] = false;
            }
        }
    }

}

/*
문제 분석
1. 정보
    - XX게임에는 피로도 시스탬이 존재하며, 일정 피로도를 사용해 던전 탐험 가능
    - 이때, 각 던전마다 탐험을 시작하기 위해 필요한 "최소 필요 피로도"와 던전 탐험을 마쳤을때 소모되는 "소모 피로도"가 존재.
        - "최소 필요 피로도" : 해당 던전을 탐험하기 위해 가지고 있어야 하는 최소한의 피로도
        - "소모 피로도" : 던전을 탐험한 후 소모되는 피로도
    - 하루에 한번씩 탐험할 수 있는 던전이 여러개 존재

2. 목표
    - 유저가 탐험할 수 있는 최대 던전 수를 return

3. 제약 조건
    - 1 <= 현재 피로도 <= 5000
    - 1 <= 던전 개수 <= 8
    - 1 <= 소모 피로도 <= 최소 필요 피로도 <= 1000

풀이
1. 아이디어
    - DFS 사용?
        - 들어온 던전을 최대한 탐험할 수 있게 DFS사용하여 최대 값 구하기
        - 시작은 0부터, visited 배열 사용
        - 만약 해당 던전을 갈수 있고, 방문하지 않았다면
            - 방문 처리하고, 해당 던전에 들어감.
            - 이후 방문 다시 제거
        - 모든 경우의 수를 구한 뒤, 가장 최대값을 return
*/
