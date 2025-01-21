package Programmers.Codekata;

public class _94_타겟넘버 {

    class Solution {

        int answer = 0;

        public int solution(int[] numbers, int target) {
            dfs(0, 0, numbers, target);
            return answer;
        }

        private void dfs(int idx, int total, int[] numbers, int target){
            if (idx == numbers.length) {
                if(total == target){
                    answer++;
                }
                return;
            }

            dfs(idx + 1, total + numbers[idx], numbers, target);
            dfs(idx + 1, total - numbers[idx], numbers, target);
        }
    }

}

/*
문제 분석
1. 정보
    - N개의 음이 아닌 정수가 존재
    - 해당 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만드려 함
2. 목표
    - 숫자를 적절히 빼고 더해서 타겟 넘버를 만드는 방법의 수를 return

3. 제약 조건
    - 2 <= 주어지는 숫자의 개수 <= 20
    - 1 <= 각 숫자 <= 50
    - 1 <= 타겟 넘버 <= 1000

풀이
1. 아이디어
     - DFS 알고리즘 사용
        - 가장 앞에있는 숫자부터 차례대로 계산
            - DFS(idx, total, numbers, target)
                - 만약 idx == numbers.length()이고, total == target -> answer++;
                - DFS(idx + 1, total + numbers[idx], numbers, target)
                - DFS(idx + 1, total - numbers[idx], numbers, target)
        - answer return
*/
