package BaekJoon.Gold2.트리의높이와너비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2250 {

    static class Node{
        int left;
        int right;
        int parent;

        public Node(int left, int right, int parent) {
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    static int N;
    static Node[] node;
    static int[] minWidth;
    static int[] maxWidth;
    static int maxDepth = -1, index = 1;
    static int resultD = -1, resultW = -1;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        minWidth = new int[N + 1];
        maxWidth = new int[N + 1];
        node = new Node[N + 1];

        for (int i = 0; i <= N; i++) {
            minWidth[i] = Integer.MAX_VALUE;
            maxWidth[i] = Integer.MIN_VALUE;
            node[i] = new Node(-1,-1,-1);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            node[c].left = l;
            node[c].right = r;
            if (node[c].left != -1) {
                node[l].parent = c;
            }
            if (node[c].right != -1) {
                node[r].parent = c;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (node[i].parent == -1) {
                compute(i, 1);
                break;
            }
        }

        for (int i = 1; i <= maxDepth; i++) {
            if (resultW < maxWidth[i] - minWidth[i] + 1) {
                resultW = maxWidth[i] - minWidth[i] + 1;
                resultD = i;
            }
        }
        System.out.println(resultD + " " + resultW);
    }
    //중위순회
    static void compute(int idx, int d) {
        Node cur = node[idx];
        maxDepth = Math.max(maxDepth, d);

        //left
        if (cur.left != -1) {
            compute(cur.left, d + 1);
        }

        //mid
        minWidth[d] = Math.min(minWidth[d], index);
        maxWidth[d] = Math.max(maxWidth[d], index++);

        //right
        if(cur.right != -1) {
            compute(cur.right, d + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ2250.solution();
    }
}

//중위순회 방식을 사용하는것이 핵심인 것 같다.
//문제에서 왼쪽, 본인, 오른쪽, 부모 순으로 index가 설정된다.
//이 뜻은 Inorder 순회랑 같은 의미이다.
//따라서 DFS를 사용하여 왼쪽자식이 -1이 아니면 왼쪽을 계산,
//이후 중간부분 계산
//이후 오른쪽 자식이 -1이 아니면 오른쪽 계산한다.
//여기서 높이와 해당 index를 가지고 설정해야한다.
//index 가장 왼쪽부터 차례대로 들어가야한다.
//높이는 d로 두어 해당 compute 과정에서의 현재 높이를 구한다
//minWidth, maxWidth를 배열로 두어, 해당 높이[d]에서의 가장 왼쪽 index, 가장 오른쪽 index를 값으로 넣는다.
//해당 과정은 트리에서 현재 위치한 node에서 하므로, minWidth와 maxWidth를 설정한 후 index값을 ++ 해준다.