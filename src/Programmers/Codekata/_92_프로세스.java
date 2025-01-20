package Programmers.Codekata;

public class _92_프로세스 {

    public static void main(String[] args) {
        Solution.solution(new int[]{2, 1, 3, 2}, 2);
        Solution.solution(new int[]{1,1,9,1,1,1}, 0);
    }

    static class Solution {
        public static int solution(int[] priorities, int location) {
            int[] priority = new int[10];
            for (int i : priorities) {
                priority[i]++;
            }

            int answer = 0;
            int idx = 0;
            int cnt = 1;
            int p = 0;
            for (int i = 9; i > 1; i--) {
                if (priority[i] > 0) {
                    p = i;
                    break;
                }
            }
            while(true){
                if (priorities[idx] == p) {
                    if (location == idx) {
                        answer = cnt;
                        break;
                    }
                    priorities[idx] = -1;
                    priority[p]--;
                    idx = (idx + 1) % priorities.length;
                    cnt++;

                    if (priority[p] == 0) {
                        for (int i = p; i > 0; i--) {
                            if (priority[i] > 0) {
                                p = i;
                                break;
                            }
                        }
                    }
                }else{
                    idx = (idx + 1) % priorities.length;
                }
            }
            System.out.println(answer);
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - OS의 역할 중 하나는 컴퓨터 시스템의 자원을 효율적으로 관리하는 것.
    - 해당 문제에서는 다음 규칙에 따라 프로세스를 관리할 경우 특정 프로세스가 몇 번째로 실행 되는지 알아내는 것
        - 1. 실행 대기 큐에서 대기중인 프로세스를 하나 꺼냄
        - 2. 큐에 대기중인 프로세스 중 우선순위가 더 높은 프로세스가 있다면, 방금 꺼낸 프로세스를 다시 큐에 넣음
        - 3. 만약 그런 프로세스가 없다면, 방금 꺼낸 프로세스를 실행
            - 3.1 한 번 실행한 프로세스는 다시 큐에 넣지 않고 그대로 종료
2. 목표
    - 주어진 위치에 해당 하는 프로세스가 몇 번째로 실행 되는지 return
3. 제약 조건
     - 1 <= 프로세스의 중요도의 길이 <= 100
        - 1 <= 프로세스 중요도 <= 9
     - 0 <= 위치 < 프로세스 개수

풀이
1. 아이디어
    - 숫자가 높을 수록 우선순위가 높음
        - 따라서 우선순위가 9부터 차례대로 프로세스를 실행해 주어야 함.
        - 먼저 해당 우선순위를 가진 프로세스의 개수를 배열로 저장
        - 0번부터 차례대로 프로세스를 돌면서, 현재 우선순위를 가진 프로세스를 찾으면, 해당 프로세스의 우선순위 값을 -1로 설정 하고 idx를 +1 함, 또한 해당 우선순위의 개수를 -1 해줌.
        - 만약 해당 우선순위가 0이 되었으면, 우선순위를 -1해줌
        - 계속 돌아야 하므로, while문을 사용하여 계속돌 수 있도록 해줌.
        - 우리가 찾고자 하는 위치의 프로세스가 실행되었다면, idx값을 return
*/
