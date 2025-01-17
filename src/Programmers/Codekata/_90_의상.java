package Programmers.Codekata;

import java.util.HashMap;
import java.util.Map;

public class _90_의상 {

    class Solution {
        public int solution(String[][] clothes) {
            Map<String, Integer> map = new HashMap<>();
            for (String[] clothe : clothes) {
                map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
            }

            int answer = 1;

            for (String s : map.keySet()) {
                answer *= map.get(s) + 1;
            }
            return answer - 1;
        }
    }

}

/*
문제 분석
1. 정보
    - 코니는 매일 다른 옷을 조합하여 입는 것을 좋아함
    - 예를 들어,
        - 얼굴 : 동그란 안경, 검정 선글라스
        - 상의 : 파란색 티셔츠
        - 하의 : 청바지
        - 겉옷 : 긴 코트
    - 오늘 코니가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면
    - 다음날은 청바지를 추가로 입거나, 동그란 안경 대신 검정 선글라스를 착용해야함
        - 코니는 각 종류별 최대 1가지 의상만 착용 가능
        - 일부가 겹치더라도 다른 의상이 겹치지 않거나, 추가로 착용한 경우도 다른 방법으로 간주
        - 하루에 최소 한 개 이상은 입어야 함

2. 목표
    - 서로 다른 옷의 조합의 수를 return

3. 제약 조건
    - 1 <= 코니가 가진 의상의 수 <= 30
    - 같은 이름은 존재 X
    - 모든 원소는 문자열로 이루어짐
    - 1 <= 문자열의 길이 <= 20, 알파벳 소문자 or _로 이루어짐

풀이
1. 아이디어
    - Map<String, String>을 통해 일단 의상들을 담는다.
    - 각 종류 별로 의상의 개수가 담기게 될 것이다.
        - 여기서 각 종류 별로
            - 의상의 개수 + 1을 한 값을 결과값에 곱해준다
            - +1의 이유는 해당 의상을 입지 않았을 경우를 포함 해야 하기 때문이다.
        - 모든 값을 구한 후 - 1 한 값을 return 한다
        - -1을 하는 이유는 전체 의상을 입지 않았을 경우는 제외해야 하기 때문이다.
*/