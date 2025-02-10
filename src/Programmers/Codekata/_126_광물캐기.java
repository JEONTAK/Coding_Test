package Programmers.Codekata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _126_광물캐기 {

    class Solution {

        class CostSum implements Comparable<CostSum> {
            int diamond;
            int iron;
            int stone;

            public CostSum(int diamond, int iron, int stone) {
                this.diamond = diamond;
                this.iron = iron;
                this.stone = stone;
            }

            @Override
            public int compareTo(CostSum o) {
                return (o.diamond + o.iron + o.stone) - (this.diamond + this.iron + this.stone);
            }
        }

        int[][] cost = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        int[] mine;
        int picksCount;
        List<CostSum> costSum = new ArrayList<>();

        public int solution(int[] picks, String[] minerals) {
            mine = new int[minerals.length];
            picksCount = picks[0] + picks[1] + picks[2];
            for (int i = 0; i < minerals.length; i++) {
                if (minerals[i].equals("diamond")) {
                    mine[i] = 0;
                } else if (minerals[i].equals("iron")) {
                    mine[i] = 1;
                } else {
                    mine[i] = 2;
                }
            }

            int idx = 0;

            while (picksCount > 0) {
                int diamond = 0;
                int iron = 0;
                int stone = 0;

                for (int i = 0; i < 5; i++) {
                    if (idx + i >= minerals.length) {
                        break;
                    }

                    diamond += cost[0][mine[idx + i]];
                    iron += cost[1][mine[idx + i]];
                    stone += cost[2][mine[idx + i]];
                }

                picksCount--;
                idx += 5;
                costSum.add(new CostSum(diamond, iron, stone));
            }

            Collections.sort(costSum);
            int answer = 0;

            for (int i = 0; i < costSum.size(); i++) {
                if (picks[0] > 0) {
                    picks[0]--;
                    answer += costSum.get(i).diamond;
                } else if (picks[1] > 0) {
                    picks[1]--;
                    answer += costSum.get(i).iron;
                } else if(picks[2] > 0){
                    picks[2]--;
                    answer += costSum.get(i).stone;
                }
            }

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 마인은 곡괭이로 광산에서 광석을 캐려고 함.
    - 마인은 다이아몬드 곡괭이, 철 곡괭이, 돌 곡괭이를 각각 0 ~ 5개 가지고 있음
    - 곡괭이로 광몰을 캘 때는 피로도가 소모 됨
    - 각 곡괭이로 광물을 캘 때의 피로도는 아래와 같음
        -      다이아 철 돌
        - 다곡    1   1  1
        - 철곡    5   1  1
        - 돌곡    25  5  1
    - 각 곡괭이는 종류에 상관없이 광물 5개를 캔 후에는 더 이상 사용 불가
    - 마인은 다음과 같은 규칙을 지키면서 최소한의 피로도로 광물을 캐려고 함
        - 사용할 수 있는 곡괭이 중 하나를 선택
        - 한 번 사용하기 시작한 곡괭이는 사용할 수 없을 때까지 사용
        - 광물은 주어진 순서대로만 캐기 가능
        - 광산에 있는 모든 광물을 캐거나, 더 사용할 곡괭이가 없을 때까지 캠

2. 목표
    - 곡괭이의 개수를 나타내는 picks와 광물의 순서를 나타내는 minerals가 주어질 때, 작업을 끝내기까지 필요한 최소한의 피로도를 return
3. 제약 조건
    - 0 <= dia, iron, stone <= 5
    - 5 <= minerals 길이 <= 50


풀이
1. 아이디어
    - 5개 별로 묶어 Cost를 구함
        - diamond : 다이아 곡괭이 사용 시 피로도
        - iron : 철 곡괭이 사용 시 피로도
        - stone : 돌 곡괭이 사용 시 피로도
    - 위 3개를 CostSum class를 만들어 저장
    - 모든 자원을 캐거나, 모든 곡괭이가 소모되었을 경우
        - 결과를 계산
            - CostSum을 diamond + iron + stone 합의 내림차순으로 정렬
            - 합의 내림차순으로 정렬하는 이유
                - 합이 크다 -> 피로도가 많이 소모된다 -> 고 가치의 광물이 많이 존재한다는 의미
                - 따라서 고 가치의 광물은 고 가치의 곡괭이를 사용해야 피로도의 이득을 볼 수 있음
            - 정렬 이후, 다이아, 철, 돌 곡괭이의 개수 만큼 costsum의 다이아, 철, 돌의 cost 비용을 answer에 더하고 return
*/
