package BaekJoon.Gold5.전깃줄;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2565 {

    static int N;
    static int[][] wire;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        wire = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            wire[i][0] = Integer.parseInt(st.nextToken());
            wire[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(wire, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        int[] seq = new int[N];
        seq[0] = wire[0][1];
        int len = 1;

        for (int i = 1; i < N; i++) {
            int cur = wire[i][1];
            if (seq[len - 1] < cur) {
                len++;
                seq[len - 1] = cur;
            }else{
                int low = 0;
                int high = len;

                while (low < high) {
                    int mid = (low + high) / 2;
                    if (seq[mid] < cur) {
                        low = mid + 1;
                    }else{
                        high = mid;
                    }
                }
                seq[low] = cur;
            }
        }

        System.out.println(N - len);
    }

    public static void main(String[] args) throws IOException {
        BOJ2565.solution();
    }
}

/*
문제 분석
1. 정보
    - 전봇대 A와 B사이 전깃줄이 여러개 존재
    - 전깃줄은 여러개여서 서로 교차하는 경우가 발생
    
2. 목표
    - 특정 전깃줄을 없애서 교차하는 경우가 발생하지 않도록 하였을때, 없애야 하는 전깃줄의 최소 개수를 출력

3. 제약 조건
    - 위치의 번호 N : 1 <= N <= 500
    - 전깃줄의 개수 M : 1 <= M <= 100

풀이
1. 알고리즘
    - LIS 사용
        - 가장 긴 증가하는 부분 수열을 찾음
        - 전체 위치 N에서 위에서 구한 수열의 길이 값을 빼주면 삭제할 전깃줄 수가 나옴

2. 탐색 과정
    - 가장 긴 증가하는 부분 수열(LIS) 사용
        - 먼저 전깃줄을 시작 번호 오름차순으로 정렬함.(wire[][])
        - seq[0] 에 wire[0][1] 값을 저장
        - len = 1로 초기화
        - 0 ~ N 까지 수행
            - 특정 위치 i의 값 cur = wire[i][1]
            - 만약 cur이 seq[len]보다 크다면
                - len + 1 해주고 seq[len]에 cur 저장
            - 아니라면
                - seq[] 배열에서 cur 값보다 이상인 첫 위치를 찾아 cur 값으로 업데이트
                - 이분 탐색 사용
                    - low = 0, high = len
                    - low < high 동안
                        - mid = low + high / 2
                        - 만약 seq[mid]값이 cur보다 작다면
                            - low = mid + 1
                        - 아니랴면
                            - high = mid
                    - 반복문 종료 이후
                        - seq[low] = cur로 업데이트
    - LIS를 사용해 구한 값을 N에서 빼면 해당 값이 최소한으로 제거해야 하는 전깃줄의 개수
*/
