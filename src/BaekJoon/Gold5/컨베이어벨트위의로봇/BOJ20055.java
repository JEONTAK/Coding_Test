package BaekJoon.Gold5.컨베이어벨트위의로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20055 {

    static int N, K;
    static int[] belt;
    static int zero = 0, up, down;
    static boolean[] robot;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new int[2 * N];
        robot = new boolean[2 * N];
        up = 0;
        down = N - 1;
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 2 * N ; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
        int idx = 0;
        while (zero < K) {
            idx++;
            compute();
        }
        System.out.println(idx);
    }

    static void compute() {
        up = (up + (2 * N) - 1) % (2 * N);
        down = (down + (2 * N) - 1) % (2 * N);
        zero = 0;
        int cur = down;
        for (int i = 0; i < N; i++) {
            if (robot[cur]) {
                if (cur == down) {
                    robot[cur] = false;
                    continue;
                }
                if (belt[(cur + 1) % (2 * N)] > 0 && !robot[(cur + 1) % (2 * N)]) {
                    belt[(cur + 1) % (2 * N)]--;
                    robot[cur] = false;
                    if((cur + 1) % (2 * N) != down){
                        robot[(cur + 1) % (2 * N)] = true;
                    }
                }
            }
            cur = (cur - 1 + 2 * N) % (2 * N);
        }
        if (belt[up] > 0) {
            robot[up] = true;
            belt[up]--;
        }
        for (int i = 0; i < 2 * N; i++) {
            if (belt[i] == 0) {
                zero++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ20055.solution();
    }
}