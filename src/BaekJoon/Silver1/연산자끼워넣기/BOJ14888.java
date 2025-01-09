package BaekJoon.Silver1.연산자끼워넣기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888 {

    static int N;
    static int[] A, oper;
    static int max = -1_000_000_001, min = 1_000_000_001;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        oper = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }

        compute(1, A[0]);
        System.out.println(max + "\n" + min);
    }

    static void compute(int idx, int result){
        if(idx == N){
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        if (oper[0] > 0) {
            oper[0]--;
            int tmp = result + A[idx];
            compute(idx + 1, tmp);
            oper[0]++;
        }

        if (oper[1] > 0) {
            oper[1]--;
            int tmp = result - A[idx];
            compute(idx + 1, tmp);
            oper[1]++;
        }

        if (oper[2] > 0) {
            oper[2]--;
            int tmp = result * A[idx];
            compute(idx + 1, tmp);
            oper[2]++;
        }

        if (oper[3] > 0) {
            oper[3]--;
            int tmp;
            if (result < 0) {
                tmp = -1 *((result * -1) / A[idx]);
            }else{
                tmp = result / A[idx];
            }
            compute(idx + 1, tmp);
            oper[3]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ14888.solution();
    }
}

/*
문제 분석

1. 정보
    - N개의 수로 이루어진 수열 A_1, A-2, ..., A_N이 주어짐
    - 수와 수 사이에 끼워 넣을 수 있는 N - 1개의 연산자가 주어짐
    - 연산자는 덧셈 뺄셈 곱셈 나눗셈으로 이루어짐
    - 연산자 우선 순위를 무시하고 앞에서부터 계산
    - 나눗셈은 정수 몫만 취함
    - 음수를 양수로 나눌때는 양수로 바꾼뒤 몫을 구하고, 다시 음수로 변환
    

2. 목표
    - 위 내용을 바탕으로 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하여 출력
    - 첫째 줄에는 최댓값, 둘째 줄에는 최솟값을 출력

3. 제약 조건
    - 2 <= N <= 11
    - 1 <= A_i <= 100
    - -10^9 <= 결과 <= 10^9
풀이
1. 아이디어
    - 백트래킹을 활용
        - 브루트 포스를 통해 모든 연산 결과의 경우의 수를 구함
        - compute(i,result) : result 에 i번째 수를 연산할 경우를 말함
            - 만약 i가 N이라면 모든 연산을 수행 -> 최댓값 및 최솟값에 비교후 저장
            - 아니라면 남은 연산자들을 사용해 result 값을 업데이트 후 compute(i + 1, result)를 수행
                - 덧셈 연산이 남아 있다면 덧셈 후 compute
                - 뺄셈 연산이 남아 있다면 뻴셈 후 compute
                - 곱셈 연산이 남아 있다면 곱셈 후 compute
                - 나눗셈 연산이 남아 있다면
                    - 현재 결과 값이 음수일 경우
                        - 양수로 바꾼뒤 몫을 구한후 음수로 compute

2. 주의할 점
    - 최댓값 초기화시 0으로 초기화 하면 틀린다.
        - 만약 결과값의 최댓값이 음수가 나온다면, 정답은 음수지만 출력은 0으로 되기 때문!
*/
