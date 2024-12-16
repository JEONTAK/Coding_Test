package BaekJoon.Silver3._01타일;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1904 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N == 1) {
            System.out.println(1);
            return;
        }
        else if(N == 2) {
            System.out.println(2);
            return;
        }

        int nMinus2 = 1;
        int nMinus1 = 2;
        int mod = 15746;

        for (int i = 3; i <= N; i++) {
           int cur = (nMinus1 + nMinus2) % mod;
           nMinus2 = nMinus1;
           nMinus1 = cur;
        }
        System.out.println(nMinus1);
    }

    public static void main(String[] args) throws IOException {
        BOJ1904.solution();
    }
}
/*
문제 분석
1. 정보
    - 타일의 정보
        - 0 또는 1이 쓰여있음
    - 동주의 방해로 다음과 같이 타일의 정보가 추가
        - 1 또는 00으로 이루어짐
2. 목표
    - N이 주어졌을떄, 크기가 N인 만들 수 있는 모든 가짓수 를 15746으로 나눈 나머지를 출력

3. 제약 조건
    - N (1 <= N <= 1000000)
    - 00이 붙어있으므로, N = 2일떄는 00, 11만 가능, N = 3일때는 001, 100, 111만 가능
풀이
1. 알고리즘
    - DP
        - N - 2, N - 1의 가짓수를 담는 값 생성
        - 결과값 = N의 길이를 가진 타일 조합의 가짓 수 % 15746의 값
    - 여기에 두가지 경우가 있을 수 잇음
        - 마지막 타일이 1일 경우
            - 길이가 N-1인 값에 1을 붙인 것
        - 마지막 타일이 00인 경우
            - 길이가 N-2인 값에 00을 붙인 것
    - 따라서 현재 값 = N - 2 + N - 1를 더한 것에서 15746 나눈 나머지의 값
    - 즉, DP[N] = (DP[N - 1] + DP[N - 2]) % 15476

- 주의할 점
    - 피보나치와 유사해서 재귀방식으로 풀면 시간초과남 (N이 최대 1000000이기 때문)
    - DP[]배열을 사용할 경우에도 N의 크기 때문에 메모리 초과가 날 가능성 있음
    - 우리는 그 N - 2, N - 1값만 알고 있으면 답을 구할 수 있기 때문에, 해당 두값만 업데이트 해주는 방식으로 풀면 됨
 */