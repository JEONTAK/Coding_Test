package BaekJoon.Gold2.색종이붙이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17136 {

    static final int N = 10;
    static int[][] map = new int[N][N];
    static int[] paper = new int[6];
    static int result = Integer.MAX_VALUE;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.fill(paper, 5);

        compute(0,0,0);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    static void compute(int x, int y, int cnt){
        if(x == 9 && y == 10){
            result = Math.min(result, cnt);
            return;
        }

        if(y == 10){
            compute(x + 1, 0, cnt);
            return;
        }

        if(result <= cnt)return;

        if(map[x][y] == 1){
            for(int i = 5 ; i >= 1 ; i--){
                if(paper[i] > 0 && isAvailable(x,y,i)){
                    setPaper(x, y, i, 0);
                    paper[i]--;
                    compute(x, y + 1, cnt + 1);
                    setPaper(x, y, i, 1);
                    paper[i]++;
                }
            }
        }else{
            compute(x, y + 1, cnt);
        }
    }

    static boolean isAvailable(int x, int y, int s) {
        if(x + s  >= 11 || y + s >= 11)return false;

        for(int i = x ; i < x + s ; i++){
            for(int j = y ; j < y + s ; j++){
                if(map[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    static void setPaper(int x, int y, int s, int v){
        for(int i = x ; i < x + s ; i++){
            for(int j = y ; j < y + s ; j++){
                map[i][j] = v;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ17136.solution();
    }
}

//처음에는 그리디 알고리즘을 사용하여 크기가 5인 색종이부터 차례대로 넣어 최솟값을 구하고자 하였다.
//하지만 수많은 반례가 있는데, 그 중 하나를 말해보자면,
//0 1 1 1 1 1 1 1 1 0
//0 1 1 1 1 1 1 1 1 0
//0 1 1 1 1 1 1 1 1 0
//0 0 0 0 1 1 0 0 0 0
//이 주어졌다고 가정하자.
//그리디 알고리즘을 사용하면 다음과 같이 변화하게 될것이다.
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 1 1 0 0 0 0
//1
//0 0 0 0 0 0 0 1 1 0
//0 0 0 0 0 0 0 1 1 0
//0 0 0 0 0 0 0 1 1 0
//0 0 0 0 1 1 0 0 0 0
//2
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 1 1 0
//0 0 0 0 1 1 0 0 0 0
//3 이후 1칸 색종이 4개를 제거해 7이란 결과가 나온다.
//하지만
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 1 1 0 0 0 0
//1
//0 0 0 0 0 0 1 1 1 0
//0 0 0 0 0 0 1 1 1 0
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 1 1 0 0 0 0
//2
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 1 1 0 0 0 0
//0 0 0 0 1 1 0 0 0 0
//3
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0
//4
//4개로 최솟값이 나온다.
//따라서 해당 문제는 그리디 알고리즘이 아닌, (0,0)부터 ~ (9,9)까지 크기가 큰 색종이부터 차례대로 넣어, 들어갈 수 있으면 색종이를 넣고,
//DFS를 사용하여 다음 좌표에서 똑같이 계산한다.
//이후 색종이를 사용하여 채우지 못한다면 다시 백트래킹 하여 색종이를 제거한 후 다음으로 크기가 작은 색종이를 넣는다.
//좌표 처음부터 끝까지 차례대로 반복하여 최솟값을 구하면 된다.
