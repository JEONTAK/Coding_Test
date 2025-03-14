package Programmers.Codekata;

import java.util.LinkedList;
import java.util.Queue;

public class _144_네트워크 {

    class Solution {
        public int solution(int n, int[][] computers) {
            int answer = 0;
            boolean[] visited = new boolean[n];

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    Queue<Integer> q = new LinkedList<>();
                    q.add(i);
                    visited[i] = true;

                    while (!q.isEmpty()) {
                        int cur = q.poll();

                        for (int j = 0; j < n; j++) {
                            if (!visited[j] && computers[cur][j] == 1) {
                                q.add(j);
                                visited[j] = true;
                            }
                        }
                    }
                    answer++;
                }
            }
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미
    - 예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어 있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있음.
    - 따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있음.
2. 목표
    - 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return
3. 제약 조건
    - 1 <= n <= 200
    - 각 컴퓨터는 0부터 n - 1 인 정수로 표현
    - i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현
    - computer[i][i]는 항상 1

풀이
1. 아이디어
    - BFS를 사용하여 문제 풀이
    - visited[] 를 만들어 해당 네트워크가 이미 지나갔는지 확인
    - 0 ~ n - 1까지 visited하지 않았다면, 해당 네트워크에서 BFS 사용하여 연결된 모든 네트워크를 visited = true 처리함
        - 해당 연결에 대한 모든 컴퓨터를 탐색했으면 answer++
    - 전체를 탐색한 후 answer 를 return
*/