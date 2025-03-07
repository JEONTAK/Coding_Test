package Programmers.Codekata;

public class _141_당구연습 {

    class Solution {
        public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
            int[] answer = new int[balls.length];
            int idx = 0;

            for (int[] ball : balls) {
                int minLen = Integer.MAX_VALUE;
                int hitX = ball[0];
                int hitY = ball[1];
                int[][] reflections = {
                        {-hitX, hitY},
                        {2 * m - hitX, hitY},
                        {hitX, -hitY},
                        {hitX, 2 * n - hitY}
                };

                for (int[] ref : reflections) {
                    int reflectX = ref[0];
                    int reflectY = ref[1];

                    if (!isBlocked(startX, startY, hitX, hitY, reflectX, reflectY)) {
                        int distSquared = (int) (Math.pow(startX - reflectX, 2) + Math.pow(startY - reflectY, 2));
                        minLen = Math.min(minLen, distSquared);
                    }
                }



                answer[idx++] = minLen;
            }

            return answer;
        }

        private boolean isBlocked(int startX, int startY, int hitX, int hitY, int reflectX, int reflectY) {
            if (startX == hitX && startX == reflectX) {
                return (startY < hitY && startY < reflectY) || (startY > hitY && startY > reflectY);
            }
            if (startY == hitY && startY == reflectY) {
                return (startX < hitX && startX < reflectX) || (startX > hitX && startX > reflectX);
            }
            return false;
        }
    }

}

/*
문제 분석
1. 정보
    - 당구 학원에 나온 머쓱이에게 당구 선생님이 "원쿠션" 연습을 하라면서 당구공의 위치가 담긴 리스트를 건네줌.
    - 리스트에는 머쓱이가 맞춰야하는 공들의 위치가 담겨 있음. 머쓱이는 리스트에 담긴 각 위치에 순서대로 공을 놓아 가며 "원쿠션" 연습을 하면 됨. 이때 머쓱이는 항상 같은 위치에 공을 놓고 쳐서 리스트에 담긴 위치에 놓인 공을 맞춤.
    - 당구대의 가로 길이 m, 세로 길이 n과 머쓱이가 쳐야하는 공이 놓인 위치 좌표를 나타내는 두 정수 startX, startY, 그리고 매 회마다 목표로 해야하는 공들의 위치 좌표를 나타내는 정수 쌍들이 들어있는 2차우너 정수 배열 balls가 주어진다.
    - 단 머쓱이가 친 공이 벽에 부딪힐 때 진행 방향은 항상 입사각과 반사각이 동일하며, 만약 꼭짓점에 부딪힐 경우 진입 방향의 반대방향으로 공이 진행됨.
    - 공의 크기는 무시하고, 두 공의 좌표가 정확히 일치하는 경우에만 두 공이 서로 맞았다고 판단.
    - 공이 목표 공에 맞기 전에 멈추는 경우는 존재하지 않고, 목표 공에 맞으면 바루 멈춘다고 가정.
2. 목표
    - "원쿠션" 연습을 위해 머쓱이가 공을 적어도 벽에 한 번은 맞춘 후 목표 공에 맞힌다고 할 때, 각 회마다 머쓱이가 친 공이 굴러간 거리의 최솟값의 제곱을 배열에 담아 return
3. 제약 조건


풀이
1. 아이디어
    - 해당 당구판을 기준으로 4면 + 각 대각선에 거울처럼 판을 생성한다 생각하면 됨.
        - 생성하는 이유 : 최소 "원쿠션"을 해야하기 때문.
            - 원쿠션을 하기 위해선, 벽을 반드시 지나가야되기 때문에, 거울처럼 생성한 반대쪽에 공을 위치시키고 해당 좌표와 공의 좌표에 대한 길이를 구하면 해당 길이가 최솟값이 됨.
    - 치려는 공의 좌표를 [x,y], 이라 가정
        - [x,y] 좌표를 다음과 같이 생성
            - 좌측 벽 반사 : [-x,y]
            - 우측 벽 반사 : [2m-x,y]
            - 상단 벽 반사 : [x,2n-y]
            - 하단 벽 반사 : [x,-y]
        - 총 8개의 좌표를 구하고, 당구공이 놓인 위치와의 길이를 구한 뒤, 가장 최소인 길이를 저장.
    - 모든 공을 위 방식으로 길이를 저장한 뒤 반환.
    
    - 주의점
        - 공의 y좌표가 같을 경우 : 좌측 벽 또는 우측 벽 반사 불가능
        - 공의 x좌표가 같을 경우 : 삳단 벽 또는 하단 벽 반사 불가능
*/