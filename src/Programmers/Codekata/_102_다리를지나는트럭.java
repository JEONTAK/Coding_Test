package Programmers.Codekata;

import java.util.LinkedList;
import java.util.Queue;

public class _102_다리를지나는트럭 {

    class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            Queue<Integer> queue = new LinkedList<>();
            int answer = 0;
            int wSum = 0;
            int inIdx = 0;
            int outIdx = 0;

            while (inIdx < truck_weights.length) {
                if (!queue.isEmpty() && answer - queue.peek() == bridge_length) {
                    wSum -= truck_weights[outIdx++];
                    queue.poll();
                }
                if (wSum + truck_weights[inIdx] <= weight) {
                    wSum += truck_weights[inIdx];
                    queue.add(answer);
                    inIdx++;
                }
                answer++;
            }

            while (!queue.isEmpty()) {
                if (answer - queue.peek() == bridge_length) {
                    queue.poll();
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
    - 트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 한다.
    - 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하 까지의 무게를 견딜 수 있다.
    - 단 다리에 완전히 오르지 않은 트럭의 무게는 무시한다
2. 목표
    - 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어질 때, 다리를 건너는데 필요한 최소 시간을 return
3. 제약 조건
    - 1 <= bridge_length <= 10000
    - 1 <= weight <= 10000
    - 1 <= truck_weights의 길이 <= 10000
        - 1 <= truck_weights <= weight

풀이
1. 아이디어
    - 큐를 사용해 다리 위에 올라가 있는 트럭들을 관리
        - 1초에 한 칸씩 가므로, 올라 갔을 때의 시간을 기준으로 다리를 다 건넜다면, 큐에서 제거한다.
        - 만약 다리 위에 올라가 있는 트럭들의 무게 합 + 올라갈 트럭의 무게가 다리가 버틸 수 있는 무게 w 보다 작거나 같다면, 트럭을 큐에 넣는다.
        - 무게 w보다 크다면, 해당 트럭은 기다린다.
    - 모든 트럭이 다리위에 올라갔다면
        - 큐가 빌때까지 시간을 ++
            - 1초에 한 칸씩 가므로, 올라 갔을 때의 시간을 기준으로 다리를 다 건넜다면, 큐에서 제거한다.
*/