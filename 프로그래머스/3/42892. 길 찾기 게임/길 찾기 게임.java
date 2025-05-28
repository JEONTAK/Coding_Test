import java.util.*;

class Solution {
    
    class Node{
        int idx;
        int x;
        int y;
        Node left;
        Node right;
        
        public Node(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.left = null;
            this.right = null;
        }
        
        public void insert(Node node) {
            if(x > node.x) {
                if(left == null) {
                    left = new Node(node.idx, node.x, node.y);
                } else {
                    left.insert(node);
                }
            } else {
                if(right == null) {
                    right = new Node(node.idx, node.x, node.y);
                } else {
                    right.insert(node);
                }
            }
        }

    }
    
    List<Integer> list;
    
    public int[][] solution(int[][] nodeinfo) {
        //노드Info 데이터에 idx 넣어 생성 및 정렬
        int[][] nodes = new int[nodeinfo.length][3];
        for(int i = 0; i < nodeinfo.length; i++) {
            nodes[i][0] = nodeinfo[i][0];
            nodes[i][1] = nodeinfo[i][1];
            nodes[i][2] = (i + 1);
        }
        
        Arrays.sort(nodes, (o1,o2) -> {
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o2[1] - o1[1];
        });
        
        //root node 설정
        Node root = new Node(nodes[0][2], nodes[0][0], nodes[0][1]);
        
        //node 삽입
        for(int i = 1; i < nodes.length; i++) {
            int x = nodes[i][0];
            int y = nodes[i][1];
            int idx = nodes[i][2];
            Node curNode = new Node(idx,x,y);
            root.insert(curNode);
        }
        
        list = new ArrayList<>();
        int[][] answer = new int[2][nodeinfo.length];
        preOrder(root);
        for(int i = 0 ; i < list.size(); i++) {
            answer[0][i] = list.get(i);
        }
        list = new ArrayList<>();
        postOrder(root);
        for(int i = 0 ; i < list.size(); i++) {
            answer[1][i] = list.get(i);
        }
        return answer;
    }
    
    public void preOrder(Node node) {
        if(node == null) return;
        list.add(node.idx);
        preOrder(node.left);
        preOrder(node.right);  
    }
    
    public void postOrder(Node node) {
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right); 
        list.add(node.idx);
    }
}



/*
트리 구조를 만들고, 해당 트리를 사용해서 전위 순회, 후위 순회한 결과를 리턴
먼저 nodeinfo를 y값 내림차순, x값 오름차순으로 정렬(idx도 추가해서)

*/