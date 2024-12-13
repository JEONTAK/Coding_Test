package BaekJoon.Silver3.터렛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1002 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            double d = computeDist(x1, y1, x2, y2);
            //동심원
            if (d == 0) {
                if (r1 == r2) {//같은 원일 때
                    sb.append(-1).append("\n");
                }else{//동심원이지만 원의 반지름이 다를떄
                    sb.append(0).append("\n");
                }
            }else{
                if (d > r1 + r2) {// 두 원이 멀리 떨어진 경우
                    sb.append(0).append("\n");
                    continue;
                }

                if (d == r1 + r2) {// 두 원이 외접하는 경우
                    sb.append(1).append("\n");
                    continue;
                }

                if (d < Math.abs(r1 - r2)) {// 원 안에 원이 들어있는 경우
                    sb.append(0).append("\n");
                    continue;
                }

                if (d == Math.abs(r1 - r2)) {//두 원이 내접하는 경우
                    sb.append(1).append("\n");
                    continue;
                }
                //나머지 경우
                sb.append(2).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static double computeDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) throws IOException {
        BOJ1002.solution();
    }
}

/*
문제 분석
1. 정보
    - 테스트케이스 T
    - 조규현의 좌표 : x1, y1
    - 백승환의 좌표 : x2, y2
    - 조규현이 계산한 류재명과의 거리 : r1
    - 백승환이 계산한 류재명과의 거리 : r2

2. 목표
    - 주어진 값을 이용해 류재명이 있을 수 있는 좌표의 수를 출력

3. 제약 조건
    - -10000 <= x1, y1, x2, y2 <= 10000
    - 1 <= r1, r2 <= 10000

풀이
1. 알고리즘
    - x1,y1을 중심으로 한 r1의 반지름을 가진 원의 방정식
        - (x - x1)^2 + (y - y1)^2 = r1^2
    - x2,y2을 중심으로 한 r2의 반지름을 가진 원의 방정식
        - (x - x2)^2 + (y - y2)^2 = r2^2

    - 만약 동심원일 경우(-1 or 0)
        - r1 != r2 : 0
        - r1 == r2 : 무한대 : -1

    - 멀리 떨어져 있을 경우(0)
        - 중심 간의 거리 > r1 + r2

    - 원안에 원이 있을 경우(0)
        - 중심 간의 거리 < |r1 - r2|

    - 내접 or 외접(1)
          - 중심 간의 거리 == r1 + r2 : 외접
          - 중심 간의 거리 == |r1 - r2| : 내접
    - 위 조건 다 아닐 경우(2)
 */
