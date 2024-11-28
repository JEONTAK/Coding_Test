package BaekJoon.Gold2.책나눠주기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ9576 {

    static class Student implements Comparable<Student> {
        int a;
        int b;

        public Student(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Student o) {
            if (this.b == o.b) {
                return this.a - o.a;
            }
            return this.b - o.b;
        }
    }

    static int T, N, M;
    static PriorityQueue<Student> pq;
    static boolean[] check;
    static int result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            result = 0;
            pq = new PriorityQueue<>();
            check = new boolean[N + 1];
            for(int i = 0 ; i < M ; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                pq.add(new Student(a, b));
            }

            while(!pq.isEmpty()){
                Student cur = pq.poll();
                for(int i = cur.a ; i <= cur.b ; i++){
                    if (!check[i]) {
                        check[i] = true;
                        result++;
                        break;
                    }
                }
            }
            sb.append(result + "\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ9576.solution();
    }
}

//우선순위 큐를 사용하여 그리디 알고리즘으로 풀게 되었다.
//우선순위를 정해야하는데,
//처음에는 a~b범위가 작은 것으로 오름차순, 범위가 같으면 a값이 작은것 부터 오름차순으로 정렬했다.
//이렇게 하면 반례가 존재해 틀린 답이 된다.
//따라서 해당 문제는 다음과 같이 정렬해야 한다.
//b값에 대하여 오름차순 정렬, b값이 같다면 a값에 대하여 오름차순 정렬