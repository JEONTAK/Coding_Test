package Programmers.Codekata;

import java.util.ArrayList;
import java.util.List;

public class _77_이진변환반복하기 {

    class Solution {
        public int[] solution(String s) {
            int[] answer = new int[2];
            while (true) {
                if (s.equals("1")) {
                    break;
                }
                answer[0]++;
                int curLen = s.length();
                s = s.replaceAll("0", "");
                int changeLen = s.length();
                answer[1] += (curLen - changeLen);
                StringBuilder sb = new StringBuilder();
                while (true) {
                    sb.append(changeLen % 2);
                    changeLen /= 2;
                    if (changeLen == 0) {
                        break;
                    }
                }
                sb.reverse();
                s = sb.toString();
            }
            return answer;
        }
    }

}

/*
4 -> 2 , 0
2 -> 1 , 0
1 -> 0 , 1


 */

/*
문제 분석
1. 정보
    - 0과 1로 이루어진 문자열 x에 대한 이진 변환을 다음과 같이 정의
        - 1. x의 모든 0을 제거
        - 2. x의 길이를 c라고 하면, x를 "c를 2진법으로 표현한 문자열"로 바꿈
        - 예를 들어 x = "0111010" 일 경우
            - "1111" 로 바뀐뒤, 해당 길이가 4 이므로
            - "100"으로 바뀜
2. 목표
    - s가 1이 될 때까지 계속 이진 변환을 가했을 때, 이진 변환 과정에서 제거된 모든 0의 개수를 배열에 담아 return

3. 제약 조건
    - 1 <= s의 길이 <= 150000
    - s에는 1이 최소 하나 이상 포함되어 있음

풀이
1. 아이디어
    - 단순 구현
    - while문 통해 s가 1이 나올때까지 반복
        - 현재 길이와, replaceAll을 하고 난 이후의 길이를 각각 구함
        - list에 현재 길이 - 이후 길이 값을 더해줌 -> 0의 개수
        - 이후 길이가 위 정보의 c가 됨
        - c를 이진법으로 변환 후 s와 바꿔줌
*/