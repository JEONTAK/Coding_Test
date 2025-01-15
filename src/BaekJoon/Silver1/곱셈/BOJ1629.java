package BaekJoon.Silver1.곱셈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629 {

    static long C;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(compute(A, B));
    }

    static long compute(long a, long b) {
        if (b == 1) {
            return a % C;
        }

        long tmp = compute(a, b / 2);

        if (b % 2 == 1) {
            return (tmp*tmp % C) * a % C;
        }

        return tmp * tmp % C;
    }

    public static void main(String[] args) throws IOException {
        BOJ1629.solution();
    }
}

/*
문제 분석
1. 정보
    - 자연수 A를 B번 곱한 수를 알고 싶음.
    - 단, 수가 매우 커질 수 있으므로, C로 나눈 나머지를 사용
    
2. 목표
    - A를 B번 곱한 수를 C로 나눈 나머지를 출력

3. 제약 조건
    - 1 <= A,B,C <= 2,147,483,647

풀이
1. 아이디어
    - 지수법칙 사용
         - A^B = A^N * A^M과 같다. (B = N + M일 경우)
    - 나머지 값의 성질
         - (A * B) % C = (A % C * B % C) % C이다.
    - 위 두가지가 키다.

    - 위 내용을 이용해 분할 정복을 접목하여 풀면?
        - B -> B/2 B/2로 나누어 푼다
            - 이때 B가 홀수이면,
                - B/2 B/2 1로 나누어 풀고
            - B가 짝수이면
                - B/2 B/2로 나누어 푼다
            - B/2 값은 한번만 구하면 되기 때문에, B/2에서 구한 값을 tmp라 할 경우
                - B가 홀수일 경우 : tmp * tmp * A 가 된다.
                - B가 짝수일 경우 : tmp * tmp 가 된다.
                - 여기서 나머지 연산을 해주어야 한다.
                - 하지만 B가 홀수 일 경우 tmp * tmp * A % C를 하면 범위를 넘어간다.
                    - 따라서 (tmp * tmp % C) * A % C로 나누어 계산하면 범위를 넘어가지 않는다.
             - 수도코드 형식으로 작성해보면
                - 지수가 1이면 A % C 를 리턴
                - tmp = compute(A, B / 2) 값으로 구함
                - 만약 B 값이 홀수 일 경우
                    - (tmp * tmp % C) * A % C를 반환
                - 짝수일 경우
                    - tmp * tmp % C를 반환
*/