package BaekJoon.Gold2.주사위윷놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17825 {

    static class Node{
        int score;
        int r;
        int b;
        boolean isB = false;

        public Node(int score, int r) {
            this.score = score;
            this.r = r;
        }
    }

    static final int N = 10;
    static int max = 0;
    static int[] dice = new int[N];
    static int[] horse = new int[N];
    static int[] cur;
    static boolean[] check;
    static Node[] map = new Node[43];

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i <= 40; i += 2) {
            map[i] = new Node(i, i + 2);
        }
        setMap();
        horse[0] = 0;
        compute(0);
        System.out.println(max);
    }

    static void compute(int cnt) {
        if (cnt == N) {
            cur = new int[4];
            check = new boolean[43];
            cal();
            return;
        }

        for (int i = 0; i < 4; i++) {
            horse[cnt] = i;
            compute(cnt + 1);
            horse[cnt] = -1;
        }
    }

    static void cal(){
        int score = 0;

        for(int i = 0 ; i < 10 ; i++){
            int end = move(horse[i], dice[i]);
            if(end == -1)return;
            cur[horse[i]] = end;
            score += map[end].score;
        }
        max = Math.max(max, score);
    }

    static int move(int horse, int dice){
        int temp = cur[horse];
        for(int i = 0 ; i < dice ; i++){
            if (i == 0 && map[temp].isB) {
                temp = map[temp].b;
                continue;
            }
            temp = map[temp].r;
        }
        if (temp <= 40 && check[temp]) {
            return -1;
        }else{
            check[cur[horse]] = false;
            check[temp] = true;
            return temp;
        }
    }


    static void setMap() {
        map[10].isB = true;
        map[20].isB = true;
        map[30].isB = true;
        map[10].b = 11;
        map[20].b = 17;
        map[30].b = 31;
        map[11] = new Node(13, 13);
        map[13] = new Node(16, 15);
        map[15] = new Node(19, 25);
        map[17] = new Node(22, 19);
        map[19] = new Node(24, 25);
        map[25] = new Node(25, 37);
        map[31] = new Node(28, 33);
        map[33] = new Node(27, 35);
        map[35] = new Node(26, 25);
        map[37] = new Node(30, 39);
        map[39] = new Node(35, 40);
        map[42] = new Node(0, 42);
    }

    public static void main(String[] args) throws IOException {
        BOJ17825.solution();
    }
}

//먼저 class를 이용하여 Node라는 class를 생성해주고 해당 class에 점수, 빨간색선, 파란색선, 파란점인지 여부를 저장해준다.
//정해진 순서에 맞게 값을 저장해준다.
//dice[] 배열을 저장하고, horse[] 배열을 새로 생성해 해당 회차에 몇번째 말이 해당 주사위를 이용할지 정한다. -> 브루트포스 알고리즘 사용해 정해준다.
//만약 10개의 주사위를 어떤 말에 쓸지 정했다면, 점수를 계산해준다
//cur[] 배열과 check[] boolean 배열을 생성해준다
//cur배열은 해당 말이 움직이고 나서의 위치를 저장해주기 위한 용도이다
//check 배열은 해당 위치에 말의 유무를 저장하기 위함이다 -> 해당 위치에 말이 있다면 움직일 수 없기 때문
//move함수를 통해 계산한다
//현재 위치를 temp값을 저장한 후,
//dice 값 만큼 움직여 준다
//여기서 움직이기 시작한 위치에서 파란선이 있다면 파란쪽으로 가게하고, 아니면 빨간선으로 가게 해준다.
//이동을 마치고, 해당 말이 도착했거나, 도착한 위치에 말이 이미 있을경우 -1을 return 해주어 점수가 없다는 것을 알려준다.
//위 조건이 아니면 출발한 위치의 check를 false, 도착한 위치를 true로 바꿔주고 도착한 위치를 return 해준다.
//return 받은 값이 -1이 아니면 해당 값을 현재 말의 위치로 저장해주고,
//해당 위치의 점수를 score에 더해준다
//모든 주사위를 다 사용하고난 후 score와 max값을 비교해 최고 값을 갱신해준다.
