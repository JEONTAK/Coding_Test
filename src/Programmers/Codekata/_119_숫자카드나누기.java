package Programmers.Codekata;

public class _119_숫자카드나누기 {

    class Solution {
        public int solution(int[] arrayA, int[] arrayB) {

            int gcdA = getGCD(arrayA);
            int gcdB = getGCD(arrayB);
            int answer = 0;

            if (isAvailable(gcdA, arrayB)) {
                answer = gcdA;
            }

            if (isAvailable(gcdB, arrayA)) {
                answer = Math.max(answer, gcdB);
            }

            return answer;
        }

        private boolean isAvailable(int gcd, int[] arr) {
            for (int cur : arr) {
                if (cur % gcd == 0) {
                    return false;
                }
            }
            return true;
        }

        private int getGCD(int[] arr) {
            int result = arr[0];

            for (int i = 1; i < arr.length; i++) {
                result = gcd(result, arr[i]);
                if (result == 1) {
                    return 1;
                }
            }
            return result;
        }

        private int gcd(int a, int b) {
            if (b == 0) return a;
            return gcd(b, a % b);
        }
    }

}

/*
문제 분석
1. 정보
    - 철수와 영희는 선생님으로부터 숫자가 적힌 카드들을 절반씩 나눠 가진 후, 다음 두 조건 중 하나를 만족하는 가장 큰 양의 정수 a의 값을 구하려고 함
        - 1. 철수가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고 영희가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a
        - 2. 영희가 가진 카드들에 적힌 모든 수자를 나눌 수 있고, 철수가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a

2. 목표
    - 철수가 가진 카드에 적힌 숫자들을 나타내는 정수 배열 arrayA와 영희가 가진 카드에 적힌 숫자들을 나타내는 정수 배열 arrayB가 주어졌을 때, 주어진 조건을 만족하는 가장 큰 양의 정수 a를 return
3. 제약 조건
    - 1 <= arrayA의 길이 = arrayB의 길이 <= 500000
    - 1 <= arrayA의 원소, arrayB의 원소 <= 100_000_000

풀이
1. 아이디어
    - arrayA의 공약수와 arrayB의 공약수를 구하여 둘다 없고 가장 큰 정수를 return 하면 될 것이라 생각 함.
    - 추가적인 생각. 최대 공약수를 구한다면, 해당 공약수의 약수들이 결국 array의 공약수가 될 것이라 생각함.
    - 따라서 먼저 arrayA의 최대 공약수를 구하려고 함
        - 유클리드 호제법 사용하여 원소의 최대 공약수를 구함
        - 먼저 0번과 1번의 최대 공약수를 구하고, 이후 2번과 최대 공약수를 구하는 방식으로 마지막 원소까지 계산하여 최대 공약수를 구함
    - 최대 공약수를 구하였다면(gcdA, gcdB) 
        - gcdA가 arrayB의 모든 원소와 나누어 떨어지지 않는다면 성공
        - gcdB가 arrayA의 모든 원소와 나누어 떨어지지 않는다면 성공
        - gcdA와 gcdB 둘다 성공하였다면 둘중 더 큰 값 반환
*/
