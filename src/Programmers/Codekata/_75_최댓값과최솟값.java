package Programmers.Codekata;

public class _75_최댓값과최솟값 {

    class Solution {
        public String solution(String s) {
            String[] split = s.split(" ");
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (String cur : split) {
                int curNum = Integer.parseInt(cur);
                min = Math.min(min, curNum);
                max = Math.max(max, curNum);
            }
            String answer = min + " " + max;
            return answer;
        }
    }

}
/*
문제 분석
1. 정보
    - 문자열 S에는 공백으로 구분된 숫자들이 저장되어 있음
    
2. 목표
    - S에 나타나는 숫자중 최솟값과 최댓값을 찾아 반환
3. 제약 조건
    - S에는 둘 이상의 정수가 공백으로 구분


풀이
1. 아이디어
    - S문자열을 split을 사용해 공백을 기준으로 나눔
    - 최솟값과 최댓값을 저장하기 위한 integer 선언
    - 나눠진 문자열들을 순회
        - 해당 문자열을 Integer로 바꾼 뒤
            - 최솟값보다 작다면 최솟값 업데이트
            - 최댓값보다 크다면 최댓값 업데이트
    - min + " " + max로 return
*/
