class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] result = new int[board.length + 1][board[0].length + 1];
        
        for(int i = 0; i < skill.length; i++) {
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            if(skill[i][0] == 1) {
                computeSim(result, r1, c1, r2, c2, -1 * degree);
            } else {
                computeSim(result, r1, c1, r2, c2, degree);
            }
            
        }
        
        for(int i = 0 ; i < result.length ; i++) {
            for(int j = 1; j < result[i].length; j++) {
                result[i][j] += result[i][j - 1];
            }
        }
        
        for(int i = 0 ; i < result[0].length; i++) {
            for(int j = 1 ; j < result.length; j++) {
                result[j][i] += result[j - 1][i];
            }
        }
        
        int answer = 0;
        
        for(int i = 0 ; i < board.length; i++) {
            for(int j = 0 ; j < board[i].length; j++) {
                if(board[i][j] + result[i][j] > 0) answer++;
            }
        }

        return answer;
    }
    
    public void computeSim(int[][] result, int r1, int c1, int r2, int c2, int degree) {
        result[r1][c1] += degree;
        result[r2 + 1][c1] -= degree;
        result[r1][c2 + 1] -= degree;
        result[r2 + 1][c2 + 1] += degree;
    }
}

/*
시뮬레이션 문제
skill에서
0번이 1이면 적의 공격
2이면 아군 회복
degree만큼 낮추거나 높임

단순 시뮬레이션 하면 시간 초과 발생

카카오 해설에는 누적합을 사용하라 나와있음.

x1,y1 ~ x2,y2 까지 n만큼의 변화를 주고싶다면,
(x1,y1) = n, (x2 + 1, y1) = -n, (x1, y2 + 1) = -n, (x2 + 1, y2 + 1) = n을 더해준다.

예를 들어, (0,0) ~ (2,3) 까지 -4를 넣고 싶다면, 

[-4, 0, 0, 0, 4]
[ 0, 0, 0, 0, 0]
[ 0, 0, 0, 0, 0]
[ 4, 0, 0, 0,-4]

먼저 각 행마다 왼쪽에서 오른쪽으로 각각 누적합을 해줌

[-4,-4,-4,-4, 0]
[ 0, 0, 0, 0, 0]
[ 0, 0, 0, 0, 0]
[ 4, 4, 4, 4, 0]

그 다음, 각 열마다 위에서 아래로 각각 누적합을 해줌

[-4,-4,-4,-4, 0]
[-4,-4,-4,-4, 0]
[-4,-4,-4,-4, 0]
[ 0, 0, 0, 0, 0]

(0,0) ~ (2,3) 까지 일일히 시뮬레이션 하지 않고도 만들 수 있음.

그렇다면 skill 값을 받아 위 설명한 좌표에 degree 값을 저장하고
마지막에 누적합으로 전체 결과를 구함.
새 int[][] 배열을 만들어 board 크기보다 1씩 크게 만들어 저장

누적합 결과와 기존 board값을 더해 1 이상이면 answer++;

*/