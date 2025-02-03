package Programmers.Codekata;

public class _116_점찍기 {

    class Solution {
        public long solution(int k, int d) {
            long answer = 0;
            long dist = (long) d * d;

            for (int x = 0; x <= d; x += k) {
                long maxY = (long) Math.sqrt(dist - (long) x * x);
                answer += (maxY / k) + 1;
            }

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 진수는 x축과 y축이 직교하는 2차원 좌표 평면에 점을 찍으면서 놀고 있다.
    - 진수는 두 양의 정수 k, d 가 주어질 때 다음과 같이 점을 찍으려 한다.
        - 원점(0,0)으로부터 x축 방향으로 a*k, y축 방향으로 b*k만큼 떨어진 위치에 점을 찍는다.
        - 원점과 거리가 d를 넘는 위치에는 점을 찍지 않는다.

2. 목표
    - 정수 k와 원점과의 거리를 나타내는 정수 d가 주어졌을 때, 점이 총 몇 개 찍히는지 return
3. 제약 조건
    - 1 <= k <= 1000000
    - 1 <= d <= 1000000

풀이
1. 아이디어
    - x,y에 점이 존재하려면
        - x^2 + y^2 <= d^2 이어야 함.
    - k씩 증가하므로,
        - (x + k)^2 + y^2 <= d^2 이어야 함.
        - 즉, y^2 <= d^2 - (x + k)^2 를 통해 x좌표마다 y의 최댓값을 구할 수 있음.
    - d^2은 최대 1조 이므로, long type 사용
    - x = 0 ; x <= d ; x += k 일동안
        - Y최대값 = Math.sqrt(d * d - x * x)
        - 결과값에 += (Y최대값 / k) + 1 (Y도 k의 배수이므로)
    - 결과값 return
*/
