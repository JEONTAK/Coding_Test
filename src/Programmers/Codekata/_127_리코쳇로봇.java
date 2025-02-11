package Programmers.Codekata;

public class _127_리코쳇로봇 {

    class Solution {

        int answer = Integer.MAX_VALUE;
        int[][] b;
        int sx, sy, fx, fy, H, W;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int[][] check;

        public int solution(String[] board) {
            H = board.length;
            W = board[0].length();
            b = new int[H][W];
            check = new int[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    check[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (board[i].charAt(j) == 'D') {
                        b[i][j] = 1;
                    } else {
                        b[i][j] = 0;
                        if (board[i].charAt(j) == 'R') {
                            sx = i;
                            sy = j;
                        } else if (board[i].charAt(j) == 'G') {
                            fx = i;
                            fy = j;
                        }
                    }
                }
            }

            dfs(sx, sy, 0);

            return answer == Integer.MAX_VALUE ? -1 : answer;
        }

        private void dfs(int x, int y, int cnt) {
            if (x == fx && y == fy) {
                answer = Math.min(answer, cnt);
                return;
            }

            if (check[x][y] <= cnt) {
                return;
            }

            check[x][y] = cnt;

            for (int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;
                while (isAvailable(nx + dx[i], ny + dy[i])) {
                    nx += dx[i];
                    ny += dy[i];
                }

                if (nx == x && ny == y) {
                    continue;
                }

                dfs(nx, ny, cnt + 1);
            }
        }

        private boolean isAvailable(int x, int y) {
            return x >= 0 && x < H && y >= 0 && y < W && b[x][y] != 1;
        }

    }

}

/*
문제 분석
1. 정보
    - 리코쳇 로봇은 보드게임이다.
    - 격자모양 게임판 위에서 말을 움직이는 게임으로 시작 위치에서 출발한 뒤 목표 위치에 정확하게 멈추기 위해 최소 몇번의 이동이 필요한지 말하는 게임
    - 게임에서 말의 이동은 현재 위치에서 상 하 좌 우 중 한 방향으로 게임판 위의 장애물이나 게임판 가장자리까지 부딪힐 때까지 미끄러져 움직이는 것을 한 번의 이동으로 정의
2. 목표
    - 게임 판의 상태를 나타내는 문자열 배열 board가 주어질 때, 말이 목표 위치에 도달하는데 최소 몇 번 이동해야 하는지 return
3. 제약 조건
    - 3 <= board의 길이 <= 100
    - 3 <= board의 원소의 길이 <= 100
    - 문자열은 ., D, R, G로만 구성
    - . : 빈 공간
    - D : 장애물
    - R : 로봇 시작 위치
    - G : 목표 위치

풀이
1. 아이디어
    - DFS 사용하여 문제 풀이
        - 로봇이 도착 한 위치가 목표지점일 경우
            - 현재 최소 움직임 값과 비교하여 최솟값으로 업데이트
        - 만약 저장한 최소 움직임 값보다 현재 움직임이 크다면 return
        - 아니라면
            - 상 하 좌 우로 로봇을 이동
            - 로봇은 벽을 만나거나 장애물을 만나기 전까지 쭉 이동
            - DFS(이동한 x, 이동한 y, cnt + 1, map) 계산
*/
