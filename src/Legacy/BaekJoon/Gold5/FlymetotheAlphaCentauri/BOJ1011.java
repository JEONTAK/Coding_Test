package Legacy.BaekJoon.Gold5.FlymetotheAlphaCentauri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1011 {

    static int T;
    static long x, y;
    static StringBuilder sb = new StringBuilder();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Long.parseLong(st.nextToken());
            y = Long.parseLong(st.nextToken());
            compute();
        }
        System.out.println(sb.toString());
    }

    static void compute(){
        long diff = y - x;
        long max = (int) Math.sqrt(diff);
        if (max == Math.sqrt(diff)) {
            sb.append(2 * max - 1).append("\n");
        } else if (diff <= max * max + max) {
            sb.append(2 * max).append("\n");
        }else{
            sb.append(2 * max + 1).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1011.solution();
    }
}
