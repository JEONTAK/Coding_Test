package BaekJoon.Level3.ACMCraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1005 {

    static int T, N, K, W;
    static int[] dp, build, degree;
    static ArrayList<Integer>[] list;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            build = new int[N + 1];
            list = new ArrayList[N + 1];
            degree = new int[N + 1];
            dp = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1 ; i <= N ; i++){
                build[i] = Integer.parseInt(st.nextToken());
                list[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < K ; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list[s].add(e);
                degree[e]++;
            }

            W = Integer.parseInt(br.readLine());

            Queue<Integer> q = new LinkedList<>();
            for(int i = 1 ; i <= N ; i++){
                if (degree[i] == 0) {
                    q.add(i);
                    dp[i] = build[i];
                }
            }

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (Integer next : list[cur]) {
                    dp[next] = Math.max(dp[next], dp[cur] + build[next]);
                    degree[next]--;
                    if (degree[next] == 0) {
                        q.add(next);
                    }
                }
            }

            System.out.println(dp[W]);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1005.solution();
    }
}
/*
    문제 분석
        1. 데이터 정보
            - 건물 N개, 규칙 K개
            - 특정 건물이 지어진 뒤에만 건설 할 수 있는 조건
        1. 규칙
            - 위상 정렬 활용
                - 특정 건물이 지어진 뒤 건물 건설 가능함
            - 각 건물은 지어지는 시간이 있음
        3. 목표
            - 목표 건물 W를 짓기 위한 최소 시간 출력
                - 각 건물 건설하는데 걸리는 시간의 총합의 최소 시간 구하기
                - dp[W] : 목표 건물 W까지 걸리는 최소 시간 계산

    풀이
        1. 위상정렬 + DP
            - 위상정렬 : 건물의 선행 조건을 처리 하기 위함
            - DP : 특정 건물 x를 짓는데 걸리는 최소 시간 저장
        2. 탐색 과정
            1. 그래프 생성
                - 각 건물의 선행 조건을 그래프로 포현
                - 각 건물의 선행 조건의 수를 배열로 저장
            2. 위상정렬 실행
                - 선행조건 완료 -> 배열이 0이 된경우 -> 해당 점을 큐에 삽입
                - 큐에서 연결된 조건들 꺼내 해당 점의 선행 조건의 수 감소 시킴
                - 연결된 정점 j에 대해 dp[j] = max(dp[j], dp[i] + 건설 시간[j])로 갱신
            3. 결과 출력
                - dp[W] 출력
 */

