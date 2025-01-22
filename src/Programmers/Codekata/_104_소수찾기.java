package Programmers.Codekata;

import java.util.HashSet;
import java.util.Set;

public class _104_소수찾기 {

    class Solution {

        Set<Integer> set = new HashSet<>();
        boolean[] visited;

        public int solution(String numbers) {
            visited = new boolean[numbers.length()];

            for (int i = 1; i <= numbers.length(); i++) {
                compute("", numbers, 0, i);
            }

            for (Integer i : set) {
                System.out.println(i);
            }
            return set.size();
        }

        private void compute(String s, String numbers, int idx, int max) {
            if (idx == max) {
                int num = Integer.parseInt(s);
                if (isPrime(num)) {
                    set.add(num);
                }
                return;
            }

            for (int i = 0; i < numbers.length(); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    compute(s + numbers.charAt(i), numbers, idx + 1, max);
                    visited[i] = false;
                }
            }
        }

        private boolean isPrime(int n) {
            if (n < 2) {
                return false;
            }

            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }

}

/*
문제 분석
1. 정보
    - 한자리 숫자가 적힌 종이 조각들이 흩어져 있다.
    - 흩어진 종이 조각을 붙여 소수를 몇개 만들 수 있는지 알아내려 한다.
    
2. 목표
    - 각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을때, 종이 조각으로 만들 수 있는 소수가 몇 개 인지 return
3. 제약 조건
    - 1 <= numbers 길이 <= 7
    - 0 <= numbers의 원소 <= 9
    - "013"은 0,1,3 숫자가 적힌 종이 조각이 흩어져 있다는 의미

풀이
1. 아이디어
    - 최대 길이는 7
        - 브루트포스를 통해 모든 가능한 숫자의 조합을 찾음
        - 해당 숫자가 소수인지 판별하고, 소수이면 소수 모음에 저장
        - 소수 모음의 길이를 return
    - compute() 메서드를 길이가 1부터 해당 numbers의 길이까지 모두 구함
*/