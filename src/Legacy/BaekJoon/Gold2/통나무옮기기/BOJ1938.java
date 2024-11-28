package BaekJoon.Gold2.통나무옮기기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1938 {

    static class Node{
        int x;
        int y;
        int l;
        int cnt;

        public Node(int x, int y, int l, int cnt) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.cnt = cnt;
        }
    }

    static int N;
    static char[][] map;
    static int sx = 0;
    static int sy = 0;
    static int fx = 0;
    static int fy = 0;
    static int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1};
    static int[] dy = {0, -1, 0, 1, -1, -1, 1, 1};
    static boolean[][][] visited;
    static int result = Integer.MAX_VALUE;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for(int i = 0; i < N; i++) {
            String temp = br.readLine();
            for(int j = 0 ; j < N ; j++){
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'B') {
                    sx += i;
                    sy += j;
                }
                if (map[i][j] == 'E') {
                    fx += i;
                    fy += j;
                }
            }
        }
        sx /= 3;
        sy /= 3;
        fx /= 3;
        fy /= 3;
        int l = 1;
        for(int i = 0 ; i < 4 ; i++){
            int nx = sx + dx[i];
            int ny = sy + dy[i];
            if(isAvailable(nx,ny, 2) && map[nx][ny] == 'B'){
                if (i % 2 != 0) {
                    l = 0;
                }
                break;
            }
        }
        Queue<Node> q = new LinkedList<>();
        visited = new boolean[N][N][2];
        visited[sx][sy][l] = true;
        q.add(new Node(sx, sy, l, 0));
        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.x == fx && cur.y == fy){
                result = Math.min(result, cur.cnt);
                continue;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isAvailable(nx, ny, cur.l) && !visited[nx][ny][cur.l] && isNotTree(nx, ny, cur.l)) {
                    visited[nx][ny][cur.l] = true;
                    q.add(new Node(nx, ny, cur.l, cur.cnt + 1));
                }
            }

            boolean canTurn = true;
            for(int i = 0 ; i < 8 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!isAvailable(nx, ny, 2) || map[nx][ny] == '1') {
                    canTurn = false;
                    break;
                }
            }
            if(canTurn && !visited[cur.x][cur.y][cur.l == 0 ? 1 : 0]){
                visited[cur.x][cur.y][cur.l == 0 ? 1 : 0] = true;
                q.add(new Node(cur.x, cur.y, cur.l == 0 ? 1 : 0, cur.cnt + 1));
            }
        }
        System.out.println(result != Integer.MAX_VALUE ? result : 0);
    }

    static boolean isNotTree(int x, int y, int lay) {
        if(lay == 0){
            if(map[x][y] == '1')
                return false;
            for(int i = 1; i <= 3 ; i +=2){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(!isAvailable(nx,ny,2) || map[nx][ny] == '1'){
                    return false;
                }
            }
        }else{
            if(map[x][y] == '1')
                return false;
            for(int i = 0; i <= 2 ; i +=2){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(!isAvailable(nx,ny,2) || map[nx][ny] == '1'){
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isAvailable(int x, int y, int c){
        if(c == 0) {
            return x >= 0 && x < N && y >= 1 && y < N - 1;
        }else if(c == 1){
            return x >= 1 && x < N - 1 && y >= 0 && y < N;
        }else{
            return x >= 0 && x < N && y >= 0 && y < N;
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1938.solution();
    }
}
//통나무는 가로3칸 또는 세로 3칸이므로, 일단 중간값을 기준으로 BFS를 돌리기로 생각하였다.
//따라서 먼저 통나무의 중간값을 구하고, 해당 통나무가 세로면 l을 1, 가로면 0으로 설정하여 계산하였다.
//Node라는 class를 만들어 통나무의 현재 x, y, cnt, l(가로인지, 세로인지)를 저장해주었고,
//Queue를 사용하여 구현했다.
//먼저 현재 위치가 도착 위치와 같다면 result와 cnt를 비교하여 작은 값을 넣도록 하였다.
//만약 다르다면, 먼저 해당 상태에서 상, 하, 좌, 우로 움직일 수 있는지 찾고, 움직일 수 있다면 이미 방문했는지 체크한다,
//이후 이동할 위치에 나무가 있다면 움직이지 못하기 때문에, 해당 조건도 체크해준다.
//모든 조건이 만족한다면, 방문체크를 해주고 큐에 cnt +1 한상태로 넣어준다.
//이후에는 통나무를 돌리는 것도 계산해준다.
//먼저 통나무 중심 기준 8방향 모두가 0 이어야 하기때문에, 8방향 모두다 해당 위치가 존재하는지, 그리고 나무가 이미 있는지 계산한다.
//만약 조건이 성립한다면 해당 위치와 상태가 이미 방문했는지 체크하고,
//방문하지 않았다면, 방문체크를 해준뒤, 큐에 현재 위치, cnt + 1, lay == 0 ? 1 : 0을 하여 저장한다.
//큐 전체를 돌고 결과값을 출력해준다.
