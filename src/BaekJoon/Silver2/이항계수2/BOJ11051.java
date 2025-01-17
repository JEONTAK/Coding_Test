package BaekJoon.Silver2.이항계수2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11051 {

    static final long P = 1_000_000_007;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        long top = factorial(N);

        long bottom = factorial(K) * factorial(N - K) % P;

        System.out.println(top * compute(bottom, P - 2) % P);
    }

    private static long factorial(long N) {
        long num = 1L;

        while (N > 1) {
            num = (num * N) % P;
            N--;
        }
        return num;
    }

    static long compute(long a, long b) {
        if (b == 1) {
            return a % P;
        }

        long tmp = compute(a, b / 2);

        if (b % 2 == 1) {
            return (tmp*tmp % P) * a % P;
        }

        return tmp * tmp % P;
    }

    public static void main(String[] args) throws IOException {
        BOJ11051.solution();
    }
}

/*
문제 분석
1. 정보
    - 자연수 N 과 정수 K가 주어졌을 때 이항 계수 C(N,K)를 10,007로 나눈 나머지를 구하는 프로그램을 작성
2. 목표
    - 자연수 N 과 정수 K가 주어졌을 때 이항 계수 C(N,K)를 10,007로 나눈 나머지를 출력
3. 제약 조건
    - 1 <= N <= 1000
    - 0 <= K <= N

풀이
1. 아이디어
    - 모듈러 연산 및 페르마의 소정리, 분할 정복을 사용하여 풀이
    - 자세한 내용은 [여기](https://velog.io/@tak980418/%EC%9D%B4%ED%95%AD-%EA%B3%84%EC%88%98-%EB%B0%8F-%EA%B4%80%EB%A0%A8-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%A0%95%EB%A6%AC)참고

*/
