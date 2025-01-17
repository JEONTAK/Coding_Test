package Programmers.Codekata;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class _89_할인행사 {

    class Solution {
        public int solution(String[] want, int[] number, String[] discount) {
            Map<String, Integer> wm = new HashMap<>();
            Map<String, Integer> dm = new HashMap<>();
            int answer = 0;

            for (int i = 0; i < want.length; i++) {
                wm.put(want[i], number[i]);
                dm.put(want[i], 0);
            }

            for (int i = 0; i < 10; i++) {
                if (dm.containsKey(discount[i])) {
                    dm.put(discount[i], dm.get(discount[i]) + 1);
                }
            }

            boolean flag = true;
            for (Entry<String, Integer> entry : dm.entrySet()){
                if(!wm.get(entry.getKey()).equals(entry.getValue())){
                    flag = false;
                    break;
                }
            }
            if(flag){
                answer++;
            }

            for (int i = 10; i < discount.length; i++) {
                if (dm.containsKey(discount[i - 10])) {
                    dm.put(discount[i - 10], dm.get(discount[i - 10]) - 1);
                }
                if (dm.containsKey(discount[i])) {
                    dm.put(discount[i], dm.get(discount[i]) + 1);
                }

                flag = true;
                for (Entry<String, Integer> entry : dm.entrySet()){
                    if(!wm.get(entry.getKey()).equals(entry.getValue())){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    answer++;
                }
            }

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - XYZ 마트는 일정한 금액을 지불하면 10일동안 회원 자격을 부여함
    - XYZ 마트에서는 회원을 대상으로 매일 한 가지 제품을 할인하는 행사를 함
        - 할인하는 제품은 하루에 하나만 구매 가능
    - 정현이는 자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우에 맞춰서 회원가입을 하려 함
        - 예를 들어
            - 원하는 제품이 바나나 3개, 사과 2개, 쌀 2개, 돼지고기 2개, 냄비 1개이며,
            - XYZ 마트에서 14일간 회원을 대상으로 할인하는 제품이 순서대로 치킨, 사과, 사과, 바나나, 쌀, 사과, 돼지고기, 바나나, 돼지고기, 쌀, 냄비, 바나나, 사과, 바나나인 경우
            - 셋째날, 넷째날, 다섯째에 회원가입을 한다면 원하는 제품과 수량이 일치하기 때문에 셋 중 하루에 회원 가입을 하려 함

2. 목표
    - 원하는 제품을 모두 할인 받을 수 있는 회원등록 날짜의 총 일수를 return

3. 제약 조건
    - 1 <= 원하는 제품 개수 = 수량 <= 10
    - 10 <= 할인 제품 길이 <= 100000
    - want와 discount 원소는 모두 알파벳 소문자로 이루어짐

풀이
1. 아이디어
    - Map<String, Integer>를 만들어 want에 해당하는 물품을 저장
        - discount 배열에서 i번째 ~ i + 10번째 일수에 해당하는 물품들 중 want에 해당하는 물품이 존재한다면 해당 값++
        - 이후 i번째 요일에 회원가입을 할 경우 모든 품목을 할인 받을 수 있는지 검사
            - map배열을 돌며 개수가 일치하는지 검사후 모두 일치하면 answer++
*/
