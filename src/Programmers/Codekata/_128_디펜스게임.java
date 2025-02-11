package Programmers.Codekata;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _128_디펜스게임 {

    class Solution {
        public int solution(int n, int k, int[] enemy) {
            int totalEnemy = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (int i = 0; i < enemy.length; i++) {
                totalEnemy += enemy[i];
                pq.add(enemy[i]);

                if (totalEnemy > n) {
                    if (k == 0) {
                        return i;
                    }
                    totalEnemy -= pq.poll();
                    k--;
                }
            }
            return enemy.length;
        }
    }

}

/*
문제 분석
1. 정보
    - 디펜스 게임은 준호가 보유한 병사 n명으로 연속되는 적의 공격을 순서대로 막는 게임
    - 다음과 같은 규칙으로 진행 됨
        - 준호는 처음에 병사 n명을 가지고 있음
        - 매 라운드마다 enemy[i]마리의 적이 등장
        - 남은 병사 중 enemy[i]명 만큼 소모하여 enemy[i]마리의 적을 막을 수 있음
            - 남은 병사의 수보다 현재 라운드의 적의 수가 더 많으면 게임이 종료
        - 게임에는 '무적권'이라는 스킬이 있고, '무적권'을 사용하면 병사의 소모 없이 한 라운드의 공격을 막을 수 있음
        - '무적권'은 최대 k번 사용 가능

2. 목표
    - 무적권을 적절한 시기에 사용하여 몇 라운드까지 막을 수 있는지 return
3. 제약 조건
    - 1 <= n <= 1_000_000_000
    - 1 <= k <= 500_000
    - 1 <= enemy의 길이 <= 1_000_000
    - 1 <= enemy[i] <= 1_000_000
    - 모든 라운드를 막을 수 있는 경우에는 enemy[i]의 길이를 return

풀이
1. 아이디어
    - int totalEnemy를 만들어 누적 적의 합을 구함
    - 우선순위 큐 사용
        - PQ<Integer>를 만들어 라운드의 적의 수를 담음
        - enemy를 0 ~ 끝까지 순회
            - totalEnemy += enemy[i]
            - pq.add(enemy[i])
            - 만약 totalEnemy가 n보다 커진다면
                - 만약 k가 0이라면
                    - 현재 라운드 반환
                - 아니라면
                    - k--
                    - pq에서 값을 하나 뽑음
                    - totalEnemy - 해당 값
*/
