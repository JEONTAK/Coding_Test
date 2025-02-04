package Programmers.Codekata;

import java.util.LinkedList;
import java.util.Queue;

public class _118_거리두기확인하기 {

    class Solution {

        class Node {
            int x;
            int y;
            int d;

            public Node(int x, int y, int d) {
                this.x = x;
                this.y = y;
                this.d = d;
            }
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        public int[] solution(String[][] places) {
            int[] answer = new int[5];

            for (int i = 0; i < 5; i++) {
                answer[i] = compute(places[i]);
            }
            return answer;
        }

        private int compute(String[] place) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (place[i].charAt(j) == 'P') {
                        Queue<Node> q = new LinkedList<>();
                        boolean[][] visited = new boolean[5][5];
                        q.add(new Node(i, j, 0));
                        visited[i][j] = true;
                        while (!q.isEmpty()) {
                            Node cur = q.poll();
                            if (cur.d == 2) {
                                continue;
                            }
                            for (int k = 0; k < 4; k++) {
                                int nx = cur.x + dx[k];
                                int ny = cur.y + dy[k];

                                if (isAvailable(nx, ny) && !visited[nx][ny]) {
                                    char next = place[nx].charAt(ny);
                                    if (next == 'P') {
                                        return 0;
                                    } else if (next == 'O') {
                                        visited[nx][ny] = true;
                                        q.add(new Node(nx, ny, cur.d + 1));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return 1;
        }

        private boolean isAvailable(int nx, int ny) {
            return nx >= 0 && nx < 5 && ny >= 0 && ny < 5;
        }
    }

}

/*
문제 분석
1. 정보
    - 코로나 감염 예방을 위해 거리를 둬서 대기. 규칙은 다음과 같음
        - 대기실은 5개이며, 각 대기실은 5X5 크기
        - 거리두기를 위하여 응시자들 끼리는 맨해튼 거리가 2 이하로 앉지 말 것
        - 단 응시자가 앉아있는 자리 사이가 파티션으로 막혀 있을 경우에는 허용

2. 목표
    - 대기실의 구조를 대기실 별로 담은 places가 주어질 때, 각 대기실 별로 거리두기를 지키고 있으면 1, 한 명이라도 지키지 않고 있으면 0을 배열에 담아 return
3. 제약 조건
    - 대기실의 개수, 행 길이, 열 길이 = 5
    - 원소는 P O X로 이루어짐
        - P : 앉아있는 자리
        - O : 빈 테이블
        - X : 파티션

풀이
1. 아이디어
    - 각 대기실 별로 계산
        - 현재 대기실에서 모든 응시자들에 대하여 계산
            - 해당 응시자를 기준으로 파티션을 제외한 2칸 이하에 응시자가 있을 경우 바로 0
            - 없다면 다음 응시자 선택
        - 모든 응시자가 거리두기를 지키고 있다면, 1 반환

    - 해당 대기실 0,0 부터 4,4까지 순회
        - 해당 좌표에 응시자 있다면
            - BFS 활용 (x,y,거리)담은 Node class 생성
            - Queue를 이용해 거리가 2이하인 곳만 탐색
            - 파티션은 지날 수 없음
            - 거리가 2 이하인 곳에 응시자 존재한다면 0 반환
            - 큐가 끝날때까지 응시자 존재하지 않는다면 다음 응시자 찾음
    - 해당 대기실 모두 순회하고도 거리두기 잘 지켜졌다면 1 반환
*/
