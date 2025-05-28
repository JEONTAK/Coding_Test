import java.util.*;

class Solution {
    
    private int[][][] build;
    
    public int[][] solution(int n, int[][] build_frame) {
        
        build = new int[n + 1][n + 1][2];
        
        for(int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2];
            int b = build_frame[i][3];
            if(b == 1) {
                if(a == 0) {
                    if(canSetPillar(x, y, n)) {
                        build[x][y][0] = 1;
                    }
                } else {
                    if(canSetBeam(x, y, n)) {
                        build[x][y][1] = 1;
                    }
                }
            } else{
                if(a == 0) {
                    build[x][y][0] = 0;
                    
                    //좌표 위 기둥이 존재
                    if(y + 1 <= n && build[x][y + 1][0] == 1) {
                        if(!canSetPillar(x, y + 1, n)) {
                            build[x][y][0] = 1;
                            continue;
                        }
                    }
                    
                    //기둥 위에 보가 존재
                    if(y + 1 <= n && build[x][y + 1][1] == 1) {
                        if(!canSetBeam(x, y + 1, n)) {
                            build[x][y][0] = 1;
                            continue;
                        }
                    }
                    
                    //기둥 왼쪽 위에 보가 존재
                    if(y + 1 <= n && 0 <= x - 1 && build[x - 1][y + 1][1] == 1) {
                        if(!canSetBeam(x - 1, y + 1, n)) {
                            build[x][y][0] = 1;
                            continue;
                        }
                    }
                } else {
                    build[x][y][1] = 0;
                    
                    //보 위에 기둥이 존재
                    if(build[x][y][0] == 1) {
                        if(!canSetPillar(x, y, n)) {
                            build[x][y][1] = 1;
                            continue;
                        }
                    }
                    
                    //보 오른쪽에 기둥이 존재
                    if(x + 1 <= n && build[x + 1][y][0] == 1) {
                        if(!canSetPillar(x + 1, y , n)) {
                            build[x][y][1] = 1;
                            continue;
                        }
                    }
                    
                    //보 오른쪽에 보가 존재
                    if(x + 1 <= n && build[x + 1][y][1] == 1) {
                        if(!canSetBeam(x + 1, y, n)) {
                            build[x][y][1] = 1;
                            continue;
                        }
                    }
                    
                    //보 왼쪽에 보가 존재
                    if(0 <= x - 1 && build[x - 1][y][1] == 1) {
                        if(!canSetBeam(x - 1, y, n)) {
                            build[x][y][1] = 1;
                            continue;
                        }
                    }
                }
            }
        }
        
        List<int[]> result = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(build[i][j][0] == 1) {
                    result.add(new int[] {i,j,0});
                }
                if(build[i][j][1] == 1) {
                    result.add(new int[] {i,j,1});
                }
            }
        }
        
        
        int[][] answer = new int[result.size()][3];
        for(int i = 0 ; i < result.size(); i++) {
            int[] temp = result.get(i);
            answer[i][0] = temp[0];
            answer[i][1] = temp[1];
            answer[i][2] = temp[2];
        }
        
        Arrays.sort(answer, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        
        return answer;
    }
    
    //기둥 생성
    private boolean canSetPillar(int x, int y, int n) {
        return y == 0 //바닥인 경우
            || (y - 1 >= 0 && build[x][y - 1][0] == 1) //아래 기둥 존재
            || (x - 1 >= 0 && build[x - 1][y][1] == 1) //왼쪽에 보 존재
            || build[x][y][1] == 1; //해당 위치에 보 존재
    }
    
    //보 생성
    private boolean canSetBeam(int x, int y, int n) {
        return (y - 1 >= 0 && build[x][y - 1][0] == 1) //아래 기둥 존재
            || (x + 1 <= n && y - 1 >= 0 && build[x + 1][y - 1][0] == 1) //우측 아래 기둥 존재
            || (x - 1 >= 0 && x + 1 <= n && build[x - 1][y][1] == 1 && build[x + 1][y][1] == 1); //양 옆에 보 존재
    }
   
}

/*
기둥은 바닥 or 기둥 or 보와 연결 되어있으면 가능
보는 기둥 or 양쪽 보와 연결되어 있으면 가능
한쪽 보와 연결되어 있는 보는 안됨
만약 해당 작업이 조건에 충족되지 않는 경우 무시됨

build_frame 순서대로 작업
[x,y,a,b]
a = 0 기둥
a = 1 보
b = 0 삭제
b = 1 설치
그럼 [][][] 배열 생성해서 관리?
[x][y][i] i 는 0과 1로 이루어짐 0은 기둥 1은 보
교차점 좌표 기준으로 오른쪽 또는 위로 설치되어 있다.
시뮬레이션 하면 될듯

기둥 생성시
1. 좌표가 바닥인 경우
2. 좌표 아래 기둥이 존재하는 경우
3. 좌표 왼쪽에 보가 존재하는 경우
4. 좌표에 보가 있는 경우

보 생성시
1. 좌표 아래 기둥이 존재하는 경우
2. 좌표 우측 아래 기둥이 존재하는 경우
3. 좌우에 보가 있는 경우

기둥 삭제 불가
1. 좌표 위에 기둥이 있는 경우
-> 해당 좌표의 기둥을 삭제해보고, 좌표 위에 기둥 설치 시도시 설치가 안되는 경우
2. 좌표 위에 보가 있는 경우
-> 해당 기둥을 삭제해보고, 해당 좌표 위에 보를 설치하려고 할 때 설치가 안되는 경우
3. 좌표 왼쪽 위 보가 있는 경우
-> 해당 기둥을 삭제해보고, 좌측 좌표 위에 보를 설치하려고 할 때 설치가 안되는 경우

보 삭제 불가
1. 좌표에 기둥이 있는 경우
-> 보를 삭제해보고 기둥을 설치하려 할 때 설치가 안되는 경우
2. 좌표 우측에 기둥이 있는 경우
-> 보를 삭제해보고 좌표 오른쪽에 기둥을 설치하려 할 때 설치가 안되는 경우
3. 좌표 우측에 보가 있는 경우 
-> 보를 삭제해보고 좌표 오른쪽에 보를 설치하려 할 때 설치가 안되는 경우
4. 좌표 왼쪽에 보가 있는 경우
-> 보를 삭제해보고 좌표 왼쪽에 보를 설치하려 할 때 설치가 안되는 경우


*/