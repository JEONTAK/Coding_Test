package BaekJoon.Gold5.센서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2212 {

    static class Diff implements Comparable<Diff>{
        int s;
        int e;
        int d;

        public Diff(int s, int e, int d) {
            this.s = s;
            this.e = e;
            this.d = d;
        }

        @Override
        public int compareTo(Diff o) {
            return o.d - this.d;
        }
    }

    static int N, K;
    static int[] sensor;
    static ArrayList<Diff> list = new ArrayList<>();
    static ArrayList<Integer> result = new ArrayList<>();
    static int min = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        if (K >= N) {
            System.out.println(0);
            return;
        }
        sensor = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensor);
        result.add(sensor[0]);
        result.add(sensor[sensor.length - 1]);
        sensor = Arrays.stream(sensor).distinct().toArray();
        for(int i = 1 ; i < sensor.length ; i++) {
            list.add(new Diff(sensor[i - 1], sensor[i], sensor[i] - sensor[i - 1]));
        }
        Collections.sort(list);
        for (int i = 0; i < K - 1; i++) {
            result.add(list.get(i).s);
            result.add(list.get(i).e);
        }
        Collections.sort(result);
        for (int i = 0; i < result.size(); i += 2) {
            min += (result.get(i + 1) - result.get(i));
        }
        System.out.println(min);
    }

    public static void main(String[] args) throws IOException {
        BOJ2212.solution();
    }
}