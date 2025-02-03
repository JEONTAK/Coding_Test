package Programmers.Codekata;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _115_호텔대실 {

    class Solution {

        public int solution(String[][] book_time) {
            int[][] times = new int[book_time.length][2];

            for (int i = 0; i < book_time.length; i++) {
                String[] split = book_time[i][0].split(":");
                int hour = Integer.parseInt(split[0]);
                int minute = Integer.parseInt(split[1]);
                times[i][0] = hour * 60 + minute;

                split = book_time[i][1].split(":");
                hour = Integer.parseInt(split[0]);
                minute = Integer.parseInt(split[1]);
                times[i][1] = hour * 60 + minute + 10;
            }

            Arrays.sort(times, (o1, o2) -> {
                return o1[0] - o2[0];
            });

            PriorityQueue<Integer> rooms = new PriorityQueue<>();

            for (int[] time : times) {
                int start = time[0];
                int end = time[1];

                if (!rooms.isEmpty() && rooms.peek() <= start) {
                    rooms.poll();
                }

                rooms.offer(end);
            }

            return rooms.size();
        }
    }

}

/*
문제 분석
1. 정보
    - 최소한의 객실만을 사용하여 예약 손님들을 받으려 한다.
    - 한 번 사용한 객실은 퇴실 시간을 기준으로 10분간 청소를 하고 다음 손님들이 사용할 수 있다.
2. 목표
    - 예약 시간이 담긴 book_time이 매개변수로 주어질 때, 필요한 최소 객실의 수를 반환
3. 제약 조건
    - 1 <= book_time의 길이 <= 1000

풀이
1. 아이디어
    - 우선순위 큐 사용
        - :를 기준으로, 시간 * 60 + 분 값을 적용해 정수 형태로 배열을 생성, 종료 시간은 + 10분 추가
        - 해당 배열을 시작 시간을 기준으로 오름차순 정렬
        - 종료 시간을 담는 우선순위 큐 생성
        - 모든 예약 시간을 순회
            - start : 시작, end : 종료 시간
            - 만약 방이 하나라도 있고, 기존 객실을 재사용 할 수 있다. -> !isEmpty() && peek() <= start 이면
                - poll()한 후 end 값 추가
        - 순회 이후, 우선순위 큐에 남아있는 크기 반환
*/
