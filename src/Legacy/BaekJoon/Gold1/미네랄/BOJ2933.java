package BaekJoon.Gold1.미네랄;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2933 {

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int R, C, N;
    static char[][] map;
    static int[][] arr;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};


    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            map[R - i - 1] = temp.toCharArray();
        }
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int cur = Integer.parseInt(st.nextToken());
            removeM(cur - 1, i % 2 == 0);
            compute();
        }
        print();
    }

    static void compute(){
        ArrayList<Node> blocks = new ArrayList<Node>();
        boolean[][] visited = new boolean[R][C];
        arr = new int[R][C];
        int idx = 1;
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                if (!visited[i][j] && map[i][j] == 'x') {
                    visited[i][j] = true;
                    blocks.add(new Node(i, j));
                    arr[i][j] = idx;
                    Queue<Node> queue = new LinkedList<Node>();
                    queue.add(new Node(i, j));
                    while(!queue.isEmpty()){
                        Node cur = queue.poll();
                        for(int k = 0 ; k < 4 ; k++){
                            int nx = cur.x + dx[k];
                            int ny = cur.y + dy[k];
                            if(isAvailable(nx,ny) && !visited[nx][ny] && map[nx][ny] == 'x'){
                                visited[nx][ny] = true;
                                arr[nx][ny] = idx;
                                queue.add(new Node(nx,ny));
                            }
                        }
                    }
                    idx++;
                }
            }
        }

        for (Node block : blocks) {
            if(block.x == 0) continue;

            ArrayList<Node> list = new ArrayList<>();
            char[][] tempMap = new char[R][C];
            for(int i = 0 ; i < R ; i++){
                for(int j = 0 ; j < C ; j++){
                    tempMap[i][j] = map[i][j];
                }
            }
            int cur = arr[block.x][block.y];
            for(int i = R - 1 ; i >= 0 ; i--){
                for(int j = 0 ; j < C ; j++){
                    if (arr[i][j] == cur) {
                        tempMap[i][j] = '.';
                        list.add(new Node(i, j));
                    }
                }
            }
            boolean flag = true;
            while(true){
                for (Node node : list) {
                    int nx = node.x + dx[3];
                    int ny = node.y + dy[3];
                    if(!isAvailable(nx,ny) || tempMap[nx][ny] == 'x'){
                        flag = false;
                    }
                }

                if(!flag) break;

                for(Node node : list){
                    node.x += dx[3];
                    node.y += dy[3];
                }
            }

            for (Node node : list) {
                tempMap[node.x][node.y] = 'x';
            }

            for(int i = 0 ; i < R ; i++){
                for(int j = 0 ; j < C ; j++){
                    map[i][j] = tempMap[i][j];
                }
            }
        }
    }

    static void removeM(int idx, boolean flag){
        if (flag) {
            for(int i = 0 ; i < C ; i++){
                if(map[idx][i] == 'x'){
                    map[idx][i] = '.';
                    return;
                }
            }
        }else{
            for(int i = C - 1 ; i >= 0 ; i--){
                if(map[idx][i] == 'x'){
                    map[idx][i] = '.';
                    return;
                }
            }
        }
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    static void print(){
        for(int i = R - 1 ; i >= 0 ; i--){
            for(int j = 0 ; j < C ; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BOJ2933.solution();
    }
}
//BFS 문제였다.
//map 배열에 현재 미네랄 값을 저장한다.
//순서대로 해당 높이와 방향에 맞게 미네랄 값을 지워주고 만약 클러스터가 나뉘고 밑으로 내려갈 수 있다면
//해당 클러스터를 list로 저장한다(위에서부터 아래로) tempMap을 만들어 해당 list는 '.'으로 바꿔주고
//list에 있는 미네랄들을 하나씩 아래로 내려준다.
//내려줄때 만약 바닥에 도착하거나, 다른 미네랄에 걸렸다면, 더이상 움직이지 못하기 때문에 해당 위치를 tempMap에 'x'로 저장한다.
//만약 움직일 수 있다면 list의 모든 미네랄을 한칸 아래로 갱신한다.
//tempMap을 다시 map에 넣어주면 한단계가 끝난다.