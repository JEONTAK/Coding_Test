package Programmers.Codekata;

import java.util.LinkedList;
import java.util.Queue;

public class _124_미로탈출 {

    class Solution {

        class Node {
            int x;
            int y;
            int d;

            public Node(int x, int y, int d) {
                this.x = x;
                this.y = y;
                this.d = d;
            }
        }

        int W;
        int H;
        int first = -1;
        int last = -1;
        Node start;
        Node lever;
        Node end;
        boolean[][] visited;
        char[][] map;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        public int solution(String[] maps) {

            W = maps[0].length();
            H = maps.length;
            map = new char[H][W];

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (maps[i].charAt(j) == 'S') {
                        start = new Node(i, j, 0);
                    } else if (maps[i].charAt(j) == 'E') {
                        end = new Node(i, j, 0);
                    } else if (maps[i].charAt(j) == 'L') {
                        lever = new Node(i, j, 0);
                    }
                    map[i][j] = maps[i].charAt(j);
                }
            }

            first = BFS(start, lever);
            if (first == -1) {
                return first;
            }

            last = BFS(lever, end);
            if (last == -1) {
                return last;
            }

            return first + last;
        }

        private int BFS(Node start, Node end) {
            int result = Integer.MAX_VALUE;
            visited = new boolean[H][W];
            Queue<Node> q = new LinkedList<>();
            q.add(start);
            visited[start.x][start.y] = true;
            while (!q.isEmpty()) {
                Node cur = q.poll();
                if (cur.x == end.x && cur.y == end.y) {
                    result = Math.min(result, cur.d);
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (isAvailable(nx, ny) && !visited[nx][ny] && map[nx][ny] != 'X'){
                        visited[nx][ny] = true;
                        q.add(new Node(nx, ny, cur.d + 1));
                    }
                }
            }

            if (result == Integer.MAX_VALUE) {
                return -1;
            }
            return result;
        }

        private boolean isAvailable(int x, int y) {
            return x >= 0 && x < H && y >= 0 && y < W;
        }
    }

}

/*
문제 분석
1. 정보
    - 1 X 1 크기의 칸들로 이루어진 직사각형 격자 형태의 미로에서 탈출하려고 함.
    - 각 칸은 통로 또는 벽으로 구성되어 있고, 벽으로 된 칸은 지나갈 수 없고 통로로 된 칸만 이동 가능
    - 통로 중 한 칸에는 미로를 빠져나가는 문이 존재하는데, 해당 문은 레버를 당겨서만 열 수 있음
    - 레버 또한 통로 중 한 칸에 존재
    - 따라서 출발 지점에서 먼저 레버가 있는 칸으로 이동하여 레버를 당긴 후, 미로를 빠져나가는 문이 있는 칸으로 이동
2. 목표
    - 미로에서 한 칸을 이동하는데 1초가 걸린다고 할 때, 최대한 빠르게 미로를 빠져나가는데 걸리는 시간 return
    - 만약 빠져나갈 수 없다면 -1 return
3. 제약 조건
    - 5 <= 지도 세로 <= 100
    - 5 <= 지도 가로 <= 100
    - 지도에는 다음 문자로만 이루어짐
        - S : 시작 지점
        - E : 출구
        - L : 레버
        - O : 통로
        - X : 벽

풀이
1. 아이디어
    - 주어진 배열을 돌며 시작, 레버, 도착 위치를 구하여 해당 좌표를 저장
    - 1. 시작 지점에서 레버까지 가는데 걸리는 최소 시간 구함
        - 만약 값이 업데이트 되지 않고 -1이면, -1 return
    - 2. 레버에서 도착 지점까지 가는데 걸리는 최소 시간 구함
        - 만약 값이 업데이트 되지 않고 -1이면, -1 return
    - BFS 알고리즘 사용하여 출발지점에서 도착지점까지 가는데 걸리는 최소 시간 구함
*/
