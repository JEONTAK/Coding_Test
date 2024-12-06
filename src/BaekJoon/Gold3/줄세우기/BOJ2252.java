package BaekJoon.Gold3.줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2252 {

    static int N, M;
    static int[] degree;
    static ArrayList<Integer>[] list;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        degree = new int[N + 1];
        list = new ArrayList[N + 1];

        for(int i = 1 ; i <= N ; i++){
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list[A].add(B);
            degree[B]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if(degree[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur).append(" ");

            for (Integer next : list[cur]) {
                degree[next]--;
                if(degree[next] == 0){
                    q.add(next);
                }
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ2252.solution();
    }
}
/*
문제 분석
1. 정보
    - 학생 N명 (1 <= N <= 32000)
    - 비교 횟수 M개 (1 <= M <= 100000)
    - 비교 정보
        - A, B : 학생 A가 학생 B의 앞에 서야함
2. 목표
    - 일부 학생들의 키를 비교한 결과를 사용해 줄을 세워 결과를 출력

3. 제약 조건
    - A, B가 주어 졌을 경우, 반드시 A학생은 B학생 앞에 서야한다.
    - 학생 번호는 1번부터 N번까지 이다.
풀이
1. 위상 정렬
    - 위상 정렬을 사용해 B에 해당하는 학생에 degree를 1씩 늘려준다

2. 그래프 표현
    - 학생 간의 관계를 방향성 있는 그래프로 표현

3. degree 계산
    - 각 학생 노드의 degree를 계산
    - degree가 0인 노드를 큐에 넣음

4. 큐
    - 큐에서 노드를 하나씩 꺼내면서, 해당 노드와 연결된 다른 노드들의 degree를 감소 시킴
    - degree가 0이된 노드를 큐에 삽입
    - 큐가 빌때까지 반복
 */