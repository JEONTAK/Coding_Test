package BaekJoon.Gold5.링크와스타트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15661 {

    static int N;
    static int[][] S;
    static boolean[] check;
    static int T, min = Integer.MAX_VALUE;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        check = new boolean[N];

        for (T = 1; T < N; T++) {
            compute(0, 0);
        }
        System.out.println(min);
    }

    static void compute(int tCnt, int idx) {
        if (tCnt == T) {
            min = Math.min(min, sum());
            if (min == 0) {
                System.out.println(min);
                System.exit(0);
            }
            return;
        }
        for (int i = idx; i < N; i++) {
            if (!check[i]) {
                check[i] = true;
                compute(tCnt + 1, i + 1);
                check[i] = false;
            }
        }
    }

    static int sum(){
        int startTeam = 0;
        int linkTeam = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if(check[i] && check[j]) {
                    startTeam += S[i][j] + S[j][i];
                } else if (!check[i] && !check[j]) {
                    linkTeam += S[i][j] + S[j][i];
                }
            }
        }
        return Math.abs(startTeam - linkTeam);
    }

    public static void main(String[] args) throws IOException {
        BOJ15661.solution();
    }
}
