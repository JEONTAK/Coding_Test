package Programmers.Codekata;


public class _110_두큐합같게만들기 {

    class Solution {
        public int solution(int[] queue1, int[] queue2) {
            long sum1 = 0;
            long sum2 = 0;
            for (int i : queue1) {
                sum1 += i;
            }
            for (int i : queue2) {;
                sum2 += i;
            }

            long totalSum = sum1 + sum2;

            if (totalSum % 2 == 1) {
                return -1;
            }
            long targetSum = totalSum / 2;

            int[] mergeQueue = new int[queue1.length + queue2.length];
            int idx = 0;
            for (int i : queue1) {
                mergeQueue[idx++] = i;
            }
            for (int i : queue2) {
                mergeQueue[idx++] = i;
            }

            int left = 0;
            int right = queue1.length;
            long curSum = sum1;

            int maxOperations = queue1.length * 3;
            int answer = 0;

            while (answer <= maxOperations) {
                if (curSum == targetSum) {
                    return answer;
                }
                if (curSum < targetSum) {
                    curSum += mergeQueue[right % mergeQueue.length];
                    right++;
                }else{
                    curSum -= mergeQueue[left % mergeQueue.length];
                    left++;
                }
                answer++;
            }
            return -1;
        }
    }

}

/*
문제 분석
1. 정보
    - 길이가 같은 두 개의 큐가 주어짐
    - 하나의 큐를 골라 pop하고, pop한 원소를 다른 큐에 집어넣는 작업을 통해 각 큐의 원소 합이 같도록 만들려고 함
    - 이때 필요한 작업의 최소 횟수를 구하고자 한다.
        - 한번의 pop과 insert를 1회 수행한 것으로 간주

2. 목표
    - 필요한 작업의 최소 횟수 return
3. 제약 조건
    - 1 <= queue1의 길이 queue2의 길이 <= 300000
    - 1 <= queue1의 원소 , queue2의 원소 <= 10^9

풀이
1. 아이디어
    - 슬라이딩 윈도우 & 투 포인터 사용
    - 각 큐의 합을 각각 구함
        - 만약 두 큐의 합의 총합이 홀수라면 절대 같아질 수 없으므로 return -1
    - 두 배열을 하나의 배열로 합침
        - left = 0, right = 두번째 큐 시작점
    - 두 큐의 합 / 2 가 목표
    - 최대 이동 횟수는 3 * n -> 두 큐의 길이가 각 n이라면, 2 * n에 큐는 순환할 수 있으므로 n을 한번 더 해주어야 함.
        - 따라서 3 * n
    - 투 포인터를 사용하여 슬라이딩 윈도우 탐색 시작(0번부터 3*n까지)
        - 만약 합이 목표와 같다면, answer return
        - 만약 합이 목표보다 작다면,
            - 현재 합에 right 위치의 값 추가
            - right++
        - 목표보다 크다면
            - 현재 합에 left 위치의 값 뺌
            - left++;
        - answer++;
     - 탐색을 다 해도 발견하지 못했다면 return -1
*/
