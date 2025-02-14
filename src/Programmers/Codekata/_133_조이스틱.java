package Programmers.Codekata;

public class _133_조이스틱 {

    class Solution {
        public int solution(String name) {
            int[] min = new int[name.length()];
            int answer = 0;
            int length = name.length();
            int move = length - 1;
            for (int i = 0; i < name.length(); i++) {
                int cur = name.charAt(i) - 'A';
                min[i] = Math.min(cur, 26 - cur);
                answer += min[i];
                
                int next = i + 1;
                while (next < length && name.charAt(next) == 'A') {
                    next++;
                }
                move = Math.min(move, i + length - next + Math.min(i, length - next));
            }

            answer += move;
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 조이스틱으로 알파벳 이름을 완성하여라.
    - 맨 처음엔 A로만 이루어져 있다.
    - 조이스틱을 각 방향으로 움직이면 아래와 같음
        - 위 : 다음 알파벳
        - 아래 : 이전 알파벳
        - 왼쪽 : 커서를 왼쪽으로 이동
        - 오른쪽 : 커서를 오른쪽으로 이동
2. 목표
    - 만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만들음
3. 제약 조건
    - name은 알파벳 대문자로만 이루어짐
    - name의 길이는 1 이상 20 이하.

풀이
1. 아이디어
    - 일단 각 자리의 알파벳들이 A로부터 만들 수 있는 알파벳의 최소 움직임에 대한 값을 저장
    - 해당 자리의 움직임 최소 횟수를 결과에 더해줌
        - 먼저 오른쪽으로 이동할 경우의 이동거리를 계산
            - 오른쪽으로 이동했을 때 'A'라면 그 다음으로 넘어감
        - 알파벳을 바꿔야 하는 자리에 도착했을 때
            - 이동 거리를 최솟값으로 업데이트
                - 기존 이동거리, 왼쪽으로 이동하는 이동거리, 오른쪽으로 이동하는 이동거리 중 최솟값
            - 이동 거리를 결과에 더해줌
    - 모든 자리를 구하였다면 결과값 return
*/
