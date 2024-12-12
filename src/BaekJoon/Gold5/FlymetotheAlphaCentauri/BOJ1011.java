package BaekJoon.Gold5.FlymetotheAlphaCentauri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1011 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            long N = (y - x);
            int k = 1;
            while (true) {
                if (N <= (long) k * k) {
                    sb.append(2 * k - 1).append("\n");
                    break;
                } else if (N <= (long) k * (k + 1)) {
                    sb.append(2 * k).append("\n");
                    break;
                }
                k++;
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ1011.solution();
    }
}

/*
문제 분석
1. 정보
    - 공간 이동 장치
        - 이전 작동 시기에 k광년 이동 하였을 경우
            - k-1, k, k+1 광년만 다시 이동 가능
    - 도착 하기 직전의 이동거리는 반드시 1광년 이어야 함

2. 목표
    - 시작지점인 x지점 부터 도착 지점인 y지점까지 정확히 도달하는데 필요한 최소한의 장치 작동 횟수를 출력

3. 제약 조건
    - (0 <= x < y < 2^31)
풀이
1. 알고리즘
    - 기본적인 개념
        - 도착 지점에 도달하기 바로 직전의 이동거리는 반드시 1광년 이어야 함.
            - 즉, 출발부터 도착까지 대칭적인 형태가 이뤄져야 함.
            - 1,2,3...3,2,1,도착 이러한 형태로 만들어지기 때문
            - 직전 이동의 +-1만 이동 가능하기 때문에!
        - 그렇다면 y - x 거리를 n이라고하였을 경우 다음과 같은 두가지 조건이 생김
            - 만약 k까지 증가하였다가 1까지 감소하는 경우
                - (K까지 증가 분) + (K ~ 1까지 감소분)
                - k * (k + 1) / 2 + (k - 1) * k / 2 = k * 2k / 2 = k^2
            - 만약 k까지 증가하고 추가로 k로 한번 더 이동하는 경우
                - (위 계산 결과 + k)
                - k^2 + k = k * (k + 1)
        - 이때 주의해야 할 점
            - n <= k^2
                - 만약 n이 k^2보다 작거나 같다면, k번 이동만으로 최대 속도에 도착할 수 있음. 즉 2*k - 1번의 이동으로 도착 가능
            - k^2 < n <= k(k * 1)
                - 위의 경우는 k번 이동 + 1번의 이동이 추가적으로 필요. 즉 2*k -1 + 1 = 2*k의 이동으로 도착 가능
 */