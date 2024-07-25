package BaekJoon.Gold5.AC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ5430 {

    static int T, N;
    static String p;
    static boolean forward, flag;
    static ArrayDeque<Integer> dq;


    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int a = 0; a < T; a++) {
            forward = true;
            flag = true;
            dq = new ArrayDeque<>();
            p = br.readLine();
            N = Integer.parseInt(br.readLine());
            String temp = br.readLine();
            temp = temp.substring(1, temp.length() - 1);
            String[] t = temp.split(",");
            for (int i = 0; i < N; i++) {
                dq.push(Integer.parseInt(t[i]));
            }
            for (int i = 0; i < p.length(); i++) {
                char c = p.charAt(i);
                if (c == 'R') {
                    forward = !forward;
                }else{
                    if (!dq.isEmpty()) {
                        if (forward) {
                            dq.removeLast();
                        }else{
                            dq.removeFirst();
                        }
                    }else{
                        sb.append("error\n");
                        dq.clear();
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                sb.append("[");
                if (!dq.isEmpty()) {
                    if (forward) {
                        sb.append(dq.pollLast());
                        while (!dq.isEmpty()) {
                            sb.append(",").append(dq.pollLast());
                        }
                    }else{
                        sb.append(dq.pollFirst());
                        while (!dq.isEmpty()) {
                            sb.append(",").append(dq.pollFirst());
                        }
                    }
                }
                sb.append("]\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ5430.solution();
    }
}
