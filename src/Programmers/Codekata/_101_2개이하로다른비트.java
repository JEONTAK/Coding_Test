package Programmers.Codekata;

public class _101_2개이하로다른비트 {

    class Solution {
        public long[] solution(long[] numbers) {
            long[] answer = new long[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                long x = numbers[i];

                if (x % 2 == 0) {
                    answer[i] = x + 1;
                } else {
                    long mask = 1;
                    while ((x & mask) != 0) {
                        mask <<= 1;
                    }

                    answer[i] = (x | mask) & ~(mask >> 1);
                }
            }

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 양의 정수 x에 대한 함수 f(x)는 다음과 같다.
        - x보다 크고 x와 비트가 1~2개 다른 수들 중에서 제일 작은 수
2. 목표
    - 정수가 담긴 배열이 주어질때, 해당 배열의 모든 수들에 대하여 각 수의 f 값을 배열에 차례대로 담아 return
3. 제약 조건
    - 1 <= 정수가 담긴 배열 <= 100000
    - 0 <= numbers의 모든 수 <= 10^15

풀이
1. 아이디어
     - x가 짝수인 경우, 마지막 비트는 반드시 0이다. 따라서 x + 1이 가장 가까운 수가 된다,
     - x가 홀수인 경우
        - 만약 x가 7이라면, 0111이다.
        - 해당 값과 조건에 맞고 가장 가까운 값은 11 : 1011이다.
        - 여기서 알 수 있는 점은 가장 오른쪽에 있는 0을 1로 바꾸고, 바로 뒤의 1을 0으로 바꾸면 해당 값이 최소값이다.
     - 즉
         - x가 짝수인 경우 x + 1값 저장
         - x가 홀수인 경우
            - x를 2진법으로 바꾸었을때 가장 오른쪽에 있는 0을 찾기 위해서
                - mask = 1로 설정
                - x와 mask를 & 하여 0이 나온다면 해당 bit가 0이라는 뜻
                    - 즉 (x & mask) != 0 동안
                        - mask << 1씩 증가
                 - 0의 위치를 mask를 통해 구하였고
                 - mask를 이용해 mask자리는 0, mask >> 1 자리는 반대로 바꾸어 주면 최솟값 구하기 가능
                    - (x | mask) & ~(mask >> 1);
*/
