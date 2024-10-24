package BaekJoon.Gold2.컬러볼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10800 {

    static class Ball {
        int idx;
        int color;
        int size;

        public Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }

    static int N;
    static int[] colorSum, result;
    static int totalSum = 0;
    static Ball[] list;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        list = new Ball[N];
        result = new int[N];
        colorSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            list[i] = new Ball(i, color, size);
        }

        Arrays.sort(list, (o1, o2) -> o1.size - o2.size);
        int idx = 0;
        for(int i = 0 ; i < N ; i++){
            Ball cur = list[i];
            while (list[idx].size < cur.size) {
                totalSum += list[idx].size;
                colorSum[list[idx].color] += list[idx].size;
                idx++;
            }
            result[cur.idx] = totalSum - colorSum[cur.color];
        }

        for(int i = 0 ; i < N ; i++){
            System.out.println(result[i]);
        }
    }
    public static void main(String[] args) throws IOException {
        BOJ10800.solution();
    }
}

//처음 시도한 방법은 Ball 객체를 만들어 값을 저장한 후, size 오름차순으로 정렬한뒤, 각각의 컬러의 합과, 전체 합을 누적합으로 구하여
//전체에서 해당 컬러 누적합을 빼는 방식으로 계산하였다.
//하지만 이렇게하면 크기가 같고 색깔이 같은경우에 잡아 먹힐 수 없는데 잡아먹혀버리는 코드로 작동이 되어버린다.
//따라서 사이즈를 기준으로 계산하는 방식으로 바꾸어야 할듯
//바꾼 방식은 다음과 같다.
//size 오름차순으로 정렬하는 것 까지는 동일하다.
//여기서 사이즈가 작은 것부터 차례대로 계산한다.
//현재 공보다 사이즈가 작은 것들을 다 뽑을때까지 totalSum에 크기들을 더해주고, colorSum에는 해당 색깔에 맞게 더해준다.
//작은 것들을 다 뽑았다면, 전체 누적합인 totalSum에서 본인의 색깔의 colorSum을 빼준 값이 해당 idx의 결과값이 나오게 된다.