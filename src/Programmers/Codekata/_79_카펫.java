package Programmers.Codekata;

public class _79_카펫 {

    class Solution {
        public int[] solution(int brown, int yellow) {
            int[] answer = new int[2];
            int sum = brown + yellow;
            for (int row = sum; row > 1; row--) {
                if (sum % row == 0) {
                    int col = sum / row;
                    int yellowSum = (row - 2) * (col - 2);
                    int brownSum = row * col - yellowSum;
                    if (brownSum == brown && yellowSum == yellow) {
                        answer[0] = row;
                        answer[1] = col;
                        break;
                    }
                }
            }
            return answer;
        }
    }

}
/*
문제 분석
1. 정보
    - 레오는 중앙에는 노란색으로 칠해져 있고, 테두리 1줄은 갈색으로 칠해진 격자 모양 카펫을 봤음
    - 집에와서 레오는 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억을 하지 못함
    
2. 목표
    - 노란색과 갈색의 격자의 개수가 주어졌을때, 카펫의 가로 세로 크기를 순서대로 배열에 담아 return
3. 제약 조건
    - 8 <= 갈색 격자의 수 <= 5000
    - 1 <= 노란색 격자의 수 <= 2,000,000
    - 카펫의 가로 길이 >= 세로 길이

풀이
1. 아이디어
    - 갈색 카펫과 노란색 카펫의 개수를 더한 값의 약수를 구함
        - 해당 악수는 반드시 가로,세로 높이 중 일부
    - 즉 더한 값부터 2가 나올때까지 -- 반복
        - 만약 해당 값이 약수라면 -> 가로로 설정
            - 세로 = 전체 개수 / 가로 값
            - 해당 값을 이용해 다음을 도출 가능
                - 해당 노란색 개수 -> (가로 - 2) * (세로 - 2)
                - 해당 갈색 개수 -> 가로 * 세로 - 노란색 개수
                - 일치한다면 값 return
*/