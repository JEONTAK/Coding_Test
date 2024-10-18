package BaekJoon.Gold2.피보나치수3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2749 {

    static final int pisano = 1500000;
    static long N;
    static long[] num;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine()) % pisano;
        num = new long[pisano + 1];
        num[0] = 0;
        num[1] = 1;
        for (int i = 2; i <= N; i++) {
            num[i] = (num[i - 1] + num[i - 2]) % 1000000;
        }
        System.out.println(num[(int) N]);
    }

    public static void main(String[] args) throws IOException {
        BOJ2749.solution();
    }
}

//해당 문제를 쉽게 해결하기 위해서는 피사노 주기를 알고 있어야 한다.
//1000000을 나눈 나머지를 가져간다는 가정하에서는 1500000번을 주기로 똑같은 값이 주어진다.
//따라서 N값을 받은후 1000000으로 나눠주고, 해당값을 크기로 가진 dp배열을 만들어 그대로 피보나치알고리즘을 수행해주면 된다.
