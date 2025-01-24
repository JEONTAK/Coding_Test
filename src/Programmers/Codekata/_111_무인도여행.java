package Programmers.Codekata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _111_무인도여행 {

    class Solution {

        class Node {
            int x;
            int y;

            public Node(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        public int[] solution(String[] maps) {
            List<Integer> result = new ArrayList<>();
            boolean[][] visited = new boolean[maps.length][maps[0].length()];

            for (int i = 0; i < maps.length; i++) {
                for (int j = 0; j < maps[i].length(); j++) {
                    if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                        int sum = 0;
                        Queue<Node> q = new LinkedList<>();
                        q.add(new Node(i, j));
                        visited[i][j] = true;
                        sum += maps[i].charAt(j) - '0';

                        while (!q.isEmpty()) {
                            Node cur = q.poll();
                            for (int k = 0; k < 4; k++) {
                                int nx = cur.x + dx[k];
                                int ny = cur.y + dy[k];

                                if (isAvailable(nx, ny, maps) && maps[nx].charAt(ny) != 'X' && !visited[nx][ny]) {
                                    q.add(new Node(nx, ny));
                                    visited[nx][ny] = true;
                                    sum += maps[nx].charAt(ny) - '0';
                                }
                            }
                        }
                        result.add(sum);
                    }
                }
            }

            if (result.isEmpty()) {
                return new int[]{-1};
            }
            int[] answer = result.stream().mapToInt(integer -> integer).toArray();
            Arrays.sort(answer);
            return answer;
        }

        private boolean isAvailable(int x, int y, String[] maps) {
            return 0 <= x && x < maps.length && 0 <= y && y < maps[0].length();
        }
    }
}

/*
문제 분석
1. 정보
    - 무인도로 여행을 가기 위해 지도를 보고 있다.
    - 지도에는 바다와 무인도들에 대한 정보가 표시되어 있따
        - X는 바다를 나타내며, 숫자는 무인도를 나타냄
        - 무인도에서 상 하 좌 우로 연결되는 땅들은 하나의 무인도를 이룬다
    - 지도의 각 칸에 적힌 숫자는 식량을 나타내는데, 상 하 좌 우로 연결되는 칸에 적힌 숫자를 모두 합한 값은 해당 무인도에서 최대 며칠동안 머물 수 있는지를 나타낸다 
2. 목표
    - 지도를 나타내는 문자열 배열 maps가 매개변수로 주어질 때, 각 섬에서 최대 며칠씩 머무를 수 있는지 배열에 오름차순으로 담아 return
3. 제약 조건
    - 3 <= maps의 길이 <= 100
        - 3 <= maps[i]의 길이 <= 100
        - 지도는 직사각형 형태

풀이
1. 아이디어
    - 방문 체크 및 BFS 활용
    - 지도의 크기에 맞게 boolean 배열 선언
        - 해당 지점에 방문하였으면 true
        - 방문한 적이 있다면 해당 지점은 지나감
    - 지도의 0,0 부터 마지막까지 순차적으로 탐색
        - 해당 지점이 방문한적이 없는 섬이라면
            - 지도의 x,y값을 담은 class 생성
            - dx = 0,1,0,-1
            - dy = 1,0,-1,0
            - dx와 dy를 차례대로 사용하여 상 하 좌 우 이동할 수 있게 구현
            - 만약 해당 지점이 바다이거나, 이미 들린적이 있다면, 지나감
            - 만약 해당 지점이 들린적 없는 섬이라면 전체 합산에 해당 지점 식량 더해줌
        - 하나의 무인도를 모두 방문하였을 경우, 식량 합산을 List에 저장
     - 모든 탐색을 마치고, List를 정렬한 후 배열에 담아 return
        - 만약 list가 비어있다면 -1을 담아 return
*/
