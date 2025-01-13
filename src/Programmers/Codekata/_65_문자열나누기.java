package Programmers.Codekata;

public class _65_문자열나누기 {

    class Solution {
        public int solution(String s) {
            int answer = 0;
            int idx = 0;
            int[] sum;
            char first;

            while (idx < s.length()) {
                sum = new int[2];
                first = s.charAt(idx++);
                sum[0]++;
                while (idx < s.length()) {
                    if (s.charAt(idx++) == first) {
                        sum[0]++;
                    } else {
                        sum[1]++;
                    }
                    if (sum[0] == sum[1]) {
                        break;
                    }
                }
                answer++;
            }
            return answer;
        }
    }
}

/*
문제 분석
1. 정보
    - 문자열 S가 입력됨
    - 첫글자를 X라 지정
    - 문자열을 왼쪽에서 오른쪽으로 읽으면서, X와 X가 아닌 다른 글자들이 나온 횟수를 각각 셈
    - 처음으로 두 횟수가 같아지는 순간 멈추고, 해당 위치까지의 문자열을 분리
    - S에서 분리한 문자열을 빼고 남은 부분에 대해서 해당 과정 반복
    - 만약 두 횟수가 다른 상태에서 더 이상 읽을 글자가 없다면 읽은 문자열 분리 후 종료
2. 목표
     - S가 주어질 때, 분리한 문자열의 개수 리턴
     
3. 제약 조건
    - 1 <= S의 길이 <= 10000
    - S는 영어 소문자로 이루어져 있음

풀이
1. 아이디어
    - S의길이가 10000 이므로 while문 사용해도 충분
    - 첫 글자를 char 형태로 받음
    - 해당 글자의 개수 ++
    - 이후 조건에 맞을때 까지 다음 글자를 비교하여 개수에 더함
    - 두 개수가 같다면 해당 인덱스 while문 빠져나오고, answer++
    - 여기서 두 while의 ()에 들어갈 조건은 idx = 0으로 설정이후, idx < s.length()일때까지.
*/
