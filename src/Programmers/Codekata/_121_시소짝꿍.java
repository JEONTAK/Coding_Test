package Programmers.Codekata;

public class _121_시소짝꿍 {

    class Solution {
        public long solution(int[] weights) {
            long[] wCnt = new long[1001];
            long answer = 0;
            for (int weight : weights) {
                wCnt[weight]++;
            }

            for (int i = 100; i < wCnt.length; i++) {
                long cur = wCnt[i];
                if (cur >= 2) {
                    answer += cur * (cur - 1) / 2;
                }

                if (i * 3 % 2 == 0 && i * 3 / 2 <= 1000) {
                    answer += cur * wCnt[i * 3 / 2];
                }

                if (i * 2 <= 1000) {
                    answer += cur * wCnt[i * 2];
                }

                if (i * 4 % 3 == 0 && i * 4 / 3 <= 1000) {
                    answer += cur * wCnt[i * 4 / 3];
                }
            }
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 시소가 설치되어 있음
    - 시소는 중심으로부터 2,3,4m 거리의 지점에 좌석이 하나씩 존재
    - 시소를 두 명이 마주 보고 탄다고 할 때, 시소가 평형인 상태에서 서로 상쇄되어 완전한 균형을 이룰 수 있다면 그 두 사람을 시소 짝꿍이라고 함.
    - 즉 탑승한 사람의 무게와 시소 축과 좌석 간의 거리의 곱이 양쪽 다 같다면 시소 짝꿍
2. 목표
    - 사람들의 몸무게 목록 weights가 주어질 때, 시소 짝꿍이 몇 쌍 존재하는지 구하여 return

3. 제약 조건
    - 2 <= 사람의 수 <= 100000
    - 100 <= 몸무게 <= 1000

풀이
1. 아이디어
    - 총 사람의 수는 10만이므로, 어떤 방식으로 탐색 하든 시간 초과가 날 가능성 존재
    - 따라서 몸무게 배열 w를 만들어 해당 배열에 몸무게 w를 가진 사람의 수를 저장
    - 저장된 w배열을 한번 돌면서
        - 몸무게가 같을 경우
            - nC2 = n! / (n-2)! *  = n * (n - 1) / 2
            - answer += n * (n - 1) / 2
        - 몸무게 다른 두명이 앉는 경우의 수
            - 3m & 2m
                - if((cur * 3) % 2 == 0)
                    - answer += w[cur] * w[cur * 3 / 2]
            - 4m & 2m
                - answer += w[cur] * w[cur * 4 / 2]
            - 4m & 3m
                - if((cur * 4) % 3 == 0)
                    - answer += w[cur] * w[cur * 4 / 3]
    - 이후 answer return

100 180 270 360


1
1
1

*/
