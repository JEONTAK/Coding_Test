package Programmers.Codekata;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _143_이중우선순위큐 {

    class Solution {
        public int[] solution(String[] operations) {
            int[] answer = new int[2];
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

            for (String operation : operations) {
                String[] oper = operation.split(" ");
                if (oper[0].equals("I")) {
                    minHeap.add(Integer.parseInt(oper[1]));
                    maxHeap.add(Integer.parseInt(oper[1]));
                } else if (oper[0].equals("D")) {
                    if (minHeap.isEmpty() || maxHeap.isEmpty()) {
                        continue;
                    }

                    if (oper[1].equals("1")) {
                        int remove = maxHeap.poll();
                        minHeap.remove(remove);
                    }else{
                        int remove = minHeap.poll();
                        maxHeap.remove(remove);
                    }
                }
            }

            if (minHeap.isEmpty() || maxHeap.isEmpty()) {
                answer[0] = 0;
                answer[1] = 0;
            }else{
                answer[0] = maxHeap.poll();
                answer[1] = minHeap.poll();
            }
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말함.
        - I 숫자 : 큐에 주어진 숫자를 삽입
        - D 1 : 큐에서 최댓값을 삭제
        - D -1 : 큐에서 최솟값을 삭제

2. 목표
    - 이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return
3. 제약 조건
    - 1 <= operations의 길이 <= 1000000
    - operations의 원소는 큐가 수행할 연산을 나타냅니다.
        - 원소는 “명령어 데이터” 형식으로 주어집니다.- 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.
    - 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.

풀이
1. 아이디어
    - minHeap과 maxHeap 두가지를 Priority Queue로 생성
        - minHeap은 최솟값을 뽑을 수 있게(오름차순), maxHeap은 최댓값을 뽑을 수 있도록 설정(내림차순)
        - I 명령 들어올 경우
            - minHeap과 maxHeap에 해당 값을 넣음.
        - D 명령 들어올 경우
            - 두 큐가 다 비어있다면, 무시하고 다음 명령으로 넘어감.
        - D 1 명령 들어올 경우
            - maxHeap에서 poll을 하고, poll한 값을 minHeap에서도 제거
        - D -1 명령 들어올 경우
            - minHeap에서 poll을 하고, poll한 값을 maxHeap에서도 제거
    - 모든 명령 실행 이후
        - 큐가 비어있다면 [0,0] 반환
        - 비어있지 않다면, minHeap에서 뽑은 값과 maxHeap에서 뽑은 값을 반환
*/