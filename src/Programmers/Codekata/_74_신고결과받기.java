package Programmers.Codekata;

import java.util.HashMap;
import java.util.Map;

public class _74_신고결과받기 {

    class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            Map<String, Integer> users = new HashMap<>();
            for (int i = 0; i < id_list.length; i++) {
                users.put(id_list[i], i);
            }
            int[] answer = new int[id_list.length];
            boolean[][] check = new boolean[id_list.length][id_list.length];

            for (int i = 0; i < report.length; i++) {
                String[] su = report[i].split(" ");
                int left = users.get(su[0]);
                int right = users.get(su[1]);
                check[right][left] = true;
            }

            for (int i = 0; i < check.length; i++) {
                int sum = 0;
                for (int j = 0; j < check[i].length; j++) {
                    if (check[i][j]) {
                        sum++;
                    }
                }
                if (sum >= k) {
                    for (int j = 0; j < check[i].length; j++) {
                        if (check[i][j]) {
                            answer[j]++;
                        }
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
    - 무지가 개발하려는 시스템은 다음과 같음
        - 각 유저는 한 번에 한 명의 유저를 신고 가능
            - 신고 횟수에 제한은 없음. 서로 다른 유저를 계속 신고 가능
            - 한 유저를 여러번 신고할 수도 있지만, 동일한 유저에 대한 신고는 1회로 처리
        - k번 이상 신고된 유저는 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송
            - 유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면서 정지 메일을 발송

2. 목표
    - 각 유저가 받는 메일의 개수를 배열로 return

3. 제약 조건
    - 2 <= 유저의 수 <= 1000
    - 1 <= 신고 횟수 <= 200000
    - 1 <= k <= 200

풀이
1. 아이디어
    - 먼저 id_list를 활용하여 Map<String, Integer> userId를 만들어 Id를 저장
    - 또한, boolean[][] check 배열을 만들어 i 유저가 j 유저에게 신고당했는지 저장
    - report를 모두 탐색
        - 해당 report에서 왼쪽은 신고한사람, 오른쪽은 신고를 당한 사람
        - 따라서 userId에서 왼쪽과 오른쪽의 id값을 찾아 check[j][i] = true;
    - 모든 report가 끝났다면,
        - check 배열을 순회
            - 만약 i번째 사람이 신고당한 횟수가 k 이상이면 다시 돌면서 신고 한사람들의 answer에 1씩 추가
    - 이후 answer return
*/