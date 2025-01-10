package BaekJoon.Silver2.종이의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1780 {

    static int N;
    static int[][] map;
    static int[] result = new int[3];

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        compute(0, 0, N);
        for (int i : result) {
            System.out.println(i);
        }
    }

    private static void compute(int x, int y, int l) {
        int[] num = new int[3];
        for(int i = x; i < x + l ; i++){
            for (int j = y; j < y + l; j++) {
                num[map[i][j] + 1]++;
            }
        }

        if (num[0] == l * l) {
            result[0]++;
        } else if (num[1] == l * l) {
            result[1]++;
        } else if (num[2] == l * l) {
            result[2]++;
        } else {
            for (int i : new int[]{x, x + l / 3, x + 2 * l / 3}) {
                for (int j : new int[]{y, y + l / 3, y + 2 * l / 3}) {
                    compute(i, j, l / 3);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1780.solution();
    }
}

/*
문제 분석

1. 정보
    - N * N 크기의 행렬로 표현되는 종이가 존재
    - 종이의 각 칸에는 -1, 0, 1 중 하나가 저장되어 있다.
    - 아래의 규칙을 따라 자르려고 한다.
        - 만약 종이가 모두 같은 수로 되어 있다면, 해당 종이를 그대로 사용한다
        - (1)이 아닌 경우에는 종이를 같은 크기의 종이 9개로 자르고, (1)번을 반복한다.
2. 목표
    - 위 과정을 반복했을때, -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수를 출력

3. 제약 조건
    - 1 <= N <= 3^7, N : 3^k 꼴
    - 배열 : -1 or 0 or 1

풀이

1. 아이디어
    - 재귀 및 분할정복
        - 해당 범위 내에 모든 숫자들이 같다면, 해당 숫자로 표현한다.
        - 만약 다른 숫자가 있다면, 해당 범위에서 9분할로 나누어 왼쪽 위, 중간 위, 오른쪽 위, 왼쪽 중간, 중간 중간, 오른쪽 중간, 왼쪽 아래, 중간 아래, 오른쪽 아래 차례대로 분할 정복
        - 모든 분할정복이 끝나고 계산 값을 출력한다.

    - 알고리즘
        - compute(i,j,l) : i = x좌표, j = y좌표, l = 해당 범위의 길이
        - 범위 탐색하여 모든숫자가 같다면 해당 숫자에 해당하는 배열에 + 1
        - 만약 다르다면
            - (i, j, l/3),
            - (i, j + l/3, l/3),
            - (i, j + l/3 * 2, l/3),
            - (i + l/3, j, l/3),
            - (i + l/3, j + l/3, l/3),
            - (i + l/3, j + l/3 * 2, l/3),
            - (i + l/3 * 2, j, l/3),
            - (i + l/3 * 2, j + l/3, l/3),
            - (i + l/3 * 2, j + l/3 * 2, l/3),
            로 나누어 분할정복


*/