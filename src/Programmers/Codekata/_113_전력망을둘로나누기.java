package Programmers.Codekata;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _113_전력망을둘로나누기 {

    class Solution {
        List<List<Integer>> list = new ArrayList<>();
        boolean[] visited;
        int answer = 1000;

        public int solution(int n, int[][] wires) {
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }

            for (int[] wire : wires) {
                list.get(wire[0]).add(wire[1]);
                list.get(wire[1]).add(wire[0]);
            }

            for (int[] wire : wires) {
                int v1 = wire[0];
                int v2 = wire[1];

                list.get(v1).remove(Integer.valueOf(v2));
                list.get(v2).remove(Integer.valueOf(v1));

                visited = new boolean[n + 1];

                int subTree = BFS(v1);
                int remain = n - subTree;
                answer = Math.min(answer, Math.abs(subTree - remain));

                list.get(v1).add(v2);
                list.get(v2).add(v1);
            }
            return answer;
        }

        private int BFS(int v1) {
            int cnt = 1;
            Queue<Integer> q = new LinkedList<>();
            q.add(v1);
            visited[v1] = true;

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (Integer next : list.get(cur)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                        cnt++;
                    }
                }
            }
            return cnt;
        }
    }
}

/*
문제 분석
1. 정보
    - n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있다.
    - 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 한다.
    - 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 한다.
2. 목표
    -송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어졌을때, 두 전력망이 가지고 있는 송전탑 개수의 차이를 return
3. 제약 조건
    - 2 <= n <= 100
    - wires는 길이가 n-1인 2차원 배열
        - wires의 각 원소는 [v1,v2]로 이루어져 있고, 이는 v1번 송전탑과 v2번 송전탑이 전선으로 연결 되어 있다는 것을 의미


풀이
1. 아이디어
    - 인접한 번호를 저장하는 리스트를 생성
    - 하나의 간선을 제거하고, BFS 사용하여 한쪽 서브트리의 노드 개수를 구함
    - 전체 노드 개수에서 한쪽 서브트리의 노드 개수를 빼면 다른 서브 트리의 크기 얻기 가능
    - 두 서브트리의 차이를 계산하고 최소값 갱신(절댓값)

*/
