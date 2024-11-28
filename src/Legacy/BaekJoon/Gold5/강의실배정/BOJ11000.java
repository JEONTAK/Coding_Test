package BaekJoon.Gold5.강의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11000 {

    static class Lecture implements Comparable<Lecture>{
        int s;
        int t;

        public Lecture(int s, int t) {
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.s == o.s) {
                return this.t - o.t;
            }
            return this.s - o.s;
        }
    }

    static int N, R = 0;
    static PriorityQueue<Integer> room = new PriorityQueue<>();
    static ArrayList<Lecture> list = new ArrayList<>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            list.add(new Lecture(s, t));
        }
        Collections.sort(list);
        room.offer(list.get(0).t);
        for (int i = 1; i < N; i++) {
            if (room.peek() <= list.get(i).s) {
                room.poll();
            }
            room.offer(list.get(i).t);
        }
        System.out.println(room.size());
    }

    public static void main(String[] args) throws IOException {
        BOJ11000.solution();
    }
}
