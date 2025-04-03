package Programmers.Codekata;

public class _153_연속펄스부분수열의합 {

    class Solution {
        public long solution(int[] sequence) {
            long answer = Long.MIN_VALUE;

            int[] pulse1 = new int[sequence.length];
            int[] pulse2 = new int[sequence.length];

            for (int i = 0; i < sequence.length; i++) {
                int sign = i % 2 == 0 ? 1 : -1;
                pulse1[i] = sequence[i] * sign;
                pulse2[i] = sequence[i] * sign * -1;
            }

            answer = Math.max(answer, findMaxSubSum(pulse1));
            answer = Math.max(answer, findMaxSubSum(pulse2));

            return answer;
        }

        private long findMaxSubSum(int[] pulse) {
            long maxSum = Long.MIN_VALUE;
            long curSum = 0;
            for (int cur : pulse) {
                curSum = Math.max(cur, curSum + cur);
                maxSum = Math.max(maxSum, curSum);
            }
            return maxSum;
        }
    }

}

/*
문제 분석
1. 정보
    - 어떤 수열의 연속 부분 수얼에 같은 길이의 펄스 수열을 각 원소끼리 곱하여 연속 펄스 부분 수열을 만들려 한다.
    - 펄스 수열이란 [1, -1, 1, -1, ...] 또는 [-1, 1, -1, 1, ...]과 같이 1 또는 -1로 시작하면서 1과 -1이 번갈아 나오는 수열이다.
    - 예를 들어 수열 [2, 3, -6, 1, 3, -1, 2, 4]의 연속 부분 수열 [3, -6, 1]에 펄스 수열 [1, -1, 1]을 곱하면 연속 펄스 부분수열은 [3, 6, 1]이 된다.
    - 또 다른 예시로 연속 부분 수열 [3, -1, 2, 4]에 펄스 수열 [-1, 1, -1, 1]을 곱하면 연속 펄스 부분수열은 [-3, -1, -2, 4]이 된다.
2. 목표
    - 정수 수열 sequence가 매개변수로 주어질 때, 연속 펄스 부분 수열의 합 중 가장 큰 것을 return
3. 제약 조건
    - 1 ≤ sequence의 길이 ≤ 500,000
    - -100,000 ≤ sequence의 원소 ≤ 100,000
        - sequence의 원소는 정수입니다.

풀이
1. 아이디어
    - 펄스 수열은 1로 시작하거나, -1로 시작하거나 두가지 경우가 존재.
    - 따라서 sequence에서 1로 시작하는 펄스 수열을 곱한 값을 다른 배열에 저장.
    - 추가로 sequence에서 -1로 시작하는 펄스 수열을 곱한 값을 다른 배열에 저장.
    - 위 2가지 배열에서 부분 합들 중 최대 값을 구하면 해당 값이 정답이 됨.
    - 따라서 부분 합들 중 최대 값을 구해야 함.
        - 각 배열을 사용하여
            - 처음부터 끝까지 순회
            - 현재 합을 미리 0으로 초기화 하여 부분 합을 구할 수 있도록 함
            - 현재 합 = 현재 숫자, 현재 숫자 + 현재 합 두가지 수 중 최댓 값
            - 최대 부분 합 결과 = 저장된 최대 부분 합, 현재 합 중 최댓 값
    - 위에서 최댓 값을 구한 후 return
*/
