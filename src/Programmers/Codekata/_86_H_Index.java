package Programmers.Codekata;

public class _86_H_Index {

    class Solution {
        public int solution(int[] citations) {
            int[] check = new int[10001];
            for (int cur : citations) {
                check[cur]++;
            }
            for (int i = 10000; i > 0; i--) {
                check[i - 1] += check[i];
            }
            int answer = 0;
            for (int i = 10000; i >= 0; i--) {
                if (i <= check[i]) {
                    answer = i;
                    break;
                }
            }
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - H-Index는 과학자의 생산성과 영향력을 나타내는 지표
    - 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 함
        - 발표한 논문 n편 중 h번 이상 인용된 논문이 h편 이상이고, 나머지 논문이 h번 이하 인용되었다면 h의 최댓갑이 과학자의 H-Index

2. 목표
    - 주어진 배열을 활용해 과학자의 H-Index를 return
3. 제약 조건
    - 1 <= 논문의 수 <= 1000
    - 0 <= 논문별 인용 횟수 <= 10000

풀이
1. 아이디어
    - 최대 인용횟수가 10000이므로
        - 길이가 10000인 배열을 만들어 i번 인용된 논문의 수를 저장한다
        - 값이 저장되면, 10000부터 0까지 아래로 인용된 논문의수를 더해 누적합을 구한다
            - 누적합을 구하는 이유는,
            - 예를 들어, 4번이상 인용된 논문은 4 ~ 10000까지의 인용된 논문의 합이기 때문이다.
            - 따라서 i번째 값은 i ~ 10000까지의 누적된 합이 들어와야 한다.
        - 누적합을 구하였다면, 우리는 최댓값을 구해야 하기 때문에, 10000부터 0까지 순회한다.
            - 이때, 인용된 논문의 수와 i 값이 같다면, 즉 h번 이상 인용된 논문이 h편 이상이라면,
            - 해당 i값을 return 해주면 된다.

*/