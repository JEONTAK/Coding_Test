package BaekJoon.Level3.텀프로젝트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466 {

    static int T, n;
    static int[] student, check;
    static int tC;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            student = new int[n + 1];
            check = new int[n + 1];
            tC = 0;
            st = new StringTokenizer(br.readLine());
            for(int i = 1 ; i <= n ; i++){
                student[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 1; i <= n ; i++){
                if (check[i] == 0) {
                    dfs(i);
                }
            }

            sb.append(n - tC).append("\n");
        }

        System.out.println(sb.toString());
    }

    static void dfs(int idx) {
        check[idx] = 1;
        int next = student[idx];

        if (check[next] == 0) {
            dfs(next);
        } else if (check[next] == 1) {
            int cur = next;
            do {
                tC++;
                cur = student[cur];
            } while (cur != next);
        }

        check[idx] = 2;
    }

    public static void main(String[] args) throws IOException {
        BOJ9466.solution();
    }
}

/*
문제 분석
    1. 정보
        - 프로젝트 팀을 구성할때, 학생들은 같이 하고 싶은 단 한명의 팀원을 선택
        - 팀의 구성 조건
            - 학생 s1이 s1을 선택한 경우(자기 자신 선택)
            - s1 -> s2 -> ... -> sr -> s1로 이어지는 사이클이 형성된 경우
        - 사이클에 포함되지 않은 학생들은 팀에 속하지 않음

    2. 목표
        - TC마다 팀에 속하지 못한 학생들의 수를 출력

    3. 제약 조건
        - T : TC의 수 (1 <= T <= 100)
        - n : 학생의 수 (2 <= n <= 100000)
        - 총 입력 크기 <= 10^7

풀이
    1. DFS 기반 사이클 탐색
        - 사이클 찾아 팀에 속한 학생들 구분
        - 방문 배열 상태로 학생 상태 관리
            - 0 : 방문 X
            - 1 : 방문 중
            - 2 : 방문 완료

    2. 탐색 과정
        1. 각 학생에 대해 DFS로 탐색 시작
        2. 탐색 중 사이클 발견되면 사이클에 포함된 학생들 카운트
            - do while loop 사용
                - 시작 값부터 하나씩 방문해 다시 시작값으로 돌아올때 까지 tC++함
        3. 방문 완료 처리 후, 방문하지 않은 학생들은 팀이 없는 것
 */
