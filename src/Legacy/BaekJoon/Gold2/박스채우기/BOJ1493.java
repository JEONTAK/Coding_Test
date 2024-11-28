package BaekJoon.Gold2.박스채우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1493 {

    static int L, W, H, N;
    static int[] cube;


    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        cube =  new int[N];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            cube[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        long result = compute(N - 1, 0, 0);
        System.out.println(result);
    }

    static long compute(int size, long fill, long cnt){
        long totalSize = ((long)(L >> size) * (W >> size) * (H >> size)) - fill;
        long fillCube = Math.min(cube[size], totalSize);
        if (size == 0) {
            if (fill + fillCube != (long) L * H * W) {
                return -1;
            }
            return cnt + fillCube;
        }else{
            return compute(size - 1, fill + fillCube << 3, cnt + fillCube);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ1493.solution();
    }
}

//크기가 가장 큰 것부터 차례대로 최대한 많이 넣어 결과값을 구하였다.
//compute에는 (크기, 채워진 큐브 개수, 누적 큐브 개수)순으로 되어있다.
//먼저 가용가능한 전체 크기를 구한다. -> L 에서 2의 size제곱 만큼 나눈것, W 에서 2의 size제곱 만큼 나눈것, H 에서 2의 size제곱 만큼 나눈것을 모두 곱하면 해당 큐브 크기가 들어갈 수 있는 개수가 나오고,
//이미 채워진 큐브의 개수(현재 크기일 경우의 큐브의 개수로 값이 주어진다)를 빼면, 우리가 해당 큐브로 채울수 있는 최대 개수를 얻을 수 있다.
//fillCube라는 변수를 생성해 우리가 가지고 있는 큐브와, 채울 수 있는 최대 개수증 작은 것을 선택하고,
//size가 최소가 될때까지 compute(크기 - 1, 이전 채워진 큐브 개수 + 현재 채운 큐브 개수 * 8(크기가 1 작아지면 개수는 3차원이므로 2 * 2 * 2 -> 8이 작아진다), 누적 큐브 개수 + 현재 채운 큐브 개수)를 계산한다.
//size가 최소가 되었다면,
//즉 1 * 1 * 1 큐브를 채우는 계산을 한 이후라면 -> 지금 까지 채워진 큐브의 개수 + fillCube -> 1 * 1 * 1로 채울 경우 채운 개수이다
//해당 개수를 L * W * H 과 비교하여 크기가 같다면 다채운것이므로,
//크기가 다르다면 -1을 return,
//같다면 누적 큐브 개수 + 채운 개수를 return 해준다.
