package Programmers.Codekata;

import java.util.Arrays;

public class _122_테이블해시함수 {

    class Solution {
        public int solution(int[][] data, int col, int row_begin, int row_end) {

            Arrays.sort(data, ((o1, o2) -> {
                if (o1[col - 1] == o2[col - 1]) {
                    return o2[0] - o1[0];
                }
                return o1[col - 1] - o2[col - 1];
            }));

            int answer = 0;

            for (int i = 0; i < data[row_begin - 1].length; i++) {
                answer += data[row_begin - 1][i] % row_begin;
            }

            for (int i = row_begin; i < row_end; i++) {
                int sum = 0;
                for (int j = 0; j < data[i].length; j++) {
                    sum += data[i][j] % (i + 1);
                }

                answer = answer ^ sum;
            }

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 완호과 관리하는 어떤 데이터베이스의 한 테이블은 모두 정수 타입인 컬럼들로 이루어짐
    - 테이블은 2차원 행렬이며, 열은 컬럼, 행은 튜플을 나타냄
    - 첫 번째 컬럼은 기본키로서 모든 튜풀에 대해 그 값이 중복되지 않도록 보장.
        - 1. 해시 함수는 col, row_begin, row_end를 입력 받음
        - 2. 테이블의 튜플을 col번째 컬럼의 값을 기준으로 오름차순 정렬하되, 만약 그 값이 동일하면 기본키인 첫 번째 컬럼의 값을 기준으로 내림차순 정렬
        - 3. 정렬된 데이터에서 S_i를 i번째 행의 튜플에 대해 각 컬럼의 값을 i로 나눈 나머지들의 합으로 정의
        - 4. row_begin <= i <= row_end인 모든 S_i를 누적하여 bitwise XOR한 값을 해시 값으로 반환

2. 목표
    - 데이터 data와 해시 함수에 대한 입력 col, row_begin, row_end가 주어질 때, 테이블의 해시 값을 return
3. 제약 조건
    - 1 <= data의 길이 <= 2500
    - 1 <= data 원소의 길이 <= 500
    - 1 <= data[][] <= 1000000
    - 1 <= col <= data 원소의 길이
    - 1 <= row_begin <= row_end <= data의 길이

풀이
1. 아이디어
    - 시뮬레이션으로 구현
        - 먼저 data[][col - 1] 값을 기준으로 오름차순
            - 같다면 data[][0] 값을 기준으로 내림차순 정렬함
        - 정렬이 된 이후
            - data[row_begin - 1][] 의 값들을 row_begin으로 나눈 나머지의 합을 answer에 저장
            - row_begin ~ row_end - 1 까지 (i)
                - sum 선언
                - sum = data[i][]의 값들을 i + 1로 나눈 나머지의 합
                - answer = answer ^ sum 을 해주어 XOR 연산 진행
            - row_end - 1 까지 끝났다면 answer return
*/
