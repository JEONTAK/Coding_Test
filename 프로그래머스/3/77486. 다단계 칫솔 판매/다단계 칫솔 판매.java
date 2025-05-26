import java.util.*;

class Solution {
    
    private final int PRICE = 100;
    
    class Node {
        int idx;
        int price;
        
        public Node (int idx, int price) {
            this.idx = idx;
            this.price = price;
        }
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        //각 구성원 인덱스 저장
        Map<String, Integer> map = new HashMap<>();
        map.put("-", 0);
        for(int i = 0; i < enroll.length; i++) {
            map.put(enroll[i], i + 1);
        }
        
        int[] parent = new int[enroll.length + 1];
        
        //인덱스 사용하여 그래프 형태로 저장
        for(int i = 0; i < enroll.length; i++) {
            String name = enroll[i];
            String refer = referral[i];
            parent[map.get(name)] = map.get(refer);
        }
        
        int[] answer = new int[enroll.length];
        //판 사람과 판 개수를 사용하여 수익 배분하기.
        for(int i = 0; i < seller.length; i++) {
            //판매 구성원 번호
            int sellerIdx = map.get(seller[i]);
            //해당 구성원이 판 총 매출
            int totalPrice = PRICE * amount[i];
            
            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(sellerIdx, totalPrice));
            
            //center까지 올라가면서 판매 수익 배분
            while(!queue.isEmpty()) {
                Node cur = queue.poll();
                
                if(cur.idx == 0) continue;
                
                //배분 할 금액
                int givePrice = cur.price / 10;
                
                //현재 구성원의 금액에 (현재 금액 - 배분 할 금액) 누적 저장
                answer[cur.idx - 1] += (cur.price - givePrice);
                
                //배분 할 금액이 0이면 더이상 위로 안올라가도 됨
                if(givePrice == 0) continue;
                
                //추천 구성원에게 배분 할 금액 전달
                queue.add(new Node(parent[cur.idx], givePrice));
            }
        }
        
        return answer;
    }
}


/*
데이터 정리를 어떻게 하냐가 중요할듯?
Map으로 구성원의 번호를 설정
List[]로 그래프 형식으로 추천인 연결
시간 초과 발생
어차피 한사람당 추천인은 한명이니 리스트 처리를 할 필요가 없을 것 같음.
-> int parent로 처리
그래도 시간 초과 발생




*/