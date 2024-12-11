package BaekJoon.Silver2.잃어버린괄호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1541 {

    static String[] part;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        part = str.split("-");

        int result = 0;
        result += groupSum(0);

        for (int i = 1; i < part.length; i++) {
            result -= groupSum(i);
        }

        System.out.println(result);
    }

    static int groupSum(int idx){
        int sum = 0;

        String[] group = part[idx].split("\\+");
        for (String num : group) {
            sum += Integer.parseInt(num);
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BOJ1541.solution();
    }
}

/*
문제 분석
1. 정보
    - 식
        - 0 ~ 9 , + , - 로 이루어져 있음.
        - 처음과 마지막 문자는 숫자.

2. 목표
    - 식의 값을 최소로 만들어 출력

3. 제약 조건
    - 연속해서 두개 이상의 연산자는 X
    - 5자리보다 많이 연속되는 숫자 X
    - 수는 0으로 시작할 수 있음
    - 식의 길이는 50보다 작거나 같음

풀이
1. 알고리즘
    - 그리디 알고리즘
        - 목표는 최솟값을 만들어야 함
            - - 기호를 기준으로 배열을 나눔
            - 각 배열에는 숫자와 + 기호가 존재
                - 배열에 들어있는 숫자를 모두 더하고 각각 빼주기
2. 탐색 과정
    - 첫번째 숫자를 결과값으로 설정
    - 그리디 알고리즘 사용해 최솟값을 구함
         - - 기호를 기준으로 배열을 나눔
            - 각 배열에는 숫자와 + 기호가 존재
                - 배열에 들어있는 숫자를 모두 더하고 각각 빼주기
 */