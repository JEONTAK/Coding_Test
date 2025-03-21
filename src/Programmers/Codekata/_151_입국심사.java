package Programmers.Codekata;

public class _151_입국심사 {

    class Solution {

        long min = 1;
        long max = 0;

        public long solution(int n, int[] times) {
            for (int time : times) {
                max = Math.max(max, time);
            }
            return compute(min, max * n, n, times);
        }

        private long compute(long start, long end, int n, int[] times) {
            while(start <= end){
                long mid = (start + end) / 2;
                if (isBigger(mid, n, times)) {
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
            return start;
        }

        private boolean isBigger(long mid, int n, int[] times) {
            long sum = 0;
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];
                if(sum >= n) return true;
            }
            return false;
        }
    }

}

/*
문제 분석
1. 정보
    - n명이 입국심사를 위해 줄을 서서 기다리고 있음.
    - 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다름
    - 처음에 모든 심사대는 비어있음.
    - 한 심사대에서는 동시에 한 명만 심사 가능
    - 가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있음.
    - 하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을수도 있음
    - 모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶음.
    
2. 목표
    - 입국심사를 기다리는 사람 수 n, 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times가 매개변수로 주어질 때, 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return
    
3. 제약 조건
    - 입국심사를 기다리는 사람은 1명 이상 1_000_000_000명 이하
    - 각 심사관이 한 명을 심사하는 데 걸리는 시간은 1분 이상 1_000_000_000분 이하
    - 심사관은 1명 이상 100000명 이하

풀이
1. 아이디어
    - 이분 탐색에서의 가장 중요한 점은 정답에서 요구하는 값을 변수로 하여 이분 탐색을 진행하는 것.
    - 따라서 심사를 받는데 걸리는 총 시간을 변수로 설정하고 구현해야함.
        - 심사를 받는데 걸리는 총 시간을 mid라 한다.
        - mid 동안 몇 명을 입국 심사할 수 있을지 계산
            - 각 심사대에서 최대로 심사할 수 있는 사람의 수를 구하고 저장.
        - 최대로 심사할 수 있는 사람의 수가 n보다 크다면 end를 mid로 하고 이분 탐색 수행
        - n보다 작다면, start를 mid로 하여 이분탐색 수행
        - 이때, start = 1, end = n * 제일 오래걸리는 심사대시간으로 초기화
*/
