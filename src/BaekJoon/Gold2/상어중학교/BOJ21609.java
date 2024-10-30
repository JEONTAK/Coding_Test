package BaekJoon.Gold2.상어중학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ21609 {

    static class Node{
        int x;
        int y;
        int c;
        int rCnt;
        int cnt;

        public Node(int x, int y, int c, int rCnt, int cnt) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.rCnt = rCnt;
            this.cnt = cnt;
        }

        public Node(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    static int N, M;
    static int[][] map;
    static int result = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static ArrayList<Node> list = new ArrayList<Node>();

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
            }
        }

        while (true) {
            list.clear();
            if(!findBlock()) {
                break;
            }
            result += maxBlock();
            turn();
        }
        System.out.println(result);
    }

    static void turn(){
        for (int i = N - 1; i >= 0; i--) {
            for(int j = N - 1 ; j >= 0; j--) {
                if (map[i][j] == -2) {
                    gravity(i, j);
                }
            }
        }

        int[][] tempMap = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[N - j - 1][i] = tempMap[i][j];
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            for(int j = N - 1 ; j >= 0; j--) {
                if (map[i][j] == -2) {
                    gravity(i, j);
                }
            }
        }
    }

    static void gravity(int x, int y) {
        for (int i = x - 1; i >= 0; i--) {
            if (map[i][y] >= 0) {
                map[x][y] = map[i][y];
                map[i][y] = -2;
                return;
            } else if (map[i][y] == -1) {
                return;
            }
        }
    }

    static int maxBlock() {
        list.sort((o1, o2) -> {
            if(o1.rCnt == o2.rCnt) {
                if(o1.x == o2.x){
                    return o2.y - o1.y;
                }
                return o2.x - o1.x;
            }
            return o2.rCnt - o1.rCnt;
        });
        Node node = list.get(0);

        boolean[][] check = new boolean[N][N];
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        map[node.x][node.y] = -2;
        check[node.x][node.y] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isAvailable(nx, ny) && !check[nx][ny] && (map[nx][ny] == 0 || map[nx][ny] == cur.c)) {
                    check[nx][ny] = true;
                    map[nx][ny] = -2;
                    q.add(new Node(nx, ny, cur.c, cur.rCnt, cur.cnt));
                }
            }
        }
        return node.cnt * node.cnt;
    }

    static boolean findBlock() {
        int cnt = 0;
        visited = new boolean[N][N];
        for(int i = 0; i < N ; i++) {
            for(int j = 0 ; j < N; j++) {
                if (!visited[i][j] && map[i][j] >= 1) {
                    int curCnt = 0;
                    int curRCnt = 0;
                    visited[i][j] = true;
                    Queue<Node> q = new LinkedList<>();
                    q.add(new Node(i, j, map[i][j]));
                    while(!q.isEmpty()) {
                        Node cur = q.poll();
                        curCnt++;
                        if(map[cur.x][cur.y] == 0){
                            curRCnt++;
                        }
                        for (int k = 0; k < 4; k++) {
                            int nx = cur.x + dx[k];
                            int ny = cur.y + dy[k];
                            if (isAvailable(nx, ny) && !visited[nx][ny] && (map[nx][ny] == 0 || map[nx][ny] == cur.c)) {
                                visited[nx][ny] = true;
                                q.add(new Node(nx, ny, cur.c));
                            }
                        }
                    }
                    if (cnt < curCnt) {
                        list.clear();
                        cnt = curCnt;
                        list.add(new Node(i, j, map[i][j], curRCnt, curCnt));
                    }else if(cnt == curCnt) {
                        list.add(new Node(i, j, map[i][j], curRCnt, curCnt));
                    }
                }
                rainbowClear();
            }
        }
        return cnt >= 2;
    }

    static void rainbowClear() {
        for (int i = 0; i < N; i++) {
            for(int j = 0 ; j < N ; j++) {
                if (map[i][j] == 0) {
                    visited[i][j] = false;
                }
            }
        }
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }


    public static void main(String[] args) throws IOException {
        BOJ21609.solution();
    }
}

//문제 내용대로 구현하면 되는 시뮬레이선 문제이다
//하지만 내용이 방대해서 시간을 많이 잡아먹었다.
//계속 사소한점에서 틀렸는데,
//블록을 만들때는 행과 열이 작은 것을 기준으로 하고,
//해당 블록을 선택할때는 행과 열이 큰것을 기준으로 잡아야한다.
//해당 부분에서 둘다 행과 열이 큰것을 기준으로 잡아 사소한 부분에서 시간을 많이 소비한 듯 하다.
//구현을 간단히 설명하자면,
//맵에서 블록들을 만들어 블록의 크기가 가장 큰것들만 집어넣는다
//해당 리스트를 정렬하여 제거할 블록을 선택하고, 제거한다
//중력 -> 반시계방향 회전 -> 중력의 과정을 거친후 다시 블록을 뽑는다.