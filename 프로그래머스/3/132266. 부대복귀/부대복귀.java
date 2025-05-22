import java.util.*;

class Solution {
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int[] road : roads) {
            int start = road[0];
            int end = road[1];
            list[start].add(end);
            list[end].add(start);
        }
        
        int[] dist = new int[n + 1];
        
        Arrays.fill(dist, 200_001);
        dist[destination] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next : list[cur]) {
                if(dist[next] > dist[cur] + 1) {
                    dist[next] = dist[cur] + 1;
                    queue.add(next);
                }
            }
        }
        
        int[] answer = new int[sources.length];
        
        for(int i = 0; i < answer.length; i++) {
            if(dist[sources[i]] == 200_001) {
                answer[i] = -1;
            } else{
                answer[i] = dist[sources[i]];
            }
        }
        
        return answer;
    }
}

/*

List<Integer>[]로 길 저장

dist[i][j]로 i -> j까지 가는데 걸리는 최소 시간 구함
근데 n이 최대 10만 -> n * n = 10,000,000,000 100억 -> 메모리 초과 날듯?
결국 destination이 주어지니까
destination을 시작으로 하고 dist[i] -> 시작 점에서 i지점 까지 가는 최소 비용으로 구하기.
지역은 최대 10만개이므로, 최댓값 200001로 설정

result에 [source][destination] 값 넣기
만약 값이 업데이트 되지 않았다면, -1 값 저장.

*/