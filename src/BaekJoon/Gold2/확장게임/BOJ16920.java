package BaekJoon.Gold2.확장게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16920 {

    static class Node{
        char idx;
        int x;
        int y;
        int d;

        public Node(char idx, int x, int y, int d) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int N, M, P;
    static int[] S;
    static int[] result;
    static char[][] map;
    static ArrayList<Node>[] list;
    static boolean[][] visited;
    static int curRemain = 0, preRemain = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        S = new int[P + 1];
        result = new int[P + 1];
        list = new ArrayList[P + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= P ; i++){
            S[i] = Integer.parseInt(st.nextToken());
            list[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < N ; i++){
            map[i] = br.readLine().toCharArray();
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] != '.' && map[i][j] != '#'){
                    list[map[i][j] - '0'].add(new Node(map[i][j], i, j, 0));
                    visited[i][j] = true;
                }
                if (map[i][j] == '.') {
                    curRemain++;
                }
            }
        }

        while(true){
            for(int i = 1; i <= P ; i++){
                compute(i);
            }
            if(curRemain == 0 || preRemain == curRemain)break;
            preRemain = curRemain;
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] != '.' && map[i][j] != '#'){
                    result[map[i][j] - '0']++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= P ; i++){
            sb.append(result[i] + " ");
        }
        System.out.println(sb.toString());
    }

    static void compute(int idx){
        if(curRemain == 0)
            return;
        Queue<Node> q = new LinkedList<>();
        for (Node node : list[idx]) {
            q.add(node);
            visited[node.x][node.y] = true;
        }
        list[idx].clear();
        while(!q.isEmpty()){
            Node cur = q.poll();
            boolean flag = true;
            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isAvailable(nx, ny) && !visited[nx][ny] && cur.d < S[cur.idx - '0'] && map[nx][ny] != '#') {
                    flag = false;
                    visited[nx][ny] = true;
                    curRemain--;
                    map[nx][ny] = cur.idx;
                    q.add(new Node(cur.idx, nx, ny, cur.d + 1));
                }
            }
            if(flag){
                list[idx].add(new Node(cur.idx, cur.x, cur.y, 0));
            }
        }
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BOJ16920.solution();
    }
}
//BFS 문제였다.
//Node라는 class를 만들어 해당 클래스 내에 idx, 좌표를 저장하였다.
//해당 노드를 리스트화 하여 각각 idx 별로 저장하였고, BFS를 돌릴때 해당 리스트 내에 저장되어 있는 좌표를 큐에 입력하였다.
//리스트를 clear한 후, 큐를 돌때 큐에서 뽑은 현재 위치에서 더이상 갈곳이 없다면 이후 다시 쓰기위해 리스트에 저장해주었다.
//해당 과정을 반복하면 결과가 나오는데,
//여기서 주의할 점은 벽에 둘러싸여 들어갈 수없는 곳이 있을 수 있다.
//따라서 현재 남은 곳과 이전 남은 곳을 각각 저장하여, 두 값이 같다면 BFS를 돌렸는데도 변화가 없으므로, 더 이상 갈곳이 없다고 판단할 수 있다.
//-> 이떄 결과 값을 출력해주면 될것이다.