package BaekJoon.Silver2.가장긴증가하는부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11053 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[N];
        result[0] = A[0];
        int len = 1;

        for (int i = 1; i < N; i++) {
            int cur = A[i];
            if (result[len - 1] < cur) {
                result[len++] = cur;
            }else{
                int low = 0;
                int high = len;
                while (low < high) {
                    int mid = (low + high) / 2;
                    if (result[mid] < cur) {
                        low = mid + 1;
                    }
                    else{
                        high = mid;
                    }
                }
                result[low] = cur;
            }
        }
        System.out.println(len);
    }

    public static void main(String[] args) throws IOException {
        BOJ11053.solution();
    }
}

/*
문제 분석
1. 정보
    - 수열 A가 주어짐
2. 목표
    - 이때 가장 긴 증가하는 부분 수열을 구하는 프로그램 작성
3. 제약 조건
    - 수열의 크기 N : 1 <= N <= 1000
    - 수열 A를 이루고 있는 A_i : 1 <= A_i <= 1000

풀이
1. 알고리즘
    - 가장 긴 증가하는 부분 수열 : LIS
    - 이분 탐색 활용
        - result[] 배열 생성해 해당 값에 부분 수열 값 저장
            - 조건에 따라 시작 or 끝 인덱스 값을 result[0]에 저장
            - 조건에 따라 시작 ~ N or 0 ~ 끝 범위에서 수행
                - 만약 현재 값이 result[] 최근 값보다 (조건에 따라) 크다면 or 작다면
                - 해당 값을 result[]에 추가하고 길이를 1 늘림
                - 아니라면, result[]에서 현재 값보다 (조건에 따라) 이상 or 이하인 첫 위치를 찾아 해당 값을 현재 값으로 대체
                    - 여기서 이분 탐색을 통해 현재 값이 들어갈 위치를 구함
                        - low = 0, high = len으로 초기화
                        - low < high 동안
                        - low와 high의 중간 값을 구하고 tempS[중간값]의 값이 현재 값보다 (조건에 따라) 크다면 or 작다면
                        - low = mid + 1
                        - 아니라면 high = mid
                        - 반복문 끝나면
                        - result[low] = 현재 값이 됨.
 */