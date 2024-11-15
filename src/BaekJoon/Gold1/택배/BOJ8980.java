package BaekJoon.Gold1.택배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ8980 {

    static int N, C, M;
    static int[][] box;
    static int[] cap;
    static int result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        box = new int[M][3];
        cap = new int[N + 1];
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 3 ; j++){
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.fill(cap, C);
        Arrays.sort(box, (o1, o2) -> {
            if(o2[1] == o1[1]){
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        for (int[] r : box) {
            int move = r[2];
            for(int i = r[0] ; i < r[1] ; i++){
                if(move == 0)break;
                if (cap[i] - r[2] >= 0) {
                    move = Math.min(move, r[2]);
                }else{
                    move = Math.min(move, cap[i]);
                }
            }
            if(move == 0){
                continue;
            }
            for(int i = r[0] ; i < r[1] ; i++){
                cap[i] -= move;
            }
            result += move;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ8980.solution();
    }
}

//전형적인 그리디 알고리즘 및 정렬 문제였다.
//정렬은 도착지점 오름차순, 시작지점 오름차순으로 정렬해준다.
//cap이라는 int 배열을 만들어 C 값을 넣어주고 해당 배열은 각 마을에서 실을 수 있는 택배의 개수를 나타내도록 한다.
//정렬 한 후, 모든 box에 대하여 다음과 같이 계산한다.
//시작 지점부터 도착지점 까지 cap 배열과 비교해 실을 수 있는 최대 값 move를 구한다.
//만약 해당 값이 0이라면 실을 수 없다는 뜻이므로 넘어가주고, 0이 아니라면 해당 모든 지점의 cap에 대하여 move를 빼준다.
//결과 값에 실은 택배 개수인 move를 더해준다.
//모두 계산하고 나서 result 값을 출력해준다.