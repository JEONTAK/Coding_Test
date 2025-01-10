package BaekJoon.Silver1.쿼드트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {

    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            String tmp = br.readLine();
            for(int j = 0 ; j < N ; j++){
                map[i][j] = tmp.charAt(j) - '0';
            }
        }

        compute(0,0,N);
        System.out.println(sb.toString());

    }

    private static void compute(int x, int y, int l) {
        int sum = 0;
        for(int i = x ; i < x + l ; i++){
            for(int j = y ; j < y + l ; j++){
                sum += map[i][j];
            }
        }
        if (sum == (l * l)) {
            sb.append("1");
        }else if(sum == 0){
            sb.append("0");
        }else{
            sb.append("(");
            compute(x, y, l / 2);
            compute(x, y + l / 2, l / 2);
            compute(x + l / 2, y, l / 2);
            compute(x + l / 2, y + l / 2, l / 2);
            sb.append(")");
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1992.solution();
    }
}

/*
문제 분석

1. 정보
    - 쿼드 트리란
        - 0과 1로 이루어진 2차원 배열에서, 같은 숫자의 점이 한 곳에 많이 몰려 있으면 압축하는 방법
        - 주어진 배열이 모두 0이면 결과는 0, 모두 1이면 압축결과는 1
        - 만약 0과 1이 섞여 있으면, 4분할로 나누어 압축을 하며 압축한 결과를 차례대로 괄호 안에 묶어서 표현
        
2. 목표
    -  영상을 압축한 결과를 출력

3. 제약 조건
    - 영상의 크기를 나타내는 N : 1 <= N <= 64, N은 항상 2의 제곱수
    - 배열 정보 : 0 or 1

풀이
1. 아이디어
    - 재귀 및 분할정복
        - 해당 범위 내에 모든 숫자들이 같다면, 해당 숫자로 표현한다.
        - 만약 다른 숫자가 있다면, 해당 범위에서 4분할로 나누어 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 순차적으로 분할 정복한다.
        - 모든 분할정복이 끝나고 계산 값을 출력한다.
        
    - 알고리즘
        - compute(i,j,l) : i = x좌표, j = y좌표, l = 해당 범위의 길이
        - 범위 탐색하여 모든숫자가 같다면 해당 숫자를 StringBuilder에 저장하고 return
        - 만약 다르다면
            - (i,j,l/2), (i, j + l/2, l/2), (i + l/2, j, l/2), (i + l/2, j + l/2, l/2) 로 분할 정복
*/
