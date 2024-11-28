package BaekJoon.Gold4.N_Queen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9663 {

    static int N;
    static int result = 0;
    static int[] queen;


    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queen = new int[N];
        compute(0);
        System.out.println(result);
    }

    static void compute(int count) {
        if (count == N) {
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            queen[count] = i;

            if(canPutQueen(count)){
                compute(count + 1);
            }
        }
    }

    static boolean canPutQueen(int count) {
        for (int i = 0; i < count; i++) {
            if (queen[count] == queen[i]) {
                return false;
            }
            else if(Math.abs(count - i) == Math.abs(queen[count] - queen[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BOJ9663.solution();
    }
}
