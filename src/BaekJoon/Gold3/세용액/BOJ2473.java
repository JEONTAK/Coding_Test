package BaekJoon.Gold3.세용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2473 {
    
    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] water = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            water[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(water);
        long[] result = new long[3];
        long resultSum = Long.MAX_VALUE;

        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = water[i] + water[left] + water[right];

                if (Math.abs(sum) < resultSum) {
                    resultSum = Math.abs(sum);
                    result[0] = water[i];
                    result[1] = water[left];
                    result[2] = water[right];
                }

                if (sum < 0) {
                    left++;
                }else{
                    right--;
                }
            }
        }

        Arrays.sort(result);
        for (long i : result) {
            System.out.print(i + " ");
        }
    }
    
    public static void main(String[] args) throws IOException {
        BOJ2473.solution();
    }
}

/*
문제 분석
1. 정보
    - 산성 용액
        - 1 ~ 10억
    - 알칼리 용액
        - -1 ~ -10억
    
2. 목표
    - 위 용액들 중 세 가지 용액을 합성하여 0에 가장 가까운 용액을 찾아 출력

3. 제약 조건
    - 용액 : -10^9 <= 용액 <= 10^9
    - 용액의 수 N : 3 <= N <= 5000
    - 출력 시 특성 값의 오름차순으로 출력 해야 함

풀이
1. 알고리즘
    - 두 포인터 
        - 용액의 양쪽 끝에 포인터를 두어 용액의 합이 용액 합의 최솟값보다 작다면 값을 업데이트 해주고, 왼쪽 포인터를 1 추가
        - 만약 최솟값보다 크다면 오른쪽 포인터를 1 감소
2. 탐색 과정
    - 먼저 입력받은 용액의 특성값들을 저장후, 오름차순으로 정렬한다.
    - 하나의 용액은 고정하고 투 포인터를 사용해 최솟값을 구한다.
        -이때 고정한 하나의 용액을 첫번째 용액부터 마지막 용액까지 모두 계산하도록 한다.
        - 두개의 포인터를 사용한다 (0, N - 1)
            - 만약 left위치 + right위치 + 고정한 용액의 절댓값이 최솟값보다 작다면 결과값을 업데이트 하고,
            - 만약 합친 값이 0보다 작으면 left를 증가, 합친 값이 0보다 크다면 right를 감소 시켜 다시 계산한다.
    - 모든 용액에서 실행한 후, 저장된 결과값을 오름차순으로 출력한다.
*/