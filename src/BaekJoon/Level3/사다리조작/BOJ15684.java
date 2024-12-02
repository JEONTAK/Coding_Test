package BaekJoon.Level3.사다리조작;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {

    static int N, M, H;
    static boolean[][] ladder;
    static int result = Integer.MAX_VALUE;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new boolean[H + 1][N + 1];

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }

        for (int i = 0; i <= 3; i++) {
            if (dfs(0, i)) {
                break;
            }
        }
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    static boolean dfs(int depth, int cnt) {
        if (depth == cnt) {
            if (compute()) {
                result = Math.min(result, depth);
                return true;
            }
            return false;
        }

        for(int i = 1 ; i <= H ; i++){
            for(int j = 1 ; j < N ; j++){
                if (!ladder[i][j - 1] && !ladder[i][j] && !ladder[i][j + 1]) {
                    ladder[i][j] = true;
                    if(dfs(depth + 1, cnt))return true;
                    ladder[i][j] = false;
                }
            }
        }
        return false;
    }

    static boolean compute(){
        for(int i = 1 ; i <= N ; i++){
            int pos = i;
            for (int j = 1; j <= H; j++) {
                if(ladder[j][pos])pos++;
                else if(pos > 1 && ladder[j][pos-1])pos--;
            }
            if(pos != i)return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BOJ15684.solution();
    }
}

/*
문제분석
    1.정보
        - 세로선 N : 세로 줄 개수
        - 가로선 M : 이미 배치된 가로줄 개수
        - 가로선 위치 H : 세로선마다 가로선을 놓을 수 있는 위치의 개수
        - 가로선 정보
            - a, b : a번 점선에서 b번째 세로선과 b + 1번째 새로선을 연결함
        - 결과 : 각 세로선 i 의 시작점에서 끝까지 내려갔을때 결과가 같은 번호 i로 도달해야 함.

    2.목표
        - 가로선을 추가하여 모든 세로선 i의 결과가 i가 되도록 수정
        - 최소한의 가로선 개수 출력
            - 최소값 <= 3 : 해당 값 출력
            - 최소값 > 3 : -1 출력
            - 불가능한 경우 : -1 출력
    3.제약 조건
        1. 가로선 제한 조건
            - 가로선이 연속하거나 겹치면 안됨
            - 가로선은 b번과 b + 1번만 연결 가능
        2. 추가 가로선 제한
            - 추가 가로선 최대 3개
        3. 탐색 범위
            - N <= 10 , H < 30 이므로 성능 문제 X
풀이
    1. 브루트포스 + 백트래킹
        - 추가 가로선 최대 3개까지 모든 경우의 수 탐색
2. 핵심 구현
    1. 시뮬레이션
        - 배치된 가로선 따라 모든 세로선의 결과를 계산
        - 결과가 모두 i -> i이면 true
    2. 백트래킹
        - 가로선을 0 ~ 3개 추가하는 모든 경우 탐색
        - 각 가로선 추가하기 전에
            - 추가 가능한지 체크
        - 모든 경우 탐색하며 최소값 갱신
 */