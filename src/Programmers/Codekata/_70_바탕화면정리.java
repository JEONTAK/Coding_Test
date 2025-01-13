package Programmers.Codekata;

public class _70_바탕화면정리 {
    class Solution {
        public int[] solution(String[] wallpaper) {
            int lux = 51;
            int luy = 51;
            int rdx = 0;
            int rdy = 0;
            for (int i = 0; i < wallpaper.length; i++) {
                for (int j = 0; j < wallpaper[i].length(); j++) {
                    if (wallpaper[i].charAt(j) == '#') {
                        lux = Math.min(lux, i);
                        luy = Math.min(luy, j);
                        rdx = Math.max(rdx, i + 1);
                        rdy = Math.max(rdy, j + 1);
                    }
                }
            }

            return new int[]{lux, luy, rdx, rdy};
        }
    }
}

/*
문제 분석
1. 정보
    - 머쓱이는 작성한 코드를 컴퓨터 바탕화면에 아무 위치에 저장
    - 작성한 코드가 많아짐에 따라, 프로그래머스에서 작성했던 코드는 바탕화면에서 삭제하기로 함
    - 바탕화면은 각 칸이 정사각형인 격자
    - 바탕화면의 상태를 나타낸 문자열 배열 wallpaper가 주어짐
        - (0,0)이 시작
        - 빈칸은 .
        - 파일이 있는 칸은 #
        - 드래그를 하면 파일을 선택할 수 있고, 선택한 파일을 삭제할 수 있음
        - 드래그는 다음과 같음
            - 격자점 S(lux,luy)에서 E(rdx,rdy)까지 드래그 했을 경우,
                - 점 S에서 점 E로 드래그한다 라고 표현
                - 드래그 한 거리 : |rdx - lux| + |rdy - luy|
2. 목표
    - 드래그 한 거리가 최소가 되는 값의 lux, luy, rdx, rdy를 출력
3. 제약 조건
    - 1 <= 격자판의 높이 <= 50
    - 1 <= 격자판의 너비 <= 50
    - 바탕화면에는 적어도 하나의 파일이 있음

풀이
1. 아이디어
    - lux, luy, rdx, rdy를 선언
    - wallpaper 배열을 왼쪽 위부터 오른쪽 아래까지 순차적으로 방문
    - 해당 격자에 #이 있다면, 위 4개에 적절히 업데이트
    - 모든 격자판을 방문한 이후, 위 4개의 정보를 활용해 드래그 범위를 구함
*/
