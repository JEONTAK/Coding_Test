package Programmers.Codekata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _142_빛의경로사이클 {

    class Solution {

        List<Integer> ans = new ArrayList<>();
        int x = 0;
        int y = 0;
        boolean[][][] visited;
        // 상 우 하 좌 순서
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        public int[] solution(String[] grid) {
            x = grid.length;
            y = grid[0].length();
            visited = new boolean[x][y][4];

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (!visited[i][j][k]) {
                            compute(grid, i, j, k);
                        }
                    }
                }
            }

            int[] answer = new int[ans.size()];
            for (int i = 0; i < ans.size(); i++) {
                answer[i] = ans.get(i);
            }
            Arrays.sort(answer);
            return answer;
        }

        private void compute(String[] grid, int sx, int sy, int k) {
            int rows = grid.length;
            int cols = grid[0].length();
            int x = sx;
            int y = sy;
            int dir = k;
            int length = 0;

            while (!visited[x][y][dir]) {
                visited[x][y][dir] = true;
                length++;

                char command = grid[x].charAt(y);
                if (command == 'L') {
                    dir = (dir + 3) % 4;
                } else if (command == 'R') {
                    dir = (dir + 1) % 4;
                }

                x = (x + dx[dir] + rows) % rows;
                y = (y + dy[dir] + cols) % cols;

                // 시작점으로 돌아오면 사이클 완성
                if (x == sx && y == sy && dir == k) {
                    if (length > 0) {
                        ans.add(length);
                        return;
                    }
                }
            }
        }
    }

}

/*
문제 분석
1. 정보
    - 각 칸마다 S, L 또는 R이 써져 있는 격자가 있음. 격자에서 빛을 쏘고자 하는데, 격자의 각 칸에는 다음과 같은 특이한 성질이 존재.
        - 빛이 "S"가 써진 칸에 도달한 경우, 직진
        - 빛이 "L"이 써진 칸에 도달한 경우, 좌회전
        - 빛이 "R"이 써진 칸에 도달한 경우, 우회전
        - 빛이 격자의 끝을 넘어갈 경우, 반대쪽 끝으로 다시 돌아옴
            - 빛이 1행에서 행이 줄어드는 방향으로 이동할 경우, 같은 열의 반대쪽 끝 행으로 다시 돌아옴
    - 이 격자 내에서 빛이 이동할 수 있는 경로 사이클이 몇 개 있고, 각 사이클의 길이가 얼마인지 알고 싶음. 경로 사이클이란, 빛이 이동하는 순환 경로를 의미.

2. 목표
    - 격자의 정보를 나타내는 1차원 문자열 배열 grid가 매개변수로 주어질 때, 주어진 격자를 통해 만들어지는 빛의 경로 사이클의 모든 길이들을 배열에 담아 오름차순으로 정렬하여 return
3. 제약 조건
    - 1 ≤ grid의 길이 ≤ 500
        - 1 ≤ grid의 각 문자열의 길이 ≤ 500
        - grid의 모든 문자열의 길이는 서로 같음
        - grid의 모든 문자열은 'L', 'R', 'S'로 이루어져 있음.

풀이
1. 아이디어
    - 사이클을 완성하기 위해선, 시작점 = 도착점 뿐만 아니라, 시작점에서의 출발 방향 = 도착점 도착 이후의 출발 방향 이어야 함.
    - 또한 한 빛에서 이미 존재하는 사이클을 통과할 경우, 해당 사이클은 중복이기 때문에 제외해야 함.
    - 따라서 각 빛마다 [4]배열을 만들어 상, 하, 좌, 우 방향으로 빛이 통과했는지 여부를 저장하여 중복을 방지해야 할듯.
    - 길이는 최대 500 * 500 이므로 250000 * 4 = 100만임
        - 3중 for문을 사용하여 모든 경우의 수를 탐색
            - 이미 지난(visited[][][] 가 true라면) 건너 뜀
            - 지나지 않았다면, 해당 방향으로 부터 시작. (시작한 위치와 방향은 저장)
                - 사이클을 돌다가 visited가 true인곳 지나면 바로 return
                - 시작점과 시작 방향을 지난다면 cnt를 배열에 저장하고 return
    - 모든 경우의 수를 구하였다면 answer 배열 오름차순 정렬 후 return
*/