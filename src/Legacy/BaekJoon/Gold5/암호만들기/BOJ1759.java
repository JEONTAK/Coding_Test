package BaekJoon.Gold5.암호만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759 {

    static int L, C;
    static char[] seq, result;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        seq = new char[C];
        result = new char[L];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            seq[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(seq);
        compute(0, 0);
    }

    static void compute(int loc, int idx) {
        if (idx == L) {
            if (isAvailable()) {
                System.out.println(result);
            }
            return;
        }
        for (int i = loc; i < C; i++) {
            result[idx] = seq[i];
            compute(i + 1, idx + 1);
        }
    }

    static boolean isAvailable(){
        int v = 0, c = 0;
        for (char ch : result) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                v++;
            }else{
                c++;
            }
        }
        if (v >= 1 && c >= 2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BOJ1759.solution();
    }
}
