package BaekJoon.Gold1.열쇠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9328 {

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int T, H, W;
    static char[][] map;
    static boolean[] keys;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static ArrayList<Node> waitList;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while(T -- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            for(int i = 0 ; i < H ; i++){
                map[i] = br.readLine().toCharArray();
            }
            keys = new boolean[26];
            waitList = new ArrayList<Node>();
            String temp = br.readLine();
            char[] alpha;
            if (!temp.equals("0")) {
                alpha = temp.toCharArray();
                for(int i = 0 ; i < alpha.length ; i++){
                    keys[alpha[i] - 'a'] = true;
                }
            }
            visited = new boolean[H][W];
            Queue<Node> q = new LinkedList<>();
            for(int i = 0 ; i < H ; i++){
                for(int j = 0 ; j < W; j++){
                    if(i == 0 || j == 0 || i == H - 1 || j == W - 1){
                        if (map[i][j] != '*') {
                            if (map[i][j] == '.') {
                                q.add(new Node(i, j));
                                visited[i][j] = true;
                            } else if (map[i][j] == '$') {
                                q.add(new Node(i, j));
                                visited[i][j] = true;
                            } else if (map[i][j] - 'a' >= 0 && map[i][j] - 'a' <= 25) {
                                keys[map[i][j] - 'a'] = true;
                                q.add(new Node(i, j));
                                visited[i][j] = true;
                            }
                        }
                    }
                }
            }

            for(int i = 0 ; i < H ; i++){
                for(int j = 0 ; j < W; j++){
                    if(i == 0 || j == 0 || i == H - 1 || j == W - 1){
                        if (map[i][j] - 'A' >= 0 && map[i][j] - 'A' <= 25) {
                            if (keys[map[i][j] - 'A']) {
                                q.add(new Node(i, j));
                                visited[i][j] = true;
                            } else {
                                waitList.add(new Node(i, j));
                            }
                        }
                    }
                }
            }

            while(!q.isEmpty()){
                Node cur = q.poll();
                for(int i = 0 ; i < 4 ; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(!isAvailable(nx,ny)) continue;
                    if(map[nx][ny] == '*') continue;
                    if (map[nx][ny] == '.') {
                        if(!visited[nx][ny]){
                            visited[nx][ny] = true;
                            q.add(new Node(nx, ny));
                        }
                    }else if(map[nx][ny] == '$'){
                        if(!visited[nx][ny]){
                            visited[nx][ny]= true;
                            q.add(new Node(nx, ny));
                        }
                    }else{
                        //열쇠
                        if (map[nx][ny] - 'a' >= 0 && map[nx][ny] - 'a' <= 25) {
                            if (!visited[nx][ny]) {
                                keys[map[nx][ny] - 'a'] = true;
                                visited[nx][ny] = true;
                                q.add(new Node(nx, ny));
                            }
                            for(int j = 0 ; j < waitList.size(); j++){
                                Node node = waitList.get(j);
                                if(map[node.x][node.y] == map[nx][ny] - 32){
                                    if(!visited[node.x][node.y]){
                                        visited[node.x][node.y] = true;
                                        q.add(new Node(node.x, node.y));
                                        waitList.remove(j);
                                        j--;
                                    }
                                }
                            }
                        }//문
                        else if(map[nx][ny] - 'A' >= 0 && map[nx][ny] - 'A' <= 25){
                            if (keys[map[nx][ny] - 'A']) {
                                if (!visited[nx][ny]) {
                                    visited[nx][ny] = true;
                                    q.add(new Node(nx, ny));
                                }
                            }else{
                                waitList.add(new Node(nx, ny));
                            }
                        }
                    }
                }
            }
            int result = 0;
            for(int i = 0 ; i < H ; i++){
                for(int j = 0 ; j < W ; j++){
                    if (visited[i][j] && map[i][j] == '$') {
                        result++;
                    }
                }
            }
            sb.append(result + "\n");
        }
        System.out.println(sb.toString());

    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < H && y >= 0 && y < W;
    }

    public static void main(String[] args) throws IOException {
        BOJ9328.solution();
    }
}
//BFS 알고리즘을 사용하였다.
//먼저 가로 세로 밖과 인접해 있논 부분 중에서 . , $ , 알파벳 소문자인 곳들을 먼저 큐에 넣어주었다.
//알파벳 소문자인 곳은 열쇠이므로, 열쇠 boolean 배열에 true 처리 해주었다.
//이후 문의 의미인 알파벳 대문자가 밖과 인접해 있는 경우에 열쇠가 있으면 큐에 넣고, 없으면 waitList에 넣어주었다.
//이후 큐를 돌리면서, 다음 위치가 . $ 인 경우에는 그대로 큐에 넣어주었다.
//만약 해당 지점이 열쇠를 의미하면, 해당 열쇠 boolean 배열에 true를 해주고, waitList에서 해당 열쇠를 가지고 열 수 있는 문이 있다면 큐에 추가하고 리스트에서 제거해준다.
//만약 해당 지점이 문을 의미하고, 열쇠가 있다면 그대로 큐에 넣어주고, 열쇠가 없다면 waitList에 넣어주었다.
//큐가 끝났다는 말은, 모든 위치를 다 돌았다는 의미 이므로, 이후 전체 map을 쭉 돌아 방문 한적이 있고, 해당 위치가 $라면 result++을 해주어 결과값을 저장한다.
//모든 테스트 케이스를 돌고 저장한 값들을 출력해준다