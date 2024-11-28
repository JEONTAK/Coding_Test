package BaekJoon.Gold2.공항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10775 {

    static int G, P, result = 0;
    static int[] parent;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        parent = new int[G + 1];
        for(int i = 0 ; i <= G ; i++){
            parent[i] = i;
        }

        for(int i = 0 ; i < P ; i++){
            int cur = Integer.parseInt(br.readLine());
            int aG = find(cur);
            if (aG == 0) {
                break;
            }
            result++;
            union(aG, aG - 1);
        }
        System.out.println(result);
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        int rx = find(x);
        int ry = find(y);
        if(rx != ry){
            parent[rx] = ry;
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ10775.solution();
    }
}

//해결 방법
//간단히 생각하면, 들어온 값에 해당하는 게이트 부터 차례대로 해당 게이트가 비어있는지 판별한 후, 비어있으면 해당 게이트에 비행기를 도킹하는 것이다.
//해당 문제의 특징은 게이트에 비행기를 도킹하고나서 해당 게이트보다 1 작은 게이트로 가야한다고 알려줘야 한다는 것이다.
//여기서 union find를 사용하여, 먼저 들어온 값을 사용해 find(gi)를 하여 parent[gi]가 어디를 가리키고 있는지 구한다.
//해당 값이 0이라면 1 ~ gi까지 모든 곳이 비행기가 도킹해있다는 의미이고, 따라서 비행기를 도킹할수 없다.
//따라서 find(gi) == 0이면 중단하고 결과값을 출력하고,
//0이 아니면, 결과값에 + 1을 하고, find(gi)의 값과 해당 위치보다 1 작은 값을 union하여 gi값이 들어오면 gi - 1로 이동할 수 있게 해준다.
