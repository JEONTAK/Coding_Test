package Programmers.Codekata;

import java.util.HashSet;
import java.util.Set;

public class _85_연속부분수열합의개수 {

    class Solution {
        public int solution(int[] elements) {
            int[] sum = new int[elements.length * 2 + 1];
            sum[0] = 0;
            for (int i = 1; i <= elements.length * 2; i++) {
                sum[i] = sum[i - 1] + elements[(i - 1) % elements.length];
            }
            Set<Integer> set = new HashSet<>();
            for (int element : elements) {
                set.add(element);
            }

            for (int len = 2; len <= elements.length; len++) {
                for (int i = 1; i <= elements.length; i++) {
                    int j = i + len - 1;
                    int tmp = sum[j] - sum[i - 1];
                    set.add(tmp);
                }
            }

            return set.size();
        }
    }

}

/*
문제 분석
1. 정보
    - 자연수로 이루어진 원형 수열의 연속하는 부분 수열의 합으로 만들 수 있는 수가 몇가지인지 궁금함
    - 원형 수열이란 처음과 끝이 연결된 형태의 수열을 말함
        - 따라서, 연속하는 부분 수열도 일반적인 수열보다 많아짐
2. 목표
    - 수열이 주어질 때, 원형 수열의 연속 부분 수열 합으로 만들 수 있는 수의 개수를 return
3. 제약 조건
    - 3 <= 수열의 길이 <= 1000
    - 1 <= 수열의 원소 <= 1000

풀이
1. 아이디어
    - Set을 사용하여 합의 중복 제거
    - 길이가 1인 수열부터 길이가 elements.length()인 부분 수열까지 모든 수열의 합을 구함
    - 원형 수열이므로, elements를 두배로 잡아 부분 합을 계산
    - 이때, 부분 합을 사용하여 계산
        - 첫번째 원소 부터 현재 원소까지의 부분합을 각 인덱스에 저장
        - 해당 부분합을 사용한다면 예를들어, elements 길이가 3일 경우
            - 길이가 2인 부분수열의 합을 찾는 방법
                - 시작 : 0, 끝 : 1
                    - 부분합[1]
                - 시작 : 1, 끝 : 2
                    - 부분합[2] - 부분합[0]
                - 시작 : 2, 끝 : 3
                    - 부분합[3] - 부분합[1]
                - 시작 : 3, 끝 : 4
                    - 부분합[4] - 부분합[2] ...
    - 해당 값들을 set에 저장한 후 모두 끝나면 set의 크기를 return
*/