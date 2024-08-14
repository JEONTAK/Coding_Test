package BaekJoon.Gold5.배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1092 {

    static int N, M;
    static ArrayList<Integer> crane = new ArrayList<>();
    static ArrayList<Integer> box = new ArrayList<>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crane.add(Integer.parseInt(st.nextToken()));
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());
        if(crane.get(0) < box.get(0)) {
            System.out.println(-1);
            return;
        }
        int result = 0;
        while (!box.isEmpty()) {
            int bIdx = 0 ,cIdx = 0;
            while (cIdx < N) {
                if (bIdx == box.size()) {
                    break;
                } else if (crane.get(cIdx) >= box.get(bIdx)) {
                    box.remove(bIdx);
                    cIdx++;
                }else{
                    bIdx++;
                }
            }
            result++;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ1092.solution();
    }
}
