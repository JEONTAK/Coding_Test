package BaekJoon.Silver3.주유소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13305 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long[][] city = new long[N][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            city[i][0] = Long.parseLong(st.nextToken());
        }

        long minOilPrice = 1_000_000_001;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long curOilPrice = Long.parseLong(st.nextToken());
            if (minOilPrice > curOilPrice) {
                minOilPrice = curOilPrice;
            }
            city[i][1] = minOilPrice;
        }

        long result = 0;

        for (int i = 0; i < N - 1; i++) {
            result += city[i][0] * city[i][1];
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ13305.solution();
    }
}
/*
문제 분석
1. 정보
    - N개의 도시 (2 <= N <= 100000)
    - 도시는 일직선 도로 위에 있음
    - 자동차는 주유소에서 기름을 넣어야 함
        - 기름통의 크기는 무제한
    - 도시마다 주유소의 리터당 가격이 다름
    - 도시의 정보(다음 도시까지의 거리, 리터당 가격)

2. 목표
    - 제일 왼쪽 도시에서 제일 오른쪽 도시로 이동하는 최소의 비용을 출력

풀이
1. 알고리즘
    - 그리디 알고리즘
        - 리터당 가격에 대한 정보를 입력 받을때, 현재까지의 리터당 가격에 대한 최솟값을 따로 저장해 놓고, 해당 값보다 작으면 값을 업데이트
        - 해당 값보다 크다면 최솟값을 대신 저장
    - 위 알고리즘의 이유
        - 조건에 기름통은 크기가 무제한이라 나와 있다. 따라서 이전 도시의 가격이 제일 낮다면 끝까지 갈 연료를 모두 채우고 가는 것이 정답이 될 것이다.
        - 따라서 현재 까지 나온 가격들 중 최솟값을 따로 저장해 놓아 최솟값과 비교해가며 값을 저장한 후, 다음 도시까지의 거리와 기름의 가격을 곱한 값들을 더해주면 결과가 나올 것이다.
2. 탐색 과정
  - 그리디 알고리즘
        - 리터당 가격에 대한 정보를 입력 받을때, 현재까지의 리터당 가격에 대한 최솟값을 따로 저장해 놓고, 해당 값보다 작으면 값을 업데이트
        - 해당 값보다 크다면 최솟값을 대신 저장
    - 위 알고리즘의 이유
        - 조건에 기름통은 크기가 무제한이라 나와 있다. 따라서 이전 도시의 가격이 제일 낮다면 끝까지 갈 연료를 모두 채우고 가는 것이 정답이 될 것이다.
        - 따라서 현재 까지 나온 가격들 중 최솟값을 따로 저장해 놓아 최솟값과 비교해가며 값을 저장한 후, 다음 도시까지의 거리와 기름의 가격을 곱한 값들을 더해주면 결과가 나올 것이다.

3. 주의할 점
    - 최대 도로의 길이가 10^9, 리터당 가격이 10^9 이므로, 최대 10^18이 나올 수 있다.
    - 따라서 long으로 결과값을 선언하여 값을 계산해 주었다.
 */