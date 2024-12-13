package BaekJoon.Silver2.베르트랑공준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ4948 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        final int max = 123456;
        boolean[] isPrime = new boolean[2 * max + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= 2 * max + 1; i++) {
            if(isPrime[i]) {
                for (int j = i * i; j <= 2 * max + 1; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int n = Integer.parseInt(br.readLine());
        while (n > 0) {
            int result = 0;
            for (int i = n + 1; i <= 2 * n; i++) {
                if(isPrime[i])result++;
            }
            sb.append(result).append("\n");
            n = Integer.parseInt(br.readLine());
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ4948.solution();
    }
}

/*
문제 분석
1. 정보
    - 자연수 n (1 <= n <= 123456)
    - 임의의 자연수 n에 대하여, n보다 크고, 2n보다 작거나 같은 소수는 적어도 하나 존재한다.

2. 목표
    - 자연수 n이 주어졌을때, n보다 크고, 2n보다 작거나 같은 소수의 개수를 출력

풀이
1. 알고리즘
    - 에라토스테네스의 체
        -에라토스테네스의 체를 사용해 먼저 2n 까지의 소수에 대한 판별을 진행한다

2. 탐색 과정
    - 에라토스테네스의 체
        -에라토스테네스의 체를 사용해 먼저 2n 까지의 소수에 대한 판별을 진행한다
    - 이후 n 부터 2n까지 소수의 개수를 구한 후 출력한다.
 */
