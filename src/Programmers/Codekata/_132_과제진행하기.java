package Programmers.Codekata;

import java.util.Arrays;
import java.util.Stack;

public class _132_과제진행하기 {

    class Solution {

        class Todo {
            String name;
            int time;
            int remain;

            public Todo(String name, int time, int remain) {
                this.name = name;
                this.time = time;
                this.remain = remain;
            }
        }

        Todo[] todoList;
        String[] answer;
        int idx = 0;

        public String[] solution(String[][] plans) {
            answer = new String[plans.length];
            todoList = new Todo[plans.length];

            for (int i = 0; i < plans.length; i++) {
                String name = plans[i][0];
                int time = getTime(plans[i][1]);
                int remain = Integer.parseInt(plans[i][2]);
                todoList[i] = new Todo(name, time, remain);
            }

            Arrays.sort(todoList, ((o1, o2) -> {
                return o1.time - o2.time;
            }));

            Stack<Todo> stack = new Stack<>();
            int curTime = -1;

            for (int i = 0; i < todoList.length; i++) {
                if (stack.isEmpty()) {
                    stack.push(todoList[i]);
                    continue;
                }

                Todo curTodo = stack.peek();
                Todo newTodo = todoList[i];

                curTime = curTodo.time;

                if (curTime + curTodo.remain <= newTodo.time) {
                    compute(stack, newTodo, curTime);
                }else{
                    curTodo.remain -= newTodo.time - curTime;
                }
                stack.push(newTodo);
            }

            while (!stack.isEmpty()) {
                answer[idx++] = stack.pop().name;
            }
            return answer;
        }

        private void compute(Stack<Todo> stack, Todo newTodo, int curTime) {
            if (stack.isEmpty()) {
                return;
            }
            Todo curTodo = stack.peek();
            if (curTime + curTodo.remain <= newTodo.time) {
                answer[idx++] = stack.pop().name;
                compute(stack, newTodo, curTime + curTodo.remain);
            }else{
                curTodo.remain -= newTodo.time - curTime;
            }
        }

        private int getTime(String s) {
            String[] split = s.split(":");
            return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }
    }

}

/*
문제 분석
1. 정보
    - 과제를 받은 루는 다음과 같은 순서대로 과제를 하려고 계획을 세움
        - 과제는 시작하기로 한 시각이 되면 시작
        - 새로운 과제를 시작할 시각이 되었을 때, 기존에 진행 중이던 과제가 있다면 진행 중이던 과제를 멈추고 새로운 과제를 시작
        - 진행중이던 과제를 끝냈을 때, 잠시 멈춘 과제가 있다면, 멈춰둔 과제를 이어 진행
         - 멈춰둔 과제가 여러 개일 경우, 가장 최근에 멈춘 과제부터 시작

2. 목표
    - 과제 계획을 담은 plans가 매개변수로 주어질 때, 과제를 끝낸 순서대로 이름을 배열에 담아 return

3. 제약 조건
    - 3 <= plans의 길이 <= 1000
        - 원소는 name, start, playtime 으로 이루어짐
        - 2 <= name의 길이 <= 10
        - start는 "hh:mm"의 형태로 00:00 ~ 23:59사이의 시간 값만 존재
        - playtime는 과제를 마치는데 걸리는 시간
            - 1 <= playtime <= 100

풀이
1. 아이디어
    - 먼저 들어온 plans를 객체로 변환
        - 과제 : name, 시간(분으로 변환), 과제 필수 시간
        - 해당 과제들을 시간에 대한 오름차순으로 정렬
    - 과제들을 처음부터 마지막까지 순회
        - 다음 과제의 시작 시간 가져옴
            - 만약 과제를 다 끝내지 못한다면
                - Deque에 해당 과제를 남은시간을 업데이트하여 저장
            - 만약 과제를 다 끝내고 시간이 있다면
                - 과제이름을 answer에 저장
                - 시간을 다 소모할때까지 Deque에서 과제를 꺼내 과제를 수행
                    - Deque에서 꺼낸 과제도 모두 수행했을때, 해당 과제의 이름을 answer에 저장
*/
