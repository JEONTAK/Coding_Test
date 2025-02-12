package Programmers.Codekata;

public class _130_두원사이의정수쌍 {

    public class Solution {
        public long solution(int r1, int r2) {
            long answer = 0;

            for (int i = 1; i <= r2; i++) {
                double y1 = dist(r1, i);
                double y2 = dist(r2, i);
                answer += ((long) y2 - (long) Math.ceil(y1) + 1);
            }
            answer *= 4;
            return answer;
        }

        public double dist(int x, int y) {
            return (double) Math.sqrt(Math.pow(x, 2) - Math.pow(y, 2));
        }
    }

}

/*
문제 분석
1. 정보
    - x축과 y축으로 이루어진 좌표계에 중심이 원점인 서로 다른 크기의 원이 두개가 주어짐

2. 목표
    - 반지름을 나타내는 두 정수 r1, r2가 매개변수로 주어질 때 두 원 공간 사이의 공간에 x좌표와 y좌표가 모두 정수인 점의 개수를 return

3. 제약 조건
    - 1 <= r1 < r2 <= 1000000

풀이
1. 아이디어
    - 반지름이 r1, r2이므로 Math.abs(x^2 + y^2)가 r1 과 r2 사이에 존재하면 두 원 사이 공간에 존재함.
    - x와 y의 좌표가 양수인 공간에서 모든 점의 좌표를 구한 후, 결과값 * 4 해줌
    - (r2 - r1 + 1) * 4 한 값을 추가한 후 return
*/
