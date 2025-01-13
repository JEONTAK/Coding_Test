package Programmers.Codekata;

import java.util.Arrays;

public class _66_대충만든자판 {

    class Solution {
        public int[] solution(String[] keymap, String[] targets) {
            int[] answer = new int[targets.length];
            int[] click = new int[26];
            Arrays.fill(click, 101);
            for (int i = 0; i < keymap.length; i++) {
                for(int j = 0 ; j < keymap[i].length(); j++){
                    int idx = keymap[i].charAt(j) - 'A';
                    click[idx] = Math.min(click[idx], j + 1);
                }
            }

            for (int i = 0; i < targets.length; i++) {
                int sum = 0;
                for (int j = 0; j < targets[i].length(); j++) {
                    int idx = targets[i].charAt(j) - 'A';
                    if (click[idx] == 101) {
                        sum = -1;
                        break;
                    }else{
                        sum += click[idx];
                    }
                }
                answer[i] = sum;
            }

            return answer;
        }
    }
}

/*
문제 분석
1. 정보
    - 휴대폰 자판은 하나의 키게 여러 개의 문자가 할당 될 수 있음
    - 예로, 1번 키에 "A","B","C" 순서대로 할당되어 있다면, 3번 누르면 C가 되는 방식
    - 같은 규칙을 적용해 만든 휴대폰 자판이 있음
        - 같은 문자가 자판 전체에 여러 번 할당 된 경우도 있고,
        - 키 하나에 같은 문자가 여러번 할당 된 경우도 있음
        - 아예 할당되지 않은 경우도 있음
2. 목표
    - 해당 자판을 이용해 특정 문자열을 작성할 때, 키를 최소 몇 번 눌러야 그 문자열을 작성할 수 있는지 출력
    - 만약 문자열을 만들 수 없다면, -1을 출력
3. 제약 조건
    - 1 <= 자판의 길이 <= 100
        - 1 <= 특정 자판의 문자열 길이 <= 100
    - 각 자판의 문자열의 길이는 다를 수 있음
    - 각 원소는 알파벳 대문자로 이루어져 있음
    - 1 <= 목표 문자열 개수 <= 100
        - 1 <= 목표 문자열의 길이 <= 100

풀이
1. 아이디어
    - 해당 알파벳을 뽑는데 필요한 최소 누름 횟수를 저장하기 위한 배열을 선언
        - click[] = new int[26]으로 하고, 최대 100번 눌러야 뽑을 수 있는 경우의 수가 있기 때문에, 101로 초기화
    - keymap의 문자열을 다 순회
        - 해당 keymap의 문자를 하나씩 꺼내 click[] 과 idx + 1을 비교, 더 작은 값으로 업데이트.
    - 모든 keymap이 끝나면, target을 모두 순회
        - target의 문자열에서 문자를 하나씩 뽑음
            - 문자에 해당하는 click의 값이 101일 경우, -1 값 저장
            - 101이 아니면 sum에 해당 값을 더함
            - 모든 문자를 뽑은 후 answer배열에 sum 값 저장
*/
