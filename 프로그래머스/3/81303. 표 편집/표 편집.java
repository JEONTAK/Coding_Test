import java.util.*;

class Solution {
    
    class Node {
        int data;
        Node prev, next;
        
        public Node() {
            
        }
        
        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
        
        public Node remove() {
            prev.next = next;
            next.prev = prev;
            if(this.next.data == -1) {
                return this.prev;
            }
            return this.next;
        }
        
        public void restore() {
            prev.next = this;
            next.prev = this;
        }
    }
    
    public Node init(int n) {
        Node initNode = new Node(-1);
        Node prevNode = initNode;
        Node curNode = null;
        
        for(int i = 0 ; i < n ; i++) {
            curNode = new Node(i);
            prevNode.next = curNode;
            curNode.prev = prevNode;
            
            prevNode = curNode;
        }
        
        Node endNode = new Node(-1);
        curNode.next = endNode;
        return initNode.next;
    }
    
    private Stack<Node> stack;
    
    public String solution(int n, int k, String[] cmd) {
        Node cursor = init(n);
        stack = new Stack<>();
        
        for(int i = 0 ; i < k ; i++) {
            cursor = cursor.next;
        }
        
        for(int i = 0 ; i < cmd.length; i++) {
            String[] command = cmd[i].split(" ");
            String op = command[0];
            
            if("U".equals(op)) {
                int opNum = Integer.parseInt(command[1]);
                cursor = up(cursor, opNum);
            } else if("D".equals(op)) {
                int opNum = Integer.parseInt(command[1]);
                cursor = down(cursor, opNum);
            } else if("C".equals(op)) {
                cursor = delete(cursor);
            } else {
                restore();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++) {
            sb.append("O");
        }
        
        while(!stack.isEmpty()) {
            sb.setCharAt(stack.pop().data, 'X');
        }
        return sb.toString();
    }
    
    Node up(Node cur, int num) {
        for(int i = 0 ; i < num ; i++) {
            cur = cur.prev;
        }
        
        return cur;
    }
    
    Node down(Node cur, int num) {
        for(int i = 0 ; i < num ; i++) {
            cur = cur.next;
        }
        
        return cur;
    }
    
    Node delete(Node cur) {
        stack.push(cur);
        cur = cur.remove();
        return cur;
    }
    
    void restore() {
        Node node = stack.pop();
        node.restore();
    }
}

/*
시뮬레이션 문제
가지고 있어야 하는것
1. 현재 위치
2. Stack 형식으로 삭제한 내용(행 번호)

U X -> X칸 위의 행 선택
D X -> X칸 밑의 행 선택
C -> Stack에 해당 칸 저장 및 삭제 (현재 위치는 그대로, 그러나 가장 마지막 데이터 삭제한 경우 위치 - 1)
Z -> 복구 현재 선택된 행은 그대로. (즉 현재 위치보다 앞 데이터 복구한다면 현재 위치 + 1, 아니면 그대로)

데이터는 최대 100만개

List형식으로 하기에는 최대 100만개 이므로 무리가 있음
삭제된 인덱스에 X를 넣자
결국 Stack을 사용해야한다는 것은 알겠음
Stack에는 삭제된 인덱스들을 저장
위치를 옮기는 것은 어떻게 구현?
LinkedList를 직접 구현?




*/