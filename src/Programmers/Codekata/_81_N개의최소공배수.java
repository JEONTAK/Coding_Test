package Programmers.Codekata;

public class _81_N개의최소공배수 {

    class Solution {
        public int solution(int[] arr) {
            int answer = 0;
            if (arr.length == 1) {
                return arr[0];
            }
            answer = arr[0] * arr[1] / GCD(arr[0], arr[1]);
            for (int i = 2; i < arr.length; i++) {
                answer = answer * arr[i] / GCD(answer, arr[i]);
            }
            return answer;
        }

        public int GCD(int a, int b) {
            if (b == 0) {
                return a;
            }
            return GCD(b, a % b);
        }
    }

}

/*
문제 분석
1. 정보
    - 두 수의 최소공배수란 입력된 두 수의 배수중 공통이 되는 가장 작은 숫자를 의미함.
        - 예를 들어 2와 7의 최소 공배수는 14가 된다.
    - N개의 수의 최소공배수는 N 개의 수들의 배수 중 공통이 되는 가장 작은 숫자가 됨.
2. 목표
    - N개의 숫자가 배열로 입력되었을 때, 이 수들의 최소공배수를 return
3. 제약 조건
    - 1 <= N <= 15
    - 수 <= 100

풀이
1. 아이디어
    - 두 수의 최소 공배수 = 두 숫자의 곱 / 최대 공약수
    - 들어온 배열은 arr
    - arr[0]과 arr[1]의 최소 공배수를 구함
    - 이후 arr[2]부터 이전에 구한 최소 공배수와 최대 공약수를 구하여 최소 공배수를 업데이트함
    - 배열 끝까지 업데이트 한 후 마지막 최소 공배수를 return
        - EX) 2 6 8 14
            - 1. 2와 6의 최소 공배수 = 2 * 6 / 2 = 6
            - 2. 6과 8의 최소 공배수 = 6 * 8 / 2 = 24
            - 3. 24와 14의 최소 공배수 = 24 * 14 / 2 = 168
            - 168 return
*/
