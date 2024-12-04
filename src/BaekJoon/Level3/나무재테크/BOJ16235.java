package BaekJoon.Level3.나무재테크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ16235 {

    static int N, M, K;
    static int[][] A, map;
    static ArrayList<Integer>[][] tree;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        A = new int[N][N];
        tree = new ArrayList[N][N];
        for(int i = 0 ; i < N ; i++){
            for (int j = 0; j < N; j++) {
                tree[i][j] = new ArrayList<>();
                map[i][j] = 5;
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            tree[x - 1][y - 1].add(z);
        }

        for(int i = 0 ; i < K ; i++){
            springAndSummer();
            fall();
            winter();
        }
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += tree[i][j].size();
            }
        }
        System.out.println(result);
    }

    static void springAndSummer(){
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if (!tree[i][j].isEmpty()) {
                    ArrayList<Integer> alive = new ArrayList<>();
                    Collections.sort(tree[i][j]);
                    int dead = 0;
                    for (int age : tree[i][j]) {
                        if (map[i][j] >= age) {
                            map[i][j] -= age;
                            alive.add(age + 1);
                        }else{
                            dead += age / 2;
                        }
                    }
                    tree[i][j] = alive;
                    map[i][j] += dead;
                }
            }
        }
    }

    static void fall(){
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                for (Integer age : tree[i][j]) {
                    if (age % 5 == 0) {
                        for (int k = 0; k < 8; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (isAvailable(nx, ny)) {
                                tree[nx][ny].add(1);
                            }
                        }
                    }
                }
            }
        }
    }

    static void winter(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ16235.solution();
    }
}

/*
문제 분석
    1. 정보
        - Map 크기 : N * N (1 <= N <= 10)
        - 가장 처음에 양분은 모든 칸에 5만큼 들어있음
        - 나무의 개수 : M (1 <= M <= N^2)
        - 입력으로 주어지는 나무의 나이 (1 <= 나이 <= 10)
       
        - 사계절을 보냄(K년)(1 <= K <= 1000)
            - 봄
                - 나무가 자신의 나이만큼 양분을 먹고 나이가 1 증가
                - 만약 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹음
                - 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 즉시 죽음
            - 여름
                - 봄에 죽은 나무가 양분으로 변하게 된다
                - 각 죽은 나무의 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가됨(소수점 버림)
            - 가을
                - 나무가 번식한다
                    - 번식하는 나무는 나이가 5의 배수이고, 인접한 8칸에 나이가 1인 나무가 생긴다
                    - 땅을 벗어나는 칸에는 생기지 않는다.
            - 겨울
                - 땅을 돌아다니면서 양분을 추가한다.
                - 양분은 A[r][c]이고, 입력으로 주어진다.

    2. 목표
        - K년이 지난후 살아남은 나무의 수 출력

    3. 제약 조건
        - 하나의 칸에 여러개의 나무가 있을 경우 양분의 양에 따라 죽는 나무가 생김
        - 죽은 나무 / 2로 양분을 추가할 때 소수점 버림 발생
        - 번식하는 나무는 5의 배수

풀이
    1. 시뮬레이션
        - 봄
            - 어린 나무부터 양분을 먹고 나이 증가
            - 양분 부족한 나무는 죽음
        - 여름
            - 죽은 나무의 나이를 2로 나눈 값(소수점 버림)만큼 양분으로 변환
        - 가을
            - 나이가 5의 배수인 나무가 인접한 8칸에 나이 1인 나무를 번식
        - 겨울
            - 각 칸에 양분 A[r][c] 추가

    2. 자료구조
        - 양분 배열
            - 현재 각 칸의 양분 상태를 저장
        - 나무 배열
            - 각 칸에 심어진 나무를 PriorityQueue로 관리(나이 어린 나무 우선)
 */
