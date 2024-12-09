package BaekJoon.Gold3.소수의연속합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ1644 {

    static int N;
    static boolean[] isPrime;
    static ArrayList<Integer> list = new ArrayList<>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
//에라토스테네스의 체
        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= N; i++) {
            if(isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i <= N; i++) {
            if(isPrime[i]) {
                list.add(i);
            }
        }


        //투 포인터 활용
        int start = 0, end = 0, current_sum = 0, result = 0;

        while (end < list.size()) {
            if (current_sum < N) {
                current_sum += list.get(end++);
            } else if (current_sum > N) {
                current_sum -= list.get(start++);
            } else{
                result++;
                current_sum += list.get(end++);
            }
        }

        while(current_sum >= N && start < list.size()) {
            if (current_sum == N) {
                result++;
            }
            current_sum -= list.get(start++);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ1644.solution();
    }
}

/*
문제 분석
1. 정보
    - 자연수 N (1 <= N <= 4000000)

2. 목표
    - 자연수 N이 주어졌을떄, 해당 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수 구하기

3. 제약 조건
    - 한 소수는 한 번만 덧셈에 사용뒬 수 있다. 즉, 두번 이상 사용하면 안됨
    - 반드시 연속된 소수의 합으로 나타내야 한다.
풀이
1. 알고리즘
    - 에라토스테네스의 체
        - 1부터 N까지 해당 알고리즘을 사용하여 소수인지 처리한다.
    - 투 포인터 활용
        - 구한 소수들을 이용해 연속된 소수의 합으로 N을 표현할 수 있는 경우의 수 계산

2. 탐색 과정
    1. 에라토스테네스의 체를 이용하여 1부터 N까지 소수 판별을 한다
    2. 투 포인터를 활용하여 경우의 수를 구한다
        - start, end를 선언
            - start는 시작, end는 끝을 의미
        - current_sum이 N보다 작으면 end를 증가시킴
        - current_sum이 N보다 크면 start를 증가시킴
        - current_sum이 N과 같다면 경우의 수를 증가시키고, N을 증가

3. 종료 조건
    - end가 마지막을 넘어가거나, start가 end와 같아진다면 종료

 */