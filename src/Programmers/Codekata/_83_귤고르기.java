package Programmers.Codekata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _83_귤고르기 {

    class Solution {

        public int solution(int k, int[] tangerine) {
            Map<Integer, Integer> list = new HashMap<>();
            for (int cur : tangerine) {
                if (list.containsKey(cur)) {
                    list.compute(cur, (key, amount) -> amount + 1);
                }else{
                    list.put(cur, 1);
                }
            }

            List<Integer> listKeySet = new ArrayList<>(list.keySet());

            Collections.sort(listKeySet, (v1, v2) -> list.get(v2).compareTo(list.get(v1)));
            int sum = 0;
            int answer = 0;
            for (Integer cur : listKeySet) {
                if (sum >= k) {
                    break;
                }
                sum += list.get(cur);
                answer++;
            }
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 수확한 귤 중 K개를 골라 상자 하나에 담아 판매하려고 함
    - 하지만 수확한 귤의 크기가 일정하지 않아 귤을 크기별로 분류했을 때 서로 다른 종류의 수를 최소화 하고 싶음
        - 예를들어
            - 수확한 귤 8개의 크기가 1,3,2,5,4,5,2,3 이라고 가정
            - 귤 6개를 판매하고 싶다면, 크기가 1, 4인 귤을 제외한 6개의 귤을 상자에 담으면 귤의 크기의 종류가 2,3,5로 총 3가지가 되며, 이때가 서로 다른 종류가 최소가 되는 경우
2. 목표
    - 귤 k를 고를 때 서로 다른 종류의 수의 최솟값을 return

3. 제약 조건
    - 1 <= k <= 귤의 개수 <= 100000
    - 1 <= 귤의 크기 <= 10,000,000

풀이
1. 아이디어
    - 입력 받은 tangerine을 사용해 Map<Integer,Integer>생성
        - 해당 Map에는 귤의 크기와 해당 귤의 개수를 저장
    - 모든 귤을 저장하고, 귤의 개수 내림차순으로 정렬
    - k개가 될때 까지 귤을 뽑고, k개보다 크거나 같아진다면
        - 귤의 종류의 개수를 return
*/