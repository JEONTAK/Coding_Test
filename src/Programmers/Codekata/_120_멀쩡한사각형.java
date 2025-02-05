package Programmers.Codekata;

public class _120_멀쩡한사각형 {

    class Solution {
        public long solution(int w, int h) {
            int gcd = gcd(w, h);

            int nw = w / gcd;
            int nh = h / gcd;

            return (long) w * h - (long) (nw + nh - 1) * gcd;
        }

        private int gcd(int a, int b) {
            if(b == 0) return a;
            return gcd(b, a % b);
        }
    }

}

/*
문제 분석
1. 정보
    - 가로 W, 세로 H인 직사각형 종이가 존재
    - 종이를 격자선을 따라 1cm X 1cm의 정사각형으로 잘라 사용할 예정이었는데, 누군가가 해당 종이를 대각선 꼭지점 2개를 잇는 방향으로 잘라 놓았음.
    - 따라서 현재 직사각형 종이는 크기가 같은 직각삼각형 2개로 나누어진 상태
2. 목표
    - 나누어진 종이에서 사용할 수 있는 정사각형의 개수를 return
3. 제약 조건
    - 1 <= W, H <= 100_000_000

풀이
1. 아이디어
    - 대각선은 중간에 정사각형이 겹치지 않는 좌표를 지날 수 있음 -> W와 H의 최대공약수를 구한 값 = gcd
        - (W / gcd, H / gcd) = (nw,nh)의 크기를 가진 직사각형이 gcd개 만큼 존재
        - 그렇다면 (nw, nh)의 크기를 가진 직사각형에서 잘린 정사각형을 제외한 크기는?
            - (nw - 1) * (nh - 1)
        - 잘린 사각형의 크기는 ?
            - nw * nh - (nw - 1) * (nh - 1) = nw + nh - 1
            - 결국 W * H - (gcd * (nw + nh - 1)값을 return
*/
