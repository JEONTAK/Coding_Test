package BaekJoon.Gold1.어른상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19237 {

    static class Shark{
        int x;
        int y;
        int d;
        int[][] delta;

        public Shark(int x, int y, int d, int[][] delta) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.delta = delta;
        }
    }

    static int N, M, K;
    static int[][][] map;
    static Shark[] sharks;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int remain;
    //0 위 1 아래 2 왼쪽 3 오른쪽

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N][2];
        sharks = new Shark[M + 1];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j ++){
                map[i][j][0] = Integer.parseInt(st.nextToken());
                if (map[i][j][0] > 0) {
                    int[][] delta = new int[4][4];
                    sharks[map[i][j][0]] = new Shark(i, j, 0, delta);
                    map[i][j][1] = K;
                }
            }
        }
        remain = M;
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= M ; i++){
            sharks[i].d = Integer.parseInt(st.nextToken()) - 1;
        }

        for(int i = 1 ; i <= M ; i++){
            for(int j = 0 ; j < 4 ; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0 ; k < 4 ; k++){
                    sharks[i].delta[j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        int cnt = 1;
        while (cnt < 1001) {
            compute();
            if (remain == 1) {
                System.out.println(cnt);
                System.exit(0);

            }
            cnt++;
        }
        System.out.println(-1);
    }

    static void compute(){
        for(int i = M ; i >= 1 ; i--){
            boolean flag = false;
            Shark cur = sharks[i];
            if(cur.x == -1 && cur.y == -1){
                continue;
            }

            for(int j = 0 ; j < 4 ; j++){
                int nx = cur.x + dx[cur.delta[cur.d][j]];
                int ny = cur.y + dy[cur.delta[cur.d][j]];
                if (isAvailable(nx, ny) && map[nx][ny][0] == 0) {
                    flag = true;
                    cur.x = nx;
                    cur.y = ny;
                    cur.d = cur.delta[cur.d][j];
                    break;
                }
            }
            if(flag)continue;

            for(int j = 0 ; j < 4 ; j++){
                int nx = cur.x + dx[cur.delta[cur.d][j]];
                int ny = cur.y + dy[cur.delta[cur.d][j]];
                if (isAvailable(nx, ny) && map[nx][ny][0] == i) {
                    cur.x = nx;
                    cur.y = ny;
                    cur.d = cur.delta[cur.d][j];
                    break;
                }
            }
        }

        for(int i = M ; i >= 1 ; i--){
            Shark cur = sharks[i];
            if(cur.x == -1 && cur.y == -1){
                continue;
            }
            map[cur.x][cur.y][0] = i;
            map[cur.x][cur.y][1] = K + 1;
        }

        for(int i = 1 ; i <= M ; i++){
            Shark cur = sharks[i];
            if(cur.x == -1 && cur.y == -1){
                continue;
            }
            if(map[cur.x][cur.y][0] != i){
                cur.x = -1;
                cur.y = -1;
                remain--;
            }
        }


        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                map[i][j][1] = Math.max(map[i][j][1] - 1, 0);
                if (map[i][j][1] == 0 && map[i][j][0] != 0) {
                    map[i][j][0] = 0;
                }
            }
        }
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void print(){
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                System.out.print(map[i][j][0] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BOJ19237.solution();
    }
}

//간단한 구현문제이다.
//상어가 1마리 남는것을 확인하기 위해 remain에 M 값을 저장해주었다.
//먼저 방향을 위 : 0 / 아래 : 1 / 왼 : 2 / 오 : 3 으로 정하고
//delta[4][4]를 만들어서 해당 방향에서의 우선순위를 저장하고,
//Shark class를 만들어 해당 위치, 방향, delta값을 저장하였다.
//또한 map 배열을 [][][] 3차원 배열로 만들고 [x 좌표][y 좌표][정보]로 구성하였다.
//정보에는 [0] : 해당 위치의 상어 or 냄새 / [1] : 남은 시간 을 저장해주었다.
//해당 상어들을 번호가 높은것부터 움직여 줬는데, 그 이유는 번호가 낮은 상어가 해당 상어를 내쫒을 수 있게 하기 위함이다.
//해당 상어에서 먼저 delta[][] 값을 이용해 우선적으로 가는 뱡향 및 다음 위치를 구하고, 해당 위치가 비어있으면 해당 위치로 옮겨주었다.
//모든 상어에서 진행한 후, 다시 번호가 높은 상어부터 현재 위치에 해당하는 좌표를 map[현재 x][현재 y]에 상어 번호, K + 1(남은시간) 을 넣어준다.
//K + 1 을 넣은 이유는 이후 한번에 -1하기 위함이다.
//위 방식을 진행하면 같은 위치에 간 상어들 중 가장 번호가 낮은 상어의 번호로 덮어진다.
//이것을 이용해 만약 상어 본인의 위치에 해당하는 map의 값이 본인과 다르다면, x = -1, y = -1을 해주어 상어가 도망가버렸다고 설정해주고 remain-- 해준다.
//모든 상어에서 진행 하고 map배열을 다 돌아 시간 값을 -1 씩 해준다. 만약 -1 해준값이 0이 된다면, 해당 위치의 상어 번호도 0으로 해주어 상어 및 냄새가 없다고 해준다.
//이후 remain이 1이 되었다면 상어가 한마리 남았다는 의미이므로, 시간 값을 출력해주고 종료해주면 된다.
//만약 1000초가 지나도 remain이 1 보다 크다면 -1을 출력해주면 된다.