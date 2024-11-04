package BaekJoon.Gold2.중앙값구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2696 {

    static int T, M, idx;
    static PriorityQueue<Integer> left, right;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            idx = 1;
            M = Integer.parseInt(br.readLine());
            sb.append((M / 2) + 1).append("\n");
            left = new PriorityQueue<>(Comparator.reverseOrder());
            right = new PriorityQueue<>();
            for(int i = 0 ; i < (M / 10) + 1 ; i++){
                st = new StringTokenizer(br.readLine());
                while(st.hasMoreTokens()){
                    int cur = Integer.parseInt(st.nextToken());
                    if(left.size() == right.size()){
                        left.add(cur);
                    }else{
                        right.add(cur);
                    }
                    if(!left.isEmpty() && !right.isEmpty() && left.peek() > right.peek()){
                        int lt = left.poll();
                        left.add(right.poll());
                        right.add(lt);
                    }
                    if(idx % 2 != 0){
                        sb.append(left.peek() + " ");
                    }
                    idx++;
                    if(idx % 20 == 0){
                        sb.append("\n");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ2696.solution();
    }
}
//이전에 풀었던 BOJ1655 가운데를 말해요와 비슷한 방식으로 풀었다.
//먼저 중앙을 기준으로 좌측과 우측으로 나눠 각각 우선순위 큐로 저장하였다.
//좌측은 내림차순, 우측은 오름차순으로 정렬하였고,
//좌측과 우측의 크기가 같을때(홀수번째 입력)는 좌측에 값을 저장하고, 다를때는 우측에 값을 저장한다.
//값을 저장하면, 좌측에서 값을 뽑고(좌측 큐에서 가장 큰 값), 우측에서 값을 뽑아(우측 큐에서 가장 작은 값) 비교한다.
//두 값을 비교해 좌측값이 더 크다면, SWAP한다.
//이렇게 하면 좌측 큐와 우측 큐를 나열했을 때 오름차순이 되면서, 좌측 큐의 처음 값은 중앙값이 되게 된다.
//idx를 따로 생성하여 1부터 시작해 idx 가 홀수일때 좌측값을 peek()하여 StringBuilder에 저장해주고,
//입력값이 10개 단위로 끊어서 들어오기 때문에, 10개 단위로 끊은 for문을 만들어 반복하였다.
//또한 idx 값이 20번째가 오면 결과값의 줄바꿈을 해야하기때문에, idx % 20 ==0 일 떄 줄바꿈을 해주었다.

