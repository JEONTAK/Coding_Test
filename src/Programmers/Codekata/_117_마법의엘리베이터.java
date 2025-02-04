package Programmers.Codekata;

public class _117_마법의엘리베이터 {

    class Solution {
        int answer = 100_000_001;
        public int solution(int storey) {
            compute(storey, 0);
            return answer;
        }

        private void compute(int storey, int cnt) {
            if (storey == 0) {
                answer = Math.min(answer, cnt);
                return;
            }

            int cur = storey % 10;
            compute(storey / 10, cnt + cur);
            if (storey > 1) {
                compute((storey + (10 - cur)) / 10, cnt + (10 - cur));
            }
        }
    }
}

/*
문제 분석
1. 정보
    - 아주 높은 탑이 존재. 탑이 너무 높아 마법의 엘리베이터를 만들음.
    - 해당 버튼은 특별함
        - -1, +1, -10, +10, -100, +100등과 같이 절댓값이 10^c 형태인 정수들이 적힌 버튼이 존재
        - 버튼을 누르면 현재 층 수에 버튼에 적혀있는 값을 더한 층으로 이동
        - 단 위치해 있는 층과 버튼의 값을 더한 결과가 0보다 작으면 엘리베이터는 움직이지 않음
        - 엘리베이터는 현재 민수가 있는 층에 있음
    - 엘리베이터를 움직이기 위해서 버튼 한 번당 마법의 돌 한 개를 사용하게 됨

2. 목표
    - 0층으로 가기 위해 필요한 마법의 돌의 최솟값을 return

3. 제약 조건
    - 1 <= 현재 민수가 위치한 층 <= 100_000_000

풀이
1. 아이디어
    - storey 를 1의 자리 부터 계산
    - DFS 사용
        - 1의 자리가 5를 기준으로 작다면
            - storey - 해당 수 and storey /= 10
            - DFS(storey, 이동 횟수 + 해당 수) 해줌
        - 1의 자리가 5를 기준으로 크다면
            - storey + 해당 수 and storey /= 10
            - DFS(storey, 이동 횟수 + 해당 수) 해줌
        - storey가 0이라면, 해당 이동 횟수가 가장 적은 이동 횟수
     - 해당 문제점
        - 만약 95와 같은 숫자라면, 90 보다 100 이 이득임.
        - 따라서 5를 기준으로 따로 계산하는것이 아닌,
        - 해당 숫자를 기준으로 뺐을때와 더했을때 모두를 계산하여 최솟값을 구해야함.
        - 추가 문제점
            - 1의자리까지 갔을 시, 숫자를 더해 10의자리를 만들고 다시 1의자리로 만드는 작업이 무한루프 발생
            - 따라서 현재 위치가 1보다 클때만 10의 자리로 올리는것을 허용
                - 한번 10의 자리로 올리면 다음 수는 1로 나오기 때문
*/