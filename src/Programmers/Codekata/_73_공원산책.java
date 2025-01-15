package Programmers.Codekata;

public class _73_공원산책 {

    class Solution {
        public int[] solution(String[] park, String[] routes) {
            int[] answer = findStart(park);
            int H = park.length;
            int W = park[0].length();
            for (int i = 0; i < routes.length; i++) {
                char dir = routes[i].charAt(0);
                int len = routes[i].charAt(2) - '0';

                if (isAvailable(park, answer, dir, len, H, W)) {
                    switch (dir) {
                        case 'N' -> answer[0] -= len;
                        case 'S' -> answer[0] += len;
                        case 'W' -> answer[1] -= len;
                        case 'E' -> answer[1] += len;
                    }
                }
            }

            return answer;
        }

        public boolean isAvailable(String[] park, int[] cur, char dir, int len, int H, int W) {
            int[] tmp = cur.clone();
            for (int i = 0; i < len; i++) {
                switch (dir) {
                    case 'N' -> tmp[0]--;
                    case 'S' -> tmp[0]++;
                    case 'W' -> tmp[1]--;
                    case 'E' -> tmp[1]++;
                }

                if (tmp[0] < 0 || tmp[0] >= H || tmp[1] < 0 || tmp[1] >= W) {
                    return false;
                }

                if (park[tmp[0]].charAt(tmp[1]) == 'X') {
                    return false;
                }
            }
            return true;
        }

        public int[] findStart(String[] park) {
            int[] start = new int[2];
            for (int i = 0; i < park.length; i++) {
                for (int j = 0; j < park[i].length(); j++) {
                    if (park[i].charAt(j) == 'S') {
                        start[0] = i;
                        start[1] = j;
                        return start;
                    }
                }
            }
            return start;
        }
    }
}

/*
문제 분석
1. 정보
     - 직사각형 격자모양의 공원이 존재
        - O : 지나갈 수 있는 길
        - X : 장애물
     - 명령은 다음과 같이 주어짐
        - ["방향 거리", "방향 거리", ...]
     - 만약 해당 명령을 수행할때
        - 공원을 벗어나거나
        - 장애물에 마주하는 경우
     - 해당 명령을 무시하고 다음 명령을 수행
     
2. 목표
    - 시작지점 S에서 시작해서 모든 명령을 수행하고 난 후의 좌표를 출력
3. 제약 조건
    - 3 <= 공원 높이 <= 50
    - 3 <= 공원 너피 <= 50
    - 1 <= 명령 개수 <= 50
        - 명령은 OP N으로 구성
        - OP : N S W E중 하나
        - 1 <= N <= 9

풀이
1. 아이디어
    - 구현 문제
        - 받은 park 배열에서 S를 찾아 시작지점으로 지정
        - 명령을 순서대로 수행
            - 해당 명령을 수행할 수 없는 조건이 발생하면
            - 다음 명령 수행
            - 수행할 수 있다면, 현재 위치 업데이트
        - 모든 명령을 수행한 후 결과 좌표 return
*/
