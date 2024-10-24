package BaekJoon.Gold2.퍼즐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1525 {

    static final int N = 3;
    static HashMap<String, Integer> hm = new HashMap<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static String result = "123456780";


    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder init = new StringBuilder();
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                init.append(st.nextToken());
            }
        }
        hm.put(init.toString(), 0);
        System.out.println(compute(init.toString()));
    }

    static int compute(String init) {

        Queue<String> q = new LinkedList<String>();
        q.add(init);

        while (!q.isEmpty()) {
            String cur = q.poll();
            int cnt = hm.get(cur);
            int zero = cur.indexOf("0");
            int x = zero % 3;
            int y = zero / 3;

            if (cur.equals(result)) {
                return cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isAvailable(nx, ny)) {
                    int next = ny * 3 + nx;
                    char c = cur.charAt(next);
                    String n = cur.replace(c, 'c');
                    n = n.replace('0', c);
                    n = n.replace('c', '0');

                    if(!hm.containsKey(n)) {
                        q.add(n);
                        hm.put(n, cnt + 1);
                    }
                }
            }
        }
        return -1;
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ1525.solution();
    }
}

//3X3배열을 String 형식으로 만든후, HashMap에  <배열, 움직인 횟수>로 저장하면 된다.
//만약 String 형식으로 만든 것이 이미 있다면 queue에 추가하지 않는다.
//정답인 "123456780"이 나오면 해당 cnt를 리턴하고, 큐를 다 돌았는데도 나오지 않으면 -1를 리턴해주면 된다.