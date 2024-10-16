package BaekJoon.Gold2.가운데를말해요;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ1655 {

    static int N;
    static ArrayList<Integer> list = new ArrayList<>();
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    static StringBuilder sb = new StringBuilder();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(minHeap.size() ==  maxHeap.size()) {
                maxHeap.offer(num);
            }else{
                minHeap.offer(num);
            }

            if(!minHeap.isEmpty() && !maxHeap.isEmpty()) {
                if(minHeap.peek() < maxHeap.peek()) {
                    int temp = minHeap.poll();
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(temp);
                }
            }
            sb.append(maxHeap.peek() + "\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BOJ1655.solution();
    }
}

//입력이 주어질 때마다 중간값을 찾아야 한다.
//만약 총 개수가 짝수개일 경우엔 두 중간값중 작은 값을 출력해야 한다.
//해결 방법은 중간값을 기준으로 왼쪽(maxHeap), 오른쪽(minHeap)으로 나누는 것이다!
//maxHeap -> 내림차순으로 정렬, minHeap -> 오름차순으로 정렬
//1. 만약 양 Heap에 개수가 같다면 -> maxHeap에 추가
//1-2.다르다면 minHeap에 추가
//2. 양 Heap이 비어있지 않다면,
//2-1. 만약 오른쪽(minHeap)의 가장 작은 값(뽑은값) 보다 왼쪽(maxHeap)의 가장 큰값(뽑은값)이 작다면, 둘이 SWAP을 해줌
//3. maxHeap에서 peek()한 값이 중간값들중 작은값이 자동으로 됨.
