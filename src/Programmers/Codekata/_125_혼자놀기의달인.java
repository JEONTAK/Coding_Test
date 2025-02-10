package Programmers.Codekata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _125_혼자놀기의달인 {

    class Solution {

        boolean[] check;
        List<Integer> result = new ArrayList<>();

        public int solution(int[] cards) {
            check = new boolean[cards.length + 1];

            for (int i = 1; i < check.length; i++) {
                if (!check[i]) {
                    check[i] = true;
                    DFS(i, 1, cards);
                }
            }

            if (result.size() <= 1) {
                return 0;
            }else{
                result.sort(Collections.reverseOrder());
            }
            return result.get(0) * result.get(1);
        }

        private void DFS(int idx, int cnt, int[] cards) {
            idx = cards[idx - 1];

            if (check[idx]) {
                result.add(cnt);
                return;
            }

            check[idx] = true;
            DFS(idx, cnt + 1, cards);
        }
    }

}

/*
문제 분석
1. 정보
    - 숫자 카드 더미를 보고 혼자 할 수 있는 재미있는 게임을 생각
        - 숫자 카드 더미에는 총 100장이 존재, 카드는 1부터 100까지 각각 한 장씩 존재
        - 2 이상 100 이하의 자연수를 하나 정해 그 수보다 작거나 같은 숫자 카드들을 준비하고 준비한 카드의 수 만큼 작은 상자를 준비
        - 상자에 카드를 한 장씩 넣고, 상자를 무작위로 섞어 일렬로 나열
        - 상자가 나열되면 나열된 순서에 따라 1번부터 순차적으로 번호를 붙임
            - 그 다음 임의의 상자를 하나 선택하여 선택한 상자 안의 숫자 카드 확인
            - 다음으로 확인한 카드에 적힌 번호에 해당하는 상자를 열어 담긴 카드에 적힌 숫자를 확인
            - 위 규칙을 열어야 하는 상자가 이미 열려있을 때 까지 반복
        - 이렇게 연 상자들을 1번 그룹
            - 만약 1번 상자 그룹을 제외하고 남는 상자가 없으면 그대로 게임이 종료, 0점 획득
        - 남는 상자가 있다면, 2번 그룹으로 칭하고 위 규칙 반복
            - 1번 상자 그룹에 속한 상자의 수와 2번 상자 그룹에 속한 상자의 수를 곱한 값이 게임의 점수

2. 목표
    - cards가 매개변수로 주어질 때, 이 게임에서 얻을 수 있는 최고 점수를 구해서 return

3. 제약 조건
    - 2 <= cards 길이 <= 100
    - 1 <= cards의 원소 <= cards 길이

풀이
1. 아이디어
    - 입력받은 cards를 통해 check[] 를 만들어 방문 여부 저장
    - 1번부터 차례대로
        - 해당 상자에 있는 카드에 해당하는 번호로 이동(이동시 check[i] true)
            - 만약 이동한 상자에 있는 카드가 이미 방문한 번호라면,  시작 번호와 개수 저장
    - 모든 상자를 방문했다면, 시작번호 ,개수 중에서 개수가 가장 큰 2개를 골라 곱한 값을 return
    - 만약 1개라면 0을 return
*/
