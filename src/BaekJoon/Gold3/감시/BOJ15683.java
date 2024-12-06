package BaekJoon.Gold3.감시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15683 {

    static class CCTV{
        int x;
        int y;
        int type;
        int d;

        public CCTV(int x, int y, int type, int d) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.d = d;
        }
    }

    static int N, M;
    static int[][] map;
    static ArrayList<CCTV> list = new ArrayList<>();
    static int result = Integer.MAX_VALUE;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] <= 5) {
                    list.add(new CCTV(i, j, map[i][j], 0));
                }
            }
        }

        bruteForce(0);
        System.out.println(result);
    }

    static void bruteForce(int idx) {
        if(idx == list.size()) {
            computeBlindSpot();
            return;
        }

        if (list.get(idx).type == 2) {
            for(int i = 0 ; i < 2 ; i++){
                list.get(idx).d = i;
                bruteForce(idx + 1);
            }
        }else if(list.get(idx).type == 5) {
            list.get(idx).d = 0;
            bruteForce(idx + 1);
        }else{
            for(int i = 0 ; i < 4 ; i++){
                list.get(idx).d = i;
                bruteForce(idx + 1);
            }
        }
    }

    static void computeBlindSpot(){
        int[][] copyMap = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                copyMap[i][j] = map[i][j];
            }
        }

        for (CCTV cctv : list) {
            int nx, ny, d;
            switch(cctv.type) {
                case 5:
                    d = (cctv.d + 3) % 4;
                    nx = cctv.x + dx[d];
                    ny = cctv.y + dy[d];
                    while (isAvailable(nx, ny) && copyMap[nx][ny] != 6) {
                        copyMap[nx][ny] = 7;
                        nx += dx[d];
                        ny += dy[d];
                    }
                case 4:
                    d = (cctv.d + 2) % 4;
                    nx = cctv.x + dx[d];
                    ny = cctv.y + dy[d];
                    while (isAvailable(nx, ny) && copyMap[nx][ny] != 6) {
                        copyMap[nx][ny] = 7;
                        nx += dx[d];
                        ny += dy[d];
                    }
                case 3:
                    d = (cctv.d + 1) % 4;
                    nx = cctv.x + dx[d];
                    ny = cctv.y + dy[d];
                    while (isAvailable(nx, ny) && copyMap[nx][ny] != 6) {
                        copyMap[nx][ny] = 7;
                        nx += dx[d];
                        ny += dy[d];
                    }
                case 1:
                    nx = cctv.x + dx[cctv.d];
                    ny = cctv.y + dy[cctv.d];
                    while (isAvailable(nx, ny) && copyMap[nx][ny] != 6) {
                        copyMap[nx][ny] = 7;
                        nx += dx[cctv.d];
                        ny += dy[cctv.d];
                    }
                    break;
                case 2:
                    nx = cctv.x + dx[cctv.d];
                    ny = cctv.y + dy[cctv.d];
                    while (isAvailable(nx, ny) && copyMap[nx][ny] != 6) {
                        copyMap[nx][ny] = 7;
                        nx += dx[cctv.d];
                        ny += dy[cctv.d];
                    }
                    d = (cctv.d + 2) % 4;
                    nx = cctv.x + dx[d];
                    ny = cctv.y + dy[d];
                    while (isAvailable(nx, ny) && copyMap[nx][ny] != 6) {
                        copyMap[nx][ny] = 7;
                        nx += dx[d];
                        ny += dy[d];
                    }
                    break;
            }
        }
        int sum = 0;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if (copyMap[i][j] == 0) {
                    sum++;
                }
            }
        }
        result = Math.min(result, sum);
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BOJ15683.solution();
    }
}

/*
문제 분석
1. 정보
    - N * M의 지도 (1 <= N, M <= 8)
        - 지도 정보
            - 0 : 빈 칸
            - 1 ~ 5 : CCTV 종류
            - 6 : 벽
    - 총 K개의 CCTV (1 <= K <= <= 8)
    - CCTV 정보
        - 1번 : 한쪽 방향만 감시
        - 2번 : 양쪽으로 된 두 방향 감시
        - 3번 : 직각으로 된 두 방향 감시
        - 4번 : 세 방향 감시
        - 5번 : 네 방향 감시
    - CCTV는 회전이 가능함
        - 항상 회전은 90도 방향으로 해야함.
    - CCTV는 CCTV를 통과하여 너머 까지 감시가 가능
2. 목표
    - 사무실 크기와 상태, CCTV 정보가 주어졌을 때, CCTV 방향을 적절히 정해, 사각 지대의 최소 크기를 구하는 것

3. 제약 조건
    - CCTV의 감시는 벽은 통과하지 못함
    - CCTV는 90도 방향으로 회전 가능

풀이
1. 브루트 포스 알고리즘
    - 모든 CCTV의 가능한 조건을 설정하여 사각지대의 최소 크기를 구해야 한다.

2. 탐색 과정
    1. 지도 생성
        - 지도를 입력 값에 맞게 설정한다.
    2. 브루트 포스 알고리즘 + 백트래킹을 활용
        - 두 알고리즘을 활용하여 모든 경우의 수에서 사각지대의 합을 구한다
    3. 최솟값 구하기
        - 사각지대의 합 중 최솟값을 저장해 구한다.

3. 종료 조건
    - 모든 경우의 수를 계산 한 후 최솟값을 출력하면 된다.

참고 사항
    - dx : 0 1 0 -1
    - dy : 1 0 -1 0

    - switch 문을 통해 5, 4, 3, 1번을 차례대로 수행할 수 있게함
        - 5번 : 5, 4, 3, 1번 다 수행하면 만들어짐
        - 4번 : 4, 3, 1번 수행하면 만들어짐
        - 3번 : 3, 1번 수행하면 만들어짐
        - 1번 : 1번 수행하면 만들어짐
        - 2번 : 2번은 따로 정해진 로직으로 수행하면 만들어짐
 */