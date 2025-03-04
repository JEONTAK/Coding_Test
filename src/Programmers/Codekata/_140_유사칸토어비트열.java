package Programmers.Codekata;

public class _140_유사칸토어비트열 {

    class Solution {

        int answer = 0;
        int N;

        public int solution(int n, long l, long r) {
            N = n;

            compute(0, l, r, 1L, 1);
            return answer;
        }

        private void compute(int n, long l, long r, long idx, int cur) {
            if (n == N) {
                if (l <= idx && idx <= r) {
                    if (cur == 1) {
                        answer++;
                    }
                }
                return;
            }

            long left = (long) Math.ceil(l / Math.pow(5, N - (n + 1)));
            long right = (long) Math.ceil(r / Math.pow(5, N - (n + 1)));

            for (long i = (idx - 1) * 5 + 1; i <= idx * 5; i++) {
                if (i < left || right < i) {
                    continue;
                }
                if (i != idx * 5 - 2) {
                    compute(n + 1, l, r, i, 1);
                }
            }
        }
    }

}

/*
문제 분석
1. 정보
    - 수학에서 칸토어 집합은 0과 1 사이의 실수로 이루어진 집합으로, [0,1]부터 시작하여 각 구간을 3등분 하여 가운데 구간을 반복적으로 제외하는 방식으로 만들어짐.
    - 남아는 칸토어 집합을 조금 변형하여 유사 칸토어 비트열을 만들음
    - 유사 칸토어 비트열은 다음과 같이 정의됨
        - 0번째 유사 칸토어 비트열은 "1"
        - n번쨰 유사 칸토어 비트열은 n - 1번째 유사 칸토어 비트열에서의 1을 11011로 치환하고 0을 00000으로 치환하여 만듬
    - 남아는 n번째 유사 칸토어 비트열에서 특정 구간 내의 1의 개수가 몇 개인지 궁금해짐

2. 목표
    - n과 1의 개수가 몇 개인지 알고 싶은 구간을 나타내는 l, r이 주어졌을 때 그 구간 내의 1의 개수를 return

3. 제약 조건
    - 1 ≤ n ≤ 20
    - 1 ≤ l, r ≤ 5n
        - l ≤ r < l + 10,000,000
        - l과 r은 비트열에서의 인덱스(1-base)이며 폐구간 [l, r]을 나타냄.
풀이
1. 아이디어
    - n번째 자리 도달할 때 까지 DFS 하기
    - 해당 자리의 다음 번째 범위
        - (i - 1) * 5 + 1 ~ i * 5
        - 위 범위중 1,2,4,5 에만 1이 해당되므로 해당 인덱스만 다시 DFS
        - 만약 해당 인덱스가 구하려는 범위 안에 있지 않으면, DFS 하지 않음
            - 범위는 l / 5^(남은 깊이), r / 5^(남은 깊이) 하고, 올림 하여 사용
        - 만약 해당 인덱스가 [l,r] 사이에 존재하지 않는다면
        - 더 이상 깊이 들어갈 필요가 없으므로 return
        - 존재한다면 깊이 + 1 로 더 들어감
        - 만약 n번째에 도달하였다면
            - 해당 숫자의 인덱스가 l과 r 사이에 있고 1이라면 1의 개수++
            - return
*/