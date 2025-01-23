package Programmers.Codekata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _109_연속된부분수열의합 {

    public class Solution {
        public int[] solution(int[] sequence, int k) {

            int start = 0;
            int end = 0;
            int sum = sequence[0];
            int n = sequence.length;

            List<SubArray> subList = new ArrayList<>();
            while (true) {
                if (sum == k) {
                    subList.add(new SubArray(start, end));
                }
                if (start == n && end == n) {
                    break;
                }
                if (sum <= k && end < n) {
                    end++;
                    if (end < n) {
                        sum += sequence[end];
                    }
                } else {
                    if (start < n) {
                        sum -= sequence[start];
                    }
                    start++;
                }
            }
            Collections.sort(subList);

            return new int[]{subList.get(0).start, subList.get(0).end};
        }

        private static class SubArray implements Comparable<SubArray> {
            int start;
            int end;
            int size;

            public SubArray(int start, int end) {
                this.start = start;
                this.end = end;
                this.size = end - start;
            }

            @Override
            public int compareTo(SubArray o) {
                if (this.size == o.size) {
                    return this.start - o.start;
                }
                return this.size - o.size;
            }
        }
    }
}

/*
문제 분석
1. 정보
    - 비내림차순으로 정렬된 수열이 주어질 때, 다음 조건을 만족하는 부분수열을 찾으려고 한다.
        - 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열이어야 한다.
        - 부분 수열의 합은 k
        - 합이 k인 부분 수열이 여러개인 경우 길이가 짧은 수열을 찾음
        - 길이가 짧은 수열이 여러개인 경우 앞쪽에 나오는 수열을 찾음
2. 목표
    - 수열을 나타내는 정수 배열 sequence와 부분 수열의 합을 나타내는 k가 매개변수로 주어질 때 위 조건을 만족하는 시작 인덱스와 마지막 인덱스를 배열에 담아 return

3. 제약 조건
    - 5 <= sequence의 길이 <= 1000000
        - 1 <= sequence 원소 <= 1000
    - 5 <= k <= 1_000_000_000

풀이
1. 아이디어
    - start = 0, end 0으로 초기화
    - sum을 선언하여 start ~ end 까지의 합을 저장
    - start, end, size를 가지고 있는 객체 생성 및 List<객체>로 sum == k 일 경우 해당 값을 저장
        - 만약 sum == k
            - list에 start, end, size로 저장
        - 만약 start == 길이 && end == 길이 이면
            - 종료
        - 만약 sum <= k && end < n
            - end++;
            - end < n 이면
                - sum += sequence[end]
        - 아니라면
            - 만약 start < n 이면
                - sum -= sequence[start]
                - start++
     - list 값을 구하였다면, 길이 오름차순, 시작 인덱스 오름차순으로 정렬
     - list.get(0) 값의 start, end를 return
*/
