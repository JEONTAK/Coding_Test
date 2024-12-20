package BaekJoon.Gold4.가장긴바이토닉부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11054 {

    static int N;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[]LIS = new int[N];
        int[]LDS = new int[N];
        int[]A = new int[N];
        int[]reverseA = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            reverseA[N - i - 1] = A[i];
        }

        for (int i = 0; i < N; i++) {
            LIS[i] = compute(Arrays.copyOfRange(A, 0, i + 1));
            LDS[N - i - 1] = compute(Arrays.copyOfRange(reverseA, 0, i + 1));
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, LIS[i] + LDS[i] - 1);
        }

        System.out.println(result);
    }

    private static int compute(int[] arr) {
        int[] tempS = new int[N];
        tempS[0] = arr[0];
        int len = 1;
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            if (tempS[len - 1] < cur) {
                len++;
                tempS[len - 1] = cur;
            }else{
                int low = 0;
                int high = len;

                while (low < high) {
                    int mid = (low + high) / 2;
                    if (tempS[mid] < cur) {
                        low = mid + 1;
                    }else{
                        high = mid;
                    }
                }
                tempS[low] = cur;
            }
        }
        return len;
    }

    public static void main(String[] args) throws IOException {
        BOJ11054.solution();
    }
}

/*
문제 분석
1. 정보
    - 수열 S가 어떤 수 S_k를 기준으로  S_1 < S_2 < ... < S_k-1 < S_k > S_k+1 > ... > S_N-1 > S_N을 만족한다면, 해당 수열은 바이토닉 수열
    
2. 목표
    - 수열 A가 주어졌을 때, 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 출력

3. 제약 조건
    1 <= N <= 1000
    1 <= A_i <= 1000

풀이
1. 알고리즘
    - LBS 문제(DP 활용하여 해결)
        1. 증가 부분 수열(LIS) 계산
            - 수열 A에서 각 인덱스 i를 끝으로 하는 가장 긴 증가 부분 수열의 길이를 계산하여 배열에 저장(LIS[i])
        2. 감소 부분 수열(LDS) 계산
            - 수열 A에서 각 인덱스 i를 시작으로 하는 가장 긴 감소 부분 수열의 길이를 계산하여 배열에 저장(LDS[i])
        3. 바이토닉 수열(BS) 계산
            - 각 인덱스 i에서 LIS[i] + LDS[i] - 1로 바이토닉 수열 계산(BS[i])
    - LIS, LDS 알고리즘
          - LDS는 수열 A를 뒤집어서 LIS를 실행한 값을 받아오면, LDS가 됨
          - 이분 탐색 활용
                    - tempS[] 배열 생성해 해당 값에 부분 수열 값 저장
                    - 조건에 따라 시작 or 끝 인덱스 값을 tempS[0]에 저장
                    - 조건에 따라 시작 ~ N or 0 ~ 끝 범위에서 수행
                        - 만약 현재 값이 tempS[] 최근 값보다 (조건에 따라) 크다면 or 작다면
                        - 해당 값을 tempS[]에 추가하고 길이를 1 늘림
                        - 아니라면, tempS[]에서 현재 값보다 (조건에 따라) 이상 or 이하인 첫 위치를 찾아 해당 값을 현재 값으로 대체
                        - 여기서 이분 탐색을 통해 현재 값이 들어갈 위치를 구함
                            - low = 0, high = len으로 초기화
                            - low < high 동안
                                - low와 high의 중간 값을 구하고 tempS[중간값]의 값이 현재 값보다 (조건에 따라) 크다면 or 작다면
                                - low = mid + 1
                                - 아니라면 high = mid
                            - 반복문 끝나면
                                - tempS[low] = 현재 값이 됨.

2. 탐색 과정
    1. 수열 A를 입력 받아온 후 저장
    2. LBS 알고리즘
        1. 증가 부분 수열(LIS) 계산
            - 수열 A에서 각 인덱스 i를 끝으로 하는 가장 긴 증가 부분 수열의 길이를 계산하여 배열에 저장(LIS[i])
        2. 감소 부분 수열(LDS) 계산
            - 수열 A에서 각 인덱스 i를 시작으로 하는 가장 긴 감소 부분 수열의 길이를 계산하여 배열에 저장(LDS[i])
        3. 바이토닉 수열(BS) 계산
            - 각 인덱스 i에서 LIS와 LDS를 사용해 바이토닉 수열 계산(BS[i])
    3. BS[] 중 가장 큰 값을 출력

 */
