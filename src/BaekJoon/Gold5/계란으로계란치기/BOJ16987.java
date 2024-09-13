package BaekJoon.Gold5.계란으로계란치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987 {

    static int N;
    static int[][] egg;
    static int max = 0;
    //0 내구도 1 무게
    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        egg = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                egg[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        compute(0);
        System.out.println(max);
    }

    static void compute(int idx) {
        if (idx == N) {
            max = Math.max(max, getResult());
            return;
        }

        if (egg[idx][0] <= 0 || noEgg(idx)) {
            compute(idx + 1);
        }else{
            for (int i = 0; i < N; i++) {
                if (i != idx) {
                    if (egg[i][0] > 0) {
                        egg[idx][0] -= egg[i][1];
                        egg[i][0] -= egg[idx][1];
                        compute(idx + 1);
                        egg[idx][0] += egg[i][1];
                        egg[i][0] += egg[idx][1];
                    }
                }
            }
        }
    }

    static boolean noEgg(int idx) {
        for (int i = 0; i < N; i++) {
            if (i != idx) {
                if (egg[i][0] > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static int getResult() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (egg[i][0] <= 0) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BOJ16987.solution();
    }
}
