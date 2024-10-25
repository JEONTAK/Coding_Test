package BaekJoon.Gold2.새로운게임2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17837 {

    static int N, K;
    static int[][] map, chess;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int cnt = 0;
    static List<Integer>[][] list;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        chess = new int[K][3];
        list = new List[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                list[i][j] = new ArrayList<>();
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            chess[i][0] = r - 1;
            chess[i][1] = c - 1;
            chess[i][2] = d - 1;
            list[r - 1][c - 1].add(i);
        }

        while(cnt < 1000){
            cnt++;
            for(int i = 0 ; i < K ; i++){
                i = compute(i);
            }
        }
        System.out.println(-1);
    }

    static int compute(int idx) {
        int i = idx;
        int r = chess[idx][0];
        int c = chess[idx][1];
        int d = chess[idx][2];

        int nr = r + dx[d];
        int nc = c + dy[d];

        if (isAvailable(nr, nc)) {
            if(map[nr][nc] == 0){
                int cur = list[r][c].indexOf(idx);

                list[nr][nc].addAll(list[r][c].subList(cur, list[r][c].size()));

                list[r][c] = list[r][c].subList(0, cur);

                chess[idx][0] = nr;
                chess[idx][1] = nc;

                for (Integer k : list[nr][nc]) {
                    chess[k][0] = nr;
                    chess[k][1] = nc;
                }
            }
            else if(map[nr][nc] == 1) {
                int cur = list[r][c].indexOf(idx);

                List<Integer> subList = list[r][c].subList(cur, list[r][c].size());
                Collections.reverse(subList);
                list[nr][nc].addAll(subList);
                list[r][c] = list[r][c].subList(0, cur);

                chess[idx][0] = nr;
                chess[idx][1] = nc;

                for (Integer k : list[nr][nc]) {
                    chess[k][0] = nr;
                    chess[k][1] = nc;
                }
            }else if(map[nr][nc] == 2){
                int changeD = (d % 2 == 0) ? d + 1 : d - 1;
                chess[idx][2] = changeD;

                if(isAvailable(r + dx[changeD], c + dy[changeD])){
                    if(map[r + dx[changeD]][c + dy[changeD]] != 2){
                        i--;
                    }
                }
            }
            if (list[nr][nc].size() > 3) {
                System.out.println(cnt);
                System.exit(0);
            }
        }
        else{
            int changeD = (d % 2 == 0) ? d + 1 : d - 1;
            chess[idx][2] = changeD;
            if(map[r + dx[changeD]][c + dy[changeD]] != 2){
                i--;
            }
        }


        return i;
    }

    static boolean isAvailable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ17837.solution();
    }
}
