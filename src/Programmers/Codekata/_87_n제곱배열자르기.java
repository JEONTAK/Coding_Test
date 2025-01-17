package Programmers.Codekata;

public class _87_n제곱배열자르기 {

    class Solution {
        public int[] solution(int n, long left, long right) {
            int[] answer = new int[(int) (right - left + 1)];
            long lx = left / n;
            long ly = left % n;

            for (int i = 0; i < answer.length; i++) {
                answer[i] = (int) (Math.max(lx, ly) + 1);
                ly++;
                if (ly == n) {
                    ly = 0;
                    lx++;
                }
            }
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 정수 n, left, right가 주어짐
    - 다음 과정을 거쳐 1차원 배열을 만들고자 함
        - 1. n행 n열 크기의 비어있는 2차원 배열을 제작
        - 2. 1행 1열 부터 i행 i열까지의 영역 내 모든 빈 칸을 숫자 i로 채움
        - 3. 1행 2행 n행을 잘라내어 모두 이어붙인 새로운 1차원 배열을 제작
        - 4. 새로운 1차원 배열을 arr이라 할때, arr[left], arr[left + 1], ... arr[right] 만 남기고 나머지 삭제

2. 목표
    - 만들어진 1차원 배열을 return

3. 제약 조건
    - 1 <= n <= 10^7
    - 0 <= left <= right <= n^2
    - right - left < 10^5

풀이
1. 아이디어
    - 일단 단순 구현으로는 풀 수 없을 것이라 생각함
        - n 이 최대 10^7이기 때문에, 반드시 메모리 초과 발생할 것이라 생각했음
    - 그렇다면 left, right의 숫자를 보고 어디서부터 어디까지인지 파악
        - left의 좌표 : (left / n, left % n)
        - n * n 배열에서 left의 좌표를 알았음
        - right - left 는 최대 10만이므로, for문을 사용해도 괜찮을 듯
        - (i,j)의 값은 i와 j 값중 더 큰 값 + 1
        - 즉 for문을 통해 i와 j를 업데이트 하면서 해당 값에 맞는 값을 배열에 저장 후 return 해줌
*/
