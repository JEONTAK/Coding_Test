package BaekJoon.Gold2.스타트택시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19238 {

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            if (this.d == o.d) {
                if(this.x ==o.x){
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.d - o.d;
        }
    }
    static class Client{
        int sX;
        int sY;
        int fX;
        int fY;

        public Client(int sX, int sY, int fX, int fY) {
            this.sX = sX;
            this.sY = sY;
            this.fX = fX;
            this.fY = fY;
        }
    }

    static int N, M, F, sX, sY;
    static int[][] map;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static ArrayList<Client> list = new ArrayList<Client>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        sX = Integer.parseInt(st.nextToken()) - 1;
        sY = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Client(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
        }
        boolean flag = true;
        while (!list.isEmpty()) {
            flag = compute(sX,sY);
            if(!flag) break;
        }
        System.out.println(flag ? F : -1);
    }

    static boolean compute(int sX, int sY) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        pq.add(new Node(sX, sY, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;

            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).sX == cur.x && list.get(i).sY == cur.y) {
                    Client client = list.get(i);
                    F -= cur.d;
                    list.remove(i);
                    return getClient(client);
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isAvailable(nx, ny) && !visited[nx][ny] && map[nx][ny] == 0 && cur.d < F) {
                    pq.add(new Node(nx, ny, cur.d + 1));
                }
            }
        }
        return false;
    }

    static boolean getClient(Client client){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        pq.add(new Node(client.sX, client.sY, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;
            if(cur.x == client.fX && cur.y == client.fY) {
                sX = client.fX;
                sY = client.fY;
                F += cur.d;
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isAvailable(nx, ny) && !visited[nx][ny] && map[nx][ny] == 0 && cur.d < F) {
                    pq.add(new Node(nx, ny, cur.d + 1));
                }
            }
        }
        return false;
    }

    static boolean isAvailable(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BOJ19238.solution();
    }
}

//먼저 승객 class를 따로 만들어 저장해 놓는다.
//이후 DFS를 사용하여 시작점에서 가장 가까운 승객을 찾는다.
//-> boolean compute(택시 위치)
//      여기서 만약 주어진 연료 내에 승객을 태울 수 없다면 false를 return한다.
//      주어진 연료 내에서 가장 가까운 승객을 찾았다면, 전체 연료에서 현재 이동한 거리를 빼준다.
//      Client 리스트에서 해당 승객을 제거한다.
//      해당 승객을 태우고 택시가 움직이는 함수를 생성해 도착지까지 계산하여 해당 boolean값을 return 한다.
//-> boolean getResult(현재 승객)
//      승객의 위치를 택시의 시작 위치로 정하고, Node를 이용하여 DFS를 실행한다.
//      만약 주어진 연료 내에 승객을 목적지에 내릴수 없다면, false를 return한다.
//      만약 주어진 연료 내에 승객을 목적지에 내릴 수 있다면,
//          다음 compute에 들어갈 택시위치를 승객의 목적지 위치로 설정하고,
//          현재 연료 값에 움직인 거리 만큼 더해준다.
//          그리고 true를 return 해준다.
//-> main()
//      while문을 이용해 list가 빌때까지, 즉 모든 승객을 목적지에 내려줄 때까지 돌린다.
//      여기서 compute를 사용하여 손님을 태워 목적지에 내릴수 있으면 true, 없으면 false를 flag에 반환할수 있게 한다.
//      만약 flag가 false이면 더 이상 손님을 태울 수 없는 경우 이므로, -1를 출력한다.
//      만약 모든 승객들을 목적지에 내려줘 list가 비어졌다면, flag는 당연스럽게 true이므로, 현재 연료값인 F를 출력해준다.