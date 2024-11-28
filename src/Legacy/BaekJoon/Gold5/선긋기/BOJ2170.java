package BaekJoon.Gold5.선긋기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2170 {

    static class Line implements Comparable<Line> {
        long s;
        long e;

        public Line(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Line o) {
            if(this.s == o.s) return (int)(this.e - o.e);
            return (int)(this.s - o.s);
        }
    }

    static int N;
    static ArrayList<Line> line = new ArrayList<>();
    static ArrayList<Line> result = new ArrayList<>();
    static long ans = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            line.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(line);
        int idx = 0;
        result.add(line.get(idx));
        for (int i = 1; i < line.size(); i++) {
            if (result.get(idx).e >= line.get(i).s) {
                result.get(idx).e = Math.max(result.get(idx).e, line.get(i).e);
            }else{
                result.add(line.get(i));
                idx++;
            }
        }
        for (int i = 0; i < result.size(); i++) {
            ans += result.get(i).e - result.get(i).s;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        BOJ2170.solution();
    }
}
