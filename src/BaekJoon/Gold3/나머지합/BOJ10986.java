package BaekJoon.Gold3.나머지합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10986 {

    static int N, M;
    static long[] count;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = new long[M];
        long sum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            sum += Integer.parseInt(st.nextToken());
            int mod = (int) (sum % M);
            if(mod < 0) mod += M;
            count[mod]++;
        }

        long result = count[0];
        for (int i = 0; i < M; i++) {
            if (count[i] > 1) {
                result += (count[i] * (count[i] - 1)) / 2;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ10986.solution();
    }
}
/*
문제 분석
1. 정보
    - 수열 길이 N (1 <= N <= 10^6)
    - 나누는 값 M (2 <= M <= 10^3)
    - 수열 A[1],A[2],...,A[N] (0 <= A[i] <= 10^9)

2. 목표
    - 구간 합이 M으로 나누어 떨어지는 경우의 수를 계산

풀이
1. 수학적 분석
    - 연속 구간의 합
        - S[j] = A[1] + A[2] + ... + A[j]
    - S[j] - S[i - 1]이 M으로 나누어 떨어지려면
        - (S[j] - S[i - 1]) % M = 0 -> S[j] % M = S[i - 1] % M
        - 이를 통해 S[j] % M이 같은 값이 같은 값을 가지고 있는 인덱스 쌍을 찾으면 해결

2. 누적합 + 나머지 활용
    1. 누적합
        - 각 인덱스 j에 대해 누적합 S[j]를 계산
        - S[j] % M 값을 저장
    2. 나머지의 개수 집계
        - 나머지 값을 count[0]...count[M - 1]로 집계
        - 나머지가 같은 인덱스 쌍에서 구간 합이 M으로 나누어 떨어짐
    3. 결과 계산
        - 나머지가 r인 값이 k개 일때, 조합의 수
            - k * (k - 1) / 2
        - 추가로 나머지가 0인 경우, 해당 값 자체가 조건을 만족하므로 count[0]만큼 추가
 */