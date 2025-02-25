package Programmers.Codekata;

public class _137_양궁대회 {

    class Solution {
        int[] answer = new int[11];
        int scoreDiff = 0;
        int[] arrows = new int[11];

        public int[] solution(int n, int[] info) {
            setArrow(0, n, info);
            if (scoreDiff == 0) {
                return new int[]{-1};
            }
            return answer;
        }

        private void setArrow(int idx, int n, int[] info) {
            if (n == 0) {
                computeScore(info);
                return;
            }

            if (idx == 11) {
                return;
            }

            for (int i = 0; i <= n; i++) {
                arrows[idx] = i;
                setArrow(idx + 1, n - i, info);
                arrows[idx] = 0;
            }
        }

        private void computeScore(int[] info) {
            int apeachScore = 0;
            int lionScore = 0;

            for (int i = 0; i <= 10; i++) {
                int score = 10 - i;
                if (info[i] < arrows[i]) {
                    lionScore += score;
                } else {
                    if (info[i] != 0) {
                        apeachScore += score;
                    }
                }
            }

            int diff = lionScore - apeachScore;
            if (scoreDiff < diff) {
                scoreDiff = diff;
                System.arraycopy(arrows, 0, answer, 0, 11);
                return;
            }

            if (scoreDiff > 0 && scoreDiff == diff) {
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] > arrows[i]) {
                        return;
                    }

                    if (answer[i] < arrows[i]) {
                        System.arraycopy(arrows, 0, answer, 0, 11);
                        return;
                    }
                }
            }
        }
    }

}

/*
문제 분석
1. 정보
    - 카카오배 양궁대회가 열림
    - 라이언은 저번 카카오배 양궁대회 우승자이고, 이번 대회에도 결승전까지 올라옴
    - 상대는 어피치
    - 양궁대회 운영위원회는 한 선수의 연속 우승보다는 다양한 선수들이 양궁대회에서 우승하기를 원함
    - 따라서 운영위원회는 결승전 규칙을 전 대회 우승자인 라이언에게 불리하게 다음과 같이 정함
        - 1. 어피치가 화살 n발을 다 쏜 후에 라이언이 화살 n발을 쏜다
        - 2. 점수를 계산
            - 1. 과녁판은 10 ~ 0점까지 존재
            - 2. 만약 k점을 어피치가 a발을 맞혔고, 라이언이 b 발을 맞혔을 경우 더 많은 화살을 k점에 맞힌 선수가 k점을 가져감
            - 단, a = b 일 경우 어피치가 k점을 가져감
            - a = b = 0인 경우 둘다 k점 못가져감
            - 3. 모든 과녁 점수에 대하여 각 선수의 최종 점수를 계산
        - 3. 최종 점수가 더 높은 선수를 우승자로 결정, 점수가 같을 경우 어피치가 우승
    - 현재 상황은 어피치가 화살 n발을 다 쏜 후이고, 라이언이 화살을 쏠 차례
    - 라이언은 어피치를 가장 큰 점수 차이로 이기기 위하여 n 발의 화살을 어떤 과녁 점수에 맞혀야 하는지를 구하려고 함

2. 목표
    - 화살의 개수를 담은 자연수 n, 어피치가 맞힌 과녁의 점수의 개수를 10점부터 0점까지 순서대로 담은 정수 배열 info가 매개변수로 주어짐.
    - 이때, 라이언이 가장 큰 점수 차이로 우승하기 위해 n발의 화살을 어떤 과녁 점수에 맞혀야 하는지를 10점 부터 0점까지 순서대로 정수 배열에 담아 return
    - 만약 라이언이 우승할 수 없는 경우 -1을 return
3. 제약 조건
    - 1 <= n <= 10
    - info의 길이 == 11
        - 0 <= info의 원소 <= n
        - info의 원소 총합 = n
        - info의 i번째 원소는 과녁의 10 - i점을 맞힌 화살 개수 임
    - 라이언이 우승할 방법이 있는 경우, return할 정수 배열의 길이는 11
        - 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 return

풀이
1. 아이디어
    - 완전 탐색을 이용하여 문제 해결
        - 10점부터 n개의 화살중 0 ~ n개까지 사용하였을 경우를 선택
            - 다음 9점에서도 똑같이 적용
            - 화살 n개를 모두 다 사용 하였을 경우 점수를 계산
            - 만약 점수 차이의 값이 기존 값보다 크다면, 해당 점수를 달성했을 때의 화살 배열로 업데이트
            - 만약 값이 같다면, 화살 배열끼리 비교하여 낮은 점수를 더 많이 맞춘 경우로 업데이트
    - 모든 경우의 수를 구하고 나서, 만약 점수차이의 값이 업데이트 되지 않았다면, -1을 return
    - 업데이트 되었다면, answer return
*/
