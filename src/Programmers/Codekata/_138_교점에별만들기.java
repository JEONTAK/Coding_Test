package Programmers.Codekata;

import java.util.Arrays;
import java.util.HashSet;

public class _138_교점에별만들기 {

    class Solution {

        class Point {
            long x;
            long y;

            public Point(long x, long y) {
                this.x = x;
                this.y = y;
            }
        }

        HashSet<Point> points = new HashSet<>();
        long minX = Long.MAX_VALUE, minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE;

        public String[] solution(int[][] line) {
            for (int i = 0; i < line.length - 1; i++) {
                for (int j = i + 1; j < line.length; j++) {
                    long under = (long) line[i][0] * line[j][1] - (long) line[j][0] * line[i][1];
                    if (under == 0) {
                        continue;
                    }

                    long upX = (long) line[i][1] * line[j][2] - (long) line[j][1] * line[i][2];
                    long upY = (long) line[i][2] * line[j][0] - (long) line[j][2] * line[i][0];

                    if (upX % under == 0 && upY % under == 0) {
                        long x = upX / under;
                        long y = upY / under;
                        points.add(new Point(x, y));

                        minX = Math.min(minX, x);
                        minY = Math.min(minY, y);
                        maxX = Math.max(maxX, x);
                        maxY = Math.max(maxY, y);
                    }
                }
            }
            int width = (int)(maxX - minX + 1);
            int height = (int)(maxY - minY + 1);

            char[][] grid = new char[height][width];

            for (char[] row : grid) {
                Arrays.fill(row,'.');
            }
            
            for (Point p : points) {
                int row = (int)(maxY - p.y);
                int col = (int)(p.x - minX);
                grid[row][col] = '*';
            }

            String[] answer = new String[height];
            for (int i = 0; i < height; i++) {
                answer[i] = new String(grid[i]);
            }

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - Ax + By + C = 0으로 표현할 수 있는 n개의 직선이 주어질 때, 이 직선의 교점 중 정수 좌표의 별을 그리려 함

2. 목표
    - 직선 A, B, C에 대한 정보가 담긴 배열 line이 매개변수로 주어질 때, 모든 별을 포함하는 최소 사각형을 return
3. 제약 조건
    - line의 세로 행 길이는 2 이상 1000 이하
        - line의 가로 길이는 3
        - 각 원소는 A, B, C 형태
        - A, B, C는 -100000 이상 100000 이하 정수
        - 무수히 많은 교점이 생기는 직선 쌍은 주어지지 않음
        - A = 0 이면서 B = 0인 경우는 주어지지 않음
    - 정답은 1000 * 1000 크기 이내에서 표현됨
    - 별이 한개 이상 그려지는 입력만 주어짐


풀이
1. 아이디어
    - 직선의 개수는 2 이상 1000 이하
        - 두 직선의 교점을 모든 직선에 대하여 구함
            - 교점 x : (line[i][1] * line[j][2] - line[i][2] * line[j][1]) / (line[i][0] * line[j][1] - line[i][1] * line[j][0])
            - 교점 y : (line[i][2] * line[j][0] - line[i][0] * line[j][2]) / (line[i][0] * line[j][1] - line[i][1] * line[j][0])
            - 위 교점을 double 값으로 구한뒤, long으로 캐스팅하여 정수인지 확인
            - 정수 값이라면, HashSet<Point>에 추가
            - minX, maxX, minY, maxY 값 업데이트
    - 모든 직선에 대하여 정수 교점을 구하였다면
        - 격자판 생성
             - X : maxX - minX + 1
             - Y : maxY - minY + 1
        - 모든 칸을 .로 초기화 한뒤 set에서 하나씩 뽑아 *로 변환
            - 위치는 (x - minX, maxY - y)
        - 변환 완료 후 결과값 형식에 맞게 return
*/