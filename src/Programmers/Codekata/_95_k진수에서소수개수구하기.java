package Programmers.Codekata;

public class _95_k진수에서소수개수구하기 {

    class Solution {
        public int solution(int n, int k) {
            int answer = 0;
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                sb.append(n % k);
                n /= k;
            }
            sb.reverse();

            String[] numbers = sb.toString().split("0");

            for (String number : numbers) {
                if (!number.isEmpty() && isPrime(Integer.parseInt(number))) {
                    answer++;
                }
            }

            return answer;
        }

        private boolean isPrime(int num) {
            if(num < 2) return false;
            if(num < 4) return true;
            for(int i = 2 ; i <= Math.sqrt(num) ; i++){
                if(num % i == 0) return false;
            }
            return true;
        }
    }

}

/*
문제 분석
1. 정보
    - 양의 정수 n이 주어짐
    - n을 k진수로 바꿨을 때, 아래 조건에 맞는 소수가 몇개 인지 알아보려 함.
        - 0P0처럼 소수 양쪽에 0이 있는 걍우
        - P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
        - 0P처럼 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
        - P처럼 소수 양쪽에 아무것도 없는경우
2. 목표
    - n을 k진수로 바꿨을 때, 조건에 맞는 소수의 개수를 return

3. 제약 조건
    - 1 <= n <= 1000000
    - 3 <= k <= 10

풀이
1. 아이디어
    - 먼저 n을 k진수로 변환
        - 변환한 값 reverse
        - 해당 값을 0을 기준으로 split
        - split한 값 모두 탐색
            - 해당값이 비어있지 않으면
                - 소수인지 판별
            - 소수라면 answer++;
        - 모두 탐색 한 후 answer return

    - 소수인지 판별
        - 만약 n < 2 : return false
        - i = 2 ; i < Math.sqrt(n) ; i++ 까지
            - 만약 n % i == 0 : return false;
        - return true;
*/
