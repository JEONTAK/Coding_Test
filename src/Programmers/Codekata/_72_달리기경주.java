package Programmers.Codekata;

import java.util.HashMap;
import java.util.Map;

public class _72_달리기경주 {

    class Solution {
        public String[] solution(String[] players, String[] callings) {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < players.length; i++) {
                map.put(players[i], i);
            }

            for (String call : callings) {
                int callRank = map.get(call);

                players[callRank] = players[callRank - 1];
                players[callRank - 1] = call;

                map.put(players[callRank - 1], callRank - 1);
                map.put(players[callRank], callRank);
            }
            return players;
        }
    }
}

/*
문제 분석
1. 정보
    - 얀에서는 달리기 경주가 열림
    - 해설진들은 선수들이 자기 바로 앞의 선수를 추월할 때, 추월한 선수의 이름을 부름
        - 예를 들어,
            - 1 : 무무, 2 : 소, 3 : 포 일때
            - 해설진이 소를 불렀다면, 소가 무무를 추월했다는 뜻

2. 목표
    - 해설진들이 부르는 소리를 이용해 최종 등수를 배열에 담아 return

3. 제약 조건
    - 5 <= 선수 <= 50000
    - 2 <= 해설 길이 <= 1,000,000

풀이
1. 아이디어
    - 선수의 콜링이 들을때마다 일일히 선수의 위치를 찾아 바꾼다면 시간초과 가능성 존재
    - 따라서 선수의 이름과 현재 등수를 Map 배열에 같이 저장해두어 찾을때 편하게 찾을 수 있도록 함
    - 콜링에 해당하는 선수의 현재 등수를 가져오고, 해당 등수 - 1인 선수와 자리를 바꿈
    - 마찬가지로 map의 데이터도 수정해줌
    - 모든 콜링이 끝나고, player 그대로 return
*/