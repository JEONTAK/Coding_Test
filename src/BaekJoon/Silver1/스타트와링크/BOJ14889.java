package BaekJoon.Silver1.스타트와링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {

    static int N;
    static int[][] player;
    static boolean[] team;
    static int result = Integer.MAX_VALUE;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        player = new int[N][N];
        team = new boolean[N];

        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                player[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        setTeam(0,0);
        System.out.println(result);
    }

    static void setTeam(int idx, int cnt){
        if(cnt == N / 2){
            result = Math.min(result, compute());
            return;
        }

        if(idx >= N) return;

        team[idx] = true;
        setTeam(idx + 1, cnt + 1);

        team[idx] = false;
        setTeam(idx + 1, cnt);
    }

    static int compute(){
        int start = 0;
        int link = 0;

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if (team[i] && team[j]) {
                    start += player[i][j];
                } else if (!team[i] && !team[j]) {
                    link += player[i][j];
                }
            }
        }
        return Math.abs(start - link);
    }

    public static void main(String[] args) throws IOException {
        BOJ14889.solution();
    }
}

/*
문제 분석

1. 정보
    - 스타트링크에 다니는 사람들이 모여서 축구를 한다.
    - 축구를 하기 위해 모인 사람은 총 N명, N은 짝수
    - 번호를 1부터 N까지로 배정했고, 각각 능력치를 조사한다.
    - 능력치 S_{ij}는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치
    - S_{ij}와 S_{ji}는 다를 수도 있고, 만약 i번 사람과 j번 사람이 같은 팀이 된다면, 팀에 더해지는 능력치는 S_{ij} + S_{ji} 이다.

2. 목표
    - 두 팀의 능력치 차이의 최솟값을 구해 출력한다.

3. 제약 조건
    - 4 <= N <= 20, N은 짝수
    - S_{ii}는 항상 0, 1 <= S_{ij} <= 100

풀이
1. 아이디어
    - 백트래킹
        - 브루트포스를 이용하여 팀을 나누는 모든 경우의 수를 구한다.
        - 만약 팀이 다 나눠졌을 경우 : cnt == N / 2
            - 팀간의 능력치의 총합의 최솟값을 업데이트한다.
        - 팀 나누는 방법
            - boolean[] 배열을 만든다. true 팀, false 팀으로 나누기 위함
        - 능력치 합 계산 방법
            - boolean[] 배열을 사용하여 true 팀의 능력치 합을 구하고
            - false 팀의 능력치 합을 구한다
                - 0 ~ N 까지 : i
                    - 이중 포문을 사용해서 : j
                        - 만약 i와 j의 값이 다르고, i와 j의 boolean값이 같다면 같은 팀이므로 해당 팀에 능력치를 추가

*/