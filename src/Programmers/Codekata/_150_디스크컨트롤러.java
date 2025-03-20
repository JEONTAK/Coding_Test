package Programmers.Codekata;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _150_디스크컨트롤러 {

    class Solution {

        class Work implements Comparable<Work> {
            int idx;
            int start;
            int time;

            public Work(int idx, int start, int time) {
                this.idx = idx;
                this.start = start;
                this.time = time;
            }

            @Override
            public int compareTo(Work o) {
                if (this.time == o.time) {
                    if (this.start == o.start) {
                        return this.idx - o.idx;
                    }
                    return this.start - o.start;
                }
                return this.time - o.time;
            }
        }

        public int solution(int[][] jobs) {
            Arrays.sort(jobs, (o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            });
            int[] finish = new int[jobs.length];
            PriorityQueue<Work> pq = new PriorityQueue<>();
            pq.add(new Work(0, jobs[0][0], jobs[0][1]));
            int time = jobs[0][0];
            int idx = 1;

            while (!pq.isEmpty()) {
                Work cur = pq.poll();
                time += cur.time;
                finish[cur.idx] = time;

                while (true) {
                    if (idx >= jobs.length) {
                        break;
                    }
                    if (jobs[idx][0] <= time) {
                        pq.add(new Work(idx, jobs[idx][0], jobs[idx][1]));
                        idx++;
                    } else {
                        break;
                    }
                }

                if (idx < jobs.length && pq.isEmpty()) {
                    pq.add(new Work(idx, jobs[idx][0], jobs[idx][1]));
                    time = jobs[idx][0];
                    idx++;
                }
            }
            int answer = 0;
            for (int i = 0; i < finish.length; i++) {
                answer += (finish[i] - jobs[i][0]);
            }
            return answer / finish.length;
        }
    }

}

/*
문제 분석
1. 정보
    - 하드디스크는 한 번에 하나의 작업만 수행할 수 있음.
    - 디스크 컨트롤러를 구현하는 방법은 여러 가지가 있음.
    - 이 문제에서는 우선순위 디스크 컨트롤러라는 가상의 장치를 이용한다고 가정
    - 우선순위 디스크 컨트롤러는 다음과 같이 동작
        - 어떤 작업 요청이 들어왔을 때 작업의 번호, 작업의 요청 시각, 작업의 소요 시간을 저장해 두는 대기 큐가 있음. 처음에 이 큐는 비어있음
        - 디스크 컨트롤러는 하드디스크가 작업을 하고 있지 않고 대기 큐가 비어있지 않다면 가장 우선 순위가 높은 작업을 대기 큐에서 꺼내서 하드디스크에 그 작업을 시킴.
        - 이때, 작업의 소요 시간이 짧은 것, 작업의 요청 시각이 빠른 것, 작업의 번호가 작은 것 순으로 우선순위가 높다.
        - 하드디스크는 작업을 한 번 시작하면 작업을 마칠 때까지 그 작업만 수행함.
        - 하드디스크가 어떤 작업을 마치는 시점과 다른 작업 요청이 들어오는 시점이 겹친다면, 하드 디스크가 작업을 마치자 마자 디스크 컨트롤러는 요청이 들어온 작업을 대기 큐에 저장한 뒤 우선 순위가 높은 작업을 대기 큐에서 꺼내서 하드디스크에 그 작업을 시킴.
2. 목표
    - 각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원 정수 배열 jobs가 매개변수로 주어질 때, 우선 순위 디스크 컨트롤러가 이 작업을 처리했을 때 모든 요청 작업의 반환 시간의 평균의 정수 부분을 return
3. 제약 조건
    - 1 <= jobs의 길이 <= 500
    - jobs[i]는 i번 작업에 대한 정보이고 [s,l] 형태임.
        - s는 작업이 요청되는 시점이며 0 <= s <= 1000
        - l은 작업의 소요시간이며 1 <= l <= 1000

풀이
1. 아이디어
    - 우선순위큐를 만들어 작업을 저장함
        - 해당 큐는 작업 소요시간 오름차순, 요청 시각 오름차순, 번호 오름차순 순으로 정렬
    - jobs를 시작 시간 오름차순으로 정렬
    - 끝난 시간을 저장하기위한 배열 finish 생성
    - 처음 jobs를 큐에 넣음
        - 해당 큐에서 값을 poll하여 끝나는 시간을 구함
        - 끝나는 시간을 finish에 저장
        - 끝나는 시간 전까지 시작시간이 포함되는 jobs들을 큐에 넣음
        - 큐 반복
        - 만약 큐가 비어있다면, 다음 번호의 jobs을 삽입함.
*/
