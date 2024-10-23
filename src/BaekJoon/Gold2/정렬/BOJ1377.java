package BaekJoon.Gold2.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BOJ1377 {

    static class A implements Comparable<A>{
        int value;
        int idx;

        public A(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        @Override
        public int compareTo(A o) {
            return this.value - o.value;
        }
    }

    static int N;
    static A[] list;
    static int result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new A[N + 1];
        for(int i = 1 ; i <= N ; i++){
            list[i] = new A(Integer.parseInt(br.readLine()), i);
        }
        Arrays.sort(list, 1, N + 1);
        int max = 0;
        for(int i = 1 ; i <= N ;i++){
            max = Math.max(max, list[i].idx - i);
        }
        System.out.println(max + 1);
    }

    public static void main(String[] args) throws IOException {
        BOJ1377.solution();
    }
}

//실제 버블소트 알고리즘을 사용한다면 O(N^2)이기 때문에 시간초과가 발생할수밖에 없다.
//따라서 버블소트의 규칙성을 활용하여 문제를 풀어야 한다.
//먼저 Idx와 해당 숫자를 배열로 저장한다.
//숫자를 기준으로 오름차순 정렬한다.
//해당 숫자의 원래 인덱스와 정렬한 현재 인덱스의 차이를 구한다.
//이 차이는 해당 인덱스가 얼마나 앞으로 움직였는지 알수 있는 척도가 된다.
//이 차이가 크다면 그만큼 해당 값은 앞으로 많이 움직였다는 뜻이다.
//그 뜻은, 차이가 가장 큰 idx가 해당 수만큼 이동했을때 버블소트가 끝났다는 말이 된다.
//따라서 해당 차이가 가장 큰 idx가 정답이 된다.
