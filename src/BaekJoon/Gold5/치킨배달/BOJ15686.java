package BaekJoon.Gold5.치킨배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686 {

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] map;
    static ArrayList<Node> house = new ArrayList<>();
    static ArrayList<Node> chicken = new ArrayList<>();
    static boolean[] check;
    static int result = Integer.MAX_VALUE;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    house.add(new Node(i, j));
                }
                if (map[i][j] == 2) {
                    chicken.add(new Node(i, j));
                }
            }
        }
        check = new boolean[chicken.size()];
        setChicken(0, 0);
        System.out.println(result);
    }

    static void setChicken(int cnt, int idx) {
        if (cnt == M) {
            compute();
            return;
        }

        for (int i = idx; i < chicken.size(); i++) {
            if (!check[i]) {
                check[i] = true;
                setChicken(cnt + 1, i + 1);
                check[i] = false;
            }
        }
    }

    static void compute(){
        int sum = 0;
        for (int i = 0; i < house.size(); i++) {
            int min = Integer.MAX_VALUE;
            for(int j = 0 ; j < chicken.size(); j++) {
                if (check[j]) {
                    int d = Math.abs(house.get(i).x - chicken.get(j).x) + Math.abs(house.get(i).y - chicken.get(j).y);
                    min = Math.min(min, d);
                }
            }
            sum += min;
        }
        result = Math.min(result, sum);
    }

    public static void main(String[] args) throws IOException {
        BOJ15686.solution();
    }
}
