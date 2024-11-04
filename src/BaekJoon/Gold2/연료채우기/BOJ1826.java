package BaekJoon.Gold2.연료채우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class BOJ1826 {

    static class Node implements Comparable<Node> {

        int a;
        int b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Node o) {
            return this.a - o.a;
        }
    }

    static int N, L, P;
    static PriorityQueue<Node> gas = new PriorityQueue<Node>();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            gas.add(new Node(a, b));
        }
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        if (L <= P) {
            System.out.println(0);
            return;
        }
        PriorityQueue<Integer> fuel = new PriorityQueue<>(Comparator.reverseOrder());
        int size = 0;
        while (P < L) {
            while (!gas.isEmpty() && gas.peek().a <= P) {
                fuel.add(gas.poll().b);
            }
            if(fuel.isEmpty()){
                System.out.println(-1);
                return;
            }
            size++;
            P += fuel.poll();
        }
        System.out.println(size);
    }

    public static void main(String[] args) throws IOException {
        BOJ1826.solution();
    }
}
//우선순위 큐는 역시 어떻게 정렬하냐, 어떻게 사용하냐가 중요한것 같다.
//참고해서 구현하였다.
//일단 주유소의 위치인 a를 오름차순으로 뽑는 우선순위 큐를 만들어 해당 큐에 값을 저장해주었다.
//여기서 현재위치에서 갈수 있는 거리의 주유소 중에서 가장 연료의 양이 많은 주유소를 방문해야 우리가 주유소 방문을 최소하 할 수 있을 것이다.
//따라서 연료양을 내림차순으로 하는 우선순위 큐 fuel을 만들어, 현재 위치에서 갈 수 있는 주유소의 위치의 연료의 양을 fuel에 저장한다.
//현재 위치에서 갈 수 있는 곳은 주유소의 위치가 연료의 누적량인 P와 작거나 같으면 될것이다.
//만약 연료가 없다 -> 갈수 있는 주유소를 다 갔는데도 도달하지 못한다 -> 도착점까지 도착을 하지 못하기떄문에 -1을 출력해주고 종료한다.
//연료가 있다면 우리는 연료의 양으로 내림차순 설정했으므로, fuel 큐에서 하나를 뽑아 P에 더해주어 P까지 갈 수 있게 되는 것이다.
//그리고 주유소를 방문한것이니 size++을 해주낟.
//위 방식을 연료 누적합인 P가 L 보닥 작을때 동안 반복 해주면 우리가 들릴 수 있는 최소 주유소 개수를 구할 수 있게 된다.
