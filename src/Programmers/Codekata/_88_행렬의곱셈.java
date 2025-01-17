package Programmers.Codekata;

public class _88_행렬의곱셈 {

    class Solution {
        public int[][] solution(int[][] arr1, int[][] arr2) {
            int[][] answer = new int[arr1.length][arr2[0].length];

            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < arr2[0].length; j++) {
                    int sum = 0;
                    for (int k = 0; k < arr1[0].length; k++) {
                        sum += arr1[i][k] * arr2[k][j];
                    }
                    answer[i][j] = sum;
                }
            }
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 2차원 행렬 arr1과 arr2를 입력받아, arr1에 arr2를 곱한 결과를 반환
2. 목표
    - 2차원 행렬 arr1과 arr2를 입력받아, arr1에 arr2를 곱한 결과를 반환
3. 제약 조건
    - 2 <= 행과 열의 길이 <= 100
    - -10 <= 원소 <= 20

풀이
1. 아이디어
    - arr1 -> a * b
    - arr2 -> b * c
    - 결과 배열 -> a * b
    - for(i : 0 ~ a)
        - for(j : 0 ~ c)
            - for(k : 0 ~ b)
                sum += arr1[i][k] * arr2[k][j]
            - answer[i][j] = sum
*/
