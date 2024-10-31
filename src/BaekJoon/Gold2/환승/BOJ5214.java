package BaekJoon.Gold2.환승;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5214 {

    static class Node{
        int e;
        boolean inLine;

        public Node(int e, boolean inLine) {
            this.e = e;
            this.inLine = inLine;
        }
    }

    static int N, K, M;
    static ArrayList<Integer>[] line;
    static ArrayList<Integer>[] station;
    static int[] dist, lineDist;
    static boolean[] visited, checkLine;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        line = new ArrayList[M + 1];
        station = new ArrayList[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        lineDist = new int[M + 1];
        Arrays.fill(lineDist, Integer.MAX_VALUE);
        visited = new boolean[N + 1];
        checkLine = new boolean[M + 1];
        dist[1] = 1;
        for(int i = 1 ; i <= N ; i++){
            station[i] = new ArrayList<>();
        }
        for(int i = 1 ; i <= M ; i++){
            line[i] = new ArrayList<>();
        }
        for(int i = 1 ; i <= M ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < K ; j++){
                int s = Integer.parseInt(st.nextToken());
                station[s].add(i);
                line[i].add(s);
            }
        }
        Queue<Node> q = new LinkedList<>();
        visited[1] = true;
        q.add(new Node(1, false));
        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.inLine){
                for (Integer next : line[cur.e]) {
                    if(!visited[next]){
                        visited[next] = true;
                        dist[next] = Math.min(dist[next], lineDist[cur.e]);
                        q.add(new Node(next, false));
                    }
                }
            }else{
                for (Integer next : station[cur.e]) {
                    if (!checkLine[next]) {
                        checkLine[next] = true;
                        lineDist[next] = Math.min(lineDist[next], dist[cur.e] + 1);
                        q.add(new Node(next, true));
                    }
                }
            }
        }
        System.out.println(dist[N] == Integer.MAX_VALUE ? -1 : dist[N]);
    }

    public static void main(String[] args) throws IOException {
        BOJ5214.solution();
    }
}
//역들이 이어진 라인을 나타내는 line 배열과 라인들을 가지고 있는 역들의 배열인 station으로 나누어 진행하였다.
//Node 라는 class를 만들어 현재 역 또는 현재 라인 값과 역인지 라인인지 구분하는 boolean을 값으로 저장할 수 있게끔 만들었고,
//해당 Node를 이용하여 첫 시작은 1번 역이므로, node(1,false)로 시작하였다.
//이후 inLine == false 인 경우에는 현재 역에 위치하고 있으므로, 해당 역에서 탈수 있는 line idx 값과 true 값을 큐에 넣어주었고,
//inLine == true인 경우에는 현재 하이퍼튜브 안에 위치하고 있으므로, 해당 하이퍼튜브에서 이동할 수 있는 역 idx값과 false값을 큐에 넣어 진행하였다.
//물론 boolean 배열을 2개만들어 각각 이미 지난 역과 이미 탑승한 호선을 체크해 불필요한 중복을 하지 않도록 방지하였다.
//이동한 역의 값은 dist배열을 생성하여 관리하였는데, lineDist라는 배열을 추가로 생성해주었다.
//만약 현재 역이라면 lineDist배열에 현재 역 위치에 해당하는 dist값 + 1과 비교하여 더 작은 값을 lineDist에 저장해주고
//반대로 현재 튜브 안이라면 dist배열에 현재 라인 위치에 해당하는 lineDist값 과 비교하여 더 작은 값을 저장해주었다.