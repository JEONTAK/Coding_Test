import java.util.*;

class Solution {
    
    class Dart implements Comparable<Dart> {
        int score;
        int throwCnt;
        int hitCnt;
        
        public Dart (int score, int throwCnt, int hitCnt) {
            this.score = score;
            this.throwCnt = throwCnt;
            this.hitCnt = hitCnt;
        }
        
        @Override
        public int compareTo(Dart o) {
            if(this.throwCnt == o.throwCnt) return o.hitCnt - this.hitCnt;
            return this.throwCnt - o.throwCnt;
        }
    }
    
    public int[] solution(int target) {
        int[] answer = new int[2];
        PriorityQueue<Dart> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[target + 1];
        pq.add(new Dart(0,0,0));
        while(!pq.isEmpty()) {
            Dart cur = pq.poll();
            
            if(cur.score == target) {
                answer[0] = cur.throwCnt;
                answer[1] = cur.hitCnt;
                break;
            }
            
            if(visited[cur.score]) continue;
            visited[cur.score] = true;
            
            for(int i = 1 ; i <= 20 ; i++) {
                for(int j = 1 ; j <= 3 ; j++) {
                    int score = cur.score + i * j;
                    if(score <= target) {
                        if(j == 1) {
                            pq.add(new Dart(score, cur.throwCnt + 1, cur.hitCnt + 1));
                        } else {
                            pq.add(new Dart(score, cur.throwCnt + 1, cur.hitCnt));
                        }
                    }
                }
            }
            
            int score = cur.score + 50;
            if(score <= target) {
                pq.add(new Dart(score, cur.throwCnt + 1, cur.hitCnt + 1));
            }
        }
        
        return answer;
    }
}

/*
점수는 1 ~ 20점 까지 존재
또한 X2, X3이 존재
마지막 불 -> 50점이 존재
점수 -> 최대 60점 최소 1점

해당 점수들을 조합하여 최소한의 횟수로 만들기
만약 최소한의 횟수가 같다면, (싱글 + 불)을 더 많이 맞춘 사람이 이김


1. PriorityQueue 사용
Dart(0,0,0)으로 생성
for(int i = 1 ; i <= 20 ; i++) {
    for(int j = 1 ; j <= 3 ; j++) {
        각 점수를 추가해서 PQ에 저장.
        이러면 점수도 Dart에 추가
    }
}
bull은 따로 점수로 추가
만약 target 점수에 도달했다면, 해당 결과를 return

2. dp사용?
일단 Dart로 구하기
Dart -> throwCnt(던진 횟수), hitCount(불 + 싱글 맞춘 횟수) 저장

dp[i] = i -> 점수
dp = Dart[i]; 로 만들기

최대 점수는 10만
10만이 넘어가는 경우는 없음


*/