import java.util.*;

class Solution {
    
    class Node implements Comparable<Node>{
        int end;
        int intensity;
        
        public Node (int end, int intensity) {
            this.end = end;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.intensity - o.intensity;
        }
    }
    
    private List<Node>[] list;
    private int[] state;
    private int[] intensitys;
    private int minSummit = 50_001;
    private int minIntensity = 10_000_001;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        list = new ArrayList[n + 1];
        for(int i = 1 ; i <= n ; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int[] path : paths) {
            int i = path[0];
            int j = path[1];
            int w = path[2];
            list[i].add(new Node(j, w));
            list[j].add(new Node(i, w));
        }
        
        //게이트, 산봉우리 Set 생성
        Set<Integer> gateSet = new HashSet<>();
        Set<Integer> summitSet = new HashSet<>();
        
        for(int gate : gates) {
            gateSet.add(gate);
        }
        
        for(int summit : summits) {
            summitSet.add(summit);
        }
        
        intensitys = new int[n + 1];
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for(int gate : gates) {
            pq.add(new Node(gate, 0));
            intensitys[gate] = 0;
        }
        
        boolean[] visited = new boolean[n + 1];
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(summitSet.contains(cur.end)) {
                if(cur.intensity < minIntensity) {
                    minIntensity = cur.intensity;
                    minSummit = cur.end;
                } else if(cur.intensity == minIntensity && cur.end < minSummit) {
                    minSummit = cur.end;
                }
                continue;
            }
            
            if(visited[cur.end]) continue;
            visited[cur.end] = true;
                      
            for(Node next : list[cur.end]) {
                if(gateSet.contains(next.end)) continue;
                
                if(cur.intensity < next.intensity) {
                    intensitys[next.end] = next.intensity;
                } else {
                    intensitys[next.end] = cur.intensity;
                }
                pq.add(new Node(next.end, intensitys[next.end]));
            }
        }
        
        return new int[] {minSummit, minIntensity};
    }
}

/*
들어온 paths들을 먼저 List<Node>[] 형태로 저장
int[] state를 통해 해당 지점의 상태값을 저장(0 : 쉼터, 1 : 게이트, 2 : 산봉우리)

gate -> summit의 모든 가짓수를 계산 (PriorityQueue 사용)
-> 메모리 초과 및 시간 초과 발생
-> 이유 : 최대 25000 * 25000 -> 약 6억개의 연산이 필요함.

다른 방법이 필요..
int[][] intensity 배열을 만들어 [i][j]까지 가는데 필요한 최대 intensity 값을 저장함.
물론 지나가는 길에 gate나 summit이 있는 경우 -1로 저장.

저장 이후 minSummit, minIntensity 값을 찾아 return
-> 시간 초과 발생
*/