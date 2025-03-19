package Programmers.Codekata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _149_여행경로 {

    class Solution {

        private List<String> answer;
        private boolean[] used;

        public String[] solution(String[][] tickets) {
            Arrays.sort(tickets, (a, b) -> {
                if (a[0].equals(b[0])) return a[1].compareTo(b[1]);
                return a[0].compareTo(b[0]);
            });

            used = new boolean[tickets.length];
            List<String> path = new ArrayList<>();
            path.add("ICN");

            dfs("ICN", tickets, path, 0);

            return answer.toArray(new String[0]);
        }

        private void dfs(String current, String[][] tickets, List<String> path, int cnt) {
            if (cnt == tickets.length) {
                if (answer == null) {
                    answer = new ArrayList<>(path);
                }
                return;
            }

            for (int i = 0; i < tickets.length; i++) {
                if (!used[i] && tickets[i][0].equals(current)) {
                    used[i] = true;
                    path.add(tickets[i][1]);
                    dfs(tickets[i][1], tickets, path, cnt + 1);
                    used[i] = false;
                    path.remove(path.size() - 1);
                    if (answer != null) return;
                }
            }
        }
    }

}

/*
문제 분석
1. 정보
    - 주어진 항공권을 모두 이용하여 여행경로를 짜려고 한다.
    - 항상 "ICN" 공항에서 출발함
2. 목표
    - 항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return
3. 제약 조건
    - 모든 공항은 알파벳 대문자 3글자로 이루어짐
    - 주어진 공항 수는 3개 이상 10000개 이하
    - tickets의 각 행 [a,b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미
    - 주어진 항공권은 모두 사용해야 함
    - 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return
    - 모든 도시를 방문할 수 없는 경우는 주어지지 않음.

풀이
1. 아이디어
    - 들어온 값을 일단 출발지 오름차순, 도착지 오름차순으로 정렬
    - 티켓의 사용 여부 확인을 위한 boolean[] 배열을 생성
    - DFS 통해 경로를 구함
        - 현재까지 사용한 티켓의 개수와 전체 티켓의 개수가 같으면 path를 answer에 저장하고 return
        - 티켓을 하나씩 확인
            - 만약 현재 위치가 티켓의 시작점이고, 해당 티켓을 사용하지 않았다면,
                - 사용 처리 함
                - path에 해당 티켓 도착지 add
                - dfs를 통해 도착지를 기준으로 다시 구함
                - dfs 끝나면 사용 처리 해제
                - path에서 해당 값 제거
                - 만약 answer가 존재한다면 = 이미 알파벳순 빠른 경로를 구함 -> 더 이상 구하지 않아도 되므로 return
*/