package BaekJoon.Gold2.불켜기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11967 {

    static class Switch{
        int a;
        int b;

        public Switch(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static int N, M;
    static int[][] map;
    static ArrayList<Switch>[][] list;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        list = new ArrayList[N][N];
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                list[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[x - 1][y - 1].add(new Switch(a - 1, b - 1));
        }

        map[0][0] = 1;
        visited = new boolean[N][N];
        visited[0][0] = true;
        Queue<Switch> q = new LinkedList<>();
        q.add(new Switch(0, 0));
        while(!q.isEmpty()){
            Switch cur = q.poll();

            if (!list[cur.a][cur.b].isEmpty()) {
                visited = new boolean[N][N];
                visited[cur.a][cur.b] = true;
                for (Switch s : list[cur.a][cur.b]) {
                    if(map[s.a][s.b] == 0){
                        map[s.a][s.b] = 1;
                    }
                }
                list[cur.a][cur.b].clear();
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.a + dx[i];
                int ny = cur.b + dy[i];
                if (isAvailable(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
                    q.add(new Switch(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for(int j = 0 ; j < N ; j++){
                sum = map[i][j] == 1 ? sum + 1 : sum;
            }
        }
        System.out.println(sum);
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ11967.solution();
    }
}
//흠... 생각하기 까다로운 문제였다.
//이 문제의 핵심은 해당 위치에 도달하지 못하면 끝이 아닌, 다른 위치에서 스위치를 켰을때 해당 위치에 도달할 수 있다는 점을 생각해야한다.
//따라서 처음에는 Queue를 두개를 사용하였다.
//먼저 첫번쨰 큐에는 도달 할 수 있어 스위치를 킬 수 있게 만드는 큐
//두번째 큐에는 아직 도달할 수 없는 위치를 저장해놓았다.
//첫번째 큐에서 위치를 뽑고 해당 위치에서 킬수 있는 방의 위치를 두번째 큐에 저장한다.
//두번째 큐를 돌면서 해당 위치가 이동 가능하면 첫번쨰 큐에 넣어주고, 이동 불가능하면 두번쨰 큐에 다시 넣어 주는 방식으로 하였다.
//하지만 이렇게 하면 큐안에 큐가 있어 시간초과가 났다.
//따라서 참고한 방식은 다음과 같다
//방을 돌아다니는 큐를 하나 만든다
//0,0을 시작점으로 한다.
//큐에서 값을 뽑고, 해당 값이 비어있지 않다면, 새 visited 배열을 만들고 스위치로 킬수 있는 방의 위치에서 스위치를 켜 값을 map에 저장한다.
//해당 위치의 값은 clear해 이후 재탐색 할 수 없도록 해준다.
//해당 위치에서 상하좌우 위치를 구해 다음 장소가 불이 켜져있고, 갈수 있고, visited하지 않았다면 큐에 넣어준다.
//해당 알고리즘을 반복한다.
