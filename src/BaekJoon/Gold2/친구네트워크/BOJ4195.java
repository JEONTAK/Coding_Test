package BaekJoon.Gold2.친구네트워크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ4195 {

    static int T, F;
    static int[] parent;
    static int[] size;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int z = 0; z < T; z++) {
            F = Integer.parseInt(br.readLine());
            parent = new int[F * 2];
            size = new int[F * 2];
            for (int i = 0; i < F * 2; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            int idx = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if(!map.containsKey(a)) {
                    map.put(a, idx++);
                }
                if(!map.containsKey(b)) {
                    map.put(b, idx++);
                }

                sb.append(union(map.get(a), map.get(b)) + "\n");
            }
        }
        System.out.println(sb.toString());
    }

    static int find(int x){
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b){
            parent[b] = a;
            size[a] += size[b];
        }
        return size[a];
    }

    public static void main(String[] args) throws IOException {
        BOJ4195.solution();
    }
}

//먼저 기본 개념은 union과 find를 사용하는것.
//Union과 find를 사용함으로써 집합을 만들 수 있음.
//해당 부분에서 추가해야할 것은 해당 집합의 크기이다. -> 우리는 해당 집합의 크기를 알아야 하기 때문에!
//따라서 int[] size라는 배열을 따로 만들어 해당 인덱스가 포함된 집합의 크기를 저장한다.
//기존 union 코드 부분에서 void형식에서 int형식으로 바꾸어주고, union할 a와 b의 부모를 구한다음,  만약 부모가 다르다면, b의 부모를 a로 설정하고
//a의 집합의 크기에 b의 집합의 크기를 더해준다.
//여기서 중요한점은 사람이 index형식으로 들어오는 것이 아닌 이름으로 들어오게 된다.
//따라서 Map<String, Integer>형식의 HashMap을 만들어 만약 HashMap에 해당 사람의 이름이 없다면 map에 저장해 놓는다.
//여기서 친구 관계의 수 F에 곱하기 2를 해주는 이유는 친구 관계당 2명씩 들어있는데, 다 다른 사람이라면 총 사람의 개수가 F * 2개가 나올 수 있기 때문!

