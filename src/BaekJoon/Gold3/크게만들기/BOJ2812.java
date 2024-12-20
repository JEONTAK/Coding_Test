package BaekJoon.Gold3.크게만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2812 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Stack<Character> s = new Stack<>();
        char[] number = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            char cur = number[i];

            while (!s.isEmpty() && K > 0 && s.peek() < cur) {
                s.pop();
                K--;
            }

            s.push(cur);
        }

        while (K > 0) {
            s.pop();
            K--;
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : s) {
            sb.append(c);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ2812.solution();
    }
}
/*
문제 분석
1. 정보
    - N자리 숫자가 주어짐
    
2. 목표
    - 숫자 K개를 지워서 얻을 수 있는 가장 큰 수를 출력
3. 제약 조건
    - 1 <= K < N <= 500000

풀이
1. 알고리즘
    - 스택을 활용한 그리디 방식
        - 주어진 숫자를 왼쪽에서 오른쪽으로 탐색
            - 스택에 이미 들어있는 숫자가 현재 숫자보다 작고, 앞으로 숫자를 더 제거할 수 있다면 제거
            - 스택에 주어진 숫자 push
        - 모든 숫자에서 완료했다면
            - 만약 K가 남아있다면, K번 만큼 stack에서 제거
        - stack에 남아있는 숫자가 최종 결과 값

2. 탐색 과정
 - 스택을 활용한 그리디 방식
        - 주어진 숫자를 탐색하여 더 큰 수를 만들 수 있도록 스택에서 제거
 */