package BaekJoon.Silver1.회의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1931 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[][] meeting = new long[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meeting[i][0] = Integer.parseInt(st.nextToken());
            meeting[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(meeting, (o1, o2) -> {
            if(o1[1] == o2[1]) return (int) (o1[0] - o2[0]);
            return (int) (o1[1] - o2[1]);
        });

        int result = 0;
        long endTime = 0;
        for (long[] current : meeting) {
            if (current[0] >= endTime) {
                result++;
                endTime = current[1];
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ1931.solution();
    }
}

/*
문제 분석
1. 정보
    - 1개의 회의실
    - N개의 회의 (1 <= N <= 100000)
    - 각 회의 I에 대한 정보
        - 시작 시간, 끝나는 시간

2. 목표
    - 사용할 수 있는 회의의 최대 개수 출력

3. 제약 조건
    - N : (1 <= N <= 100000)
    - 시간 : (0 <= 시간 <= 2^31 - 1)

풀이
1. 알고리즘
    - 정렬
        - 받은 값을 끝나는 시간 오름차순, 시작 시간 오름차순으로 정렬
    - 그리디 알고리즘
        - 정렬된 값 사용해서 하나씩 뽑음
2. 탐색 과정
    - 입력 받은 회의의 정보를 정렬한다.
        - 끝나는 시간 오름차순, 시작 시간 오름차순으로 정렬
    - 정렬한 값을 사용한다.
        - 회의를 배치하면서 해당 회의가 끝나는 시간을 endTime으로 저장
        - 만약 다음 뽑은 회의가 endTime보다 일찍 시작한다면 넘어감
        - 만약 다음 뽑은 회의가 endTime보다 늦거나 같게 시작한다면
            - result++
            - endTime을 다음 뽑은 회의의 종료시간으로 설정
 */