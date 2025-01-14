package Programmers.Codekata;

import Programmers.Codekata._67_둘만의암호.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _71_개인정보수집유효기간 {

    public static void main(String[] args) {
        int[] result = Solution.solution("2022.05.19", new String[] {"A 6", "B 12", "C 3"}, new String[] {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});

        System.out.println(Arrays.toString(Arrays.stream(result).toArray()));

        result = Solution.solution("2020.01.01", new String[] {"Z 3", "D 5"}, new String[] {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"});

        System.out.println(Arrays.toString(Arrays.stream(result).toArray()));
    }

    static class Solution {
        public static int[] solution(String today, String[] terms, String[] privacies) {
            List<Integer> result = new ArrayList<>();
            today = today.replaceAll("\\.", "");
            int[] valid = new int[26];
            for (int i = 0; i < terms.length; i++) {
                String[] tmp = terms[i].split(" ");
                valid[tmp[0].charAt(0) - 'A'] = Integer.parseInt(tmp[1]);
            }

            for (int i = 0; i < privacies.length; i++) {
                String[] splitYnT = privacies[i].split(" ");
                String[] date = splitYnT[0].split("\\.");

                date[1] = String.valueOf((Integer.parseInt(date[1]) + valid[splitYnT[1].charAt(0) - 'A']));

                if (Integer.parseInt(date[1]) > 12) {
                    date[0] = String.valueOf(Integer.parseInt(date[0]) + Integer.parseInt(date[1]) / 12);
                    date[1] = String.valueOf(Integer.parseInt(date[1]) % 12);
                }

                if (Integer.parseInt(date[1]) == 0) {
                    date[0] = String.valueOf(Integer.parseInt(date[0]) - 1);
                    date[1] = "12";
                }

                String fin = date[0] + String.format("%02d", Integer.parseInt(date[1]))+ date[2];
                System.out.println(today + " " + fin);
                if (Integer.parseInt(today) >= Integer.parseInt(fin)) {
                    result.add(i + 1);
                }
            }

            int[] answer = new int[result.size()];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = result.get(i);
            }
            Arrays.sort(answer);

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 1 ~ N번으로 분류된 개인정보 N개가 존재
    - 약관 종류는 여러가지가 있으며 각 약관마다 개인정보 보관 유효기간이 정해져 있음
    - 수집된 개인정보는 유효기간 전까지만 보관 가능하며, 유효기간이 지났다면 반드시 파기해야함.
    - 모든달은 28일까지 있다고 가정

2. 목표
    - 파기해야할 개인정보를 return

3. 제약 조건
    - 1 <= 약관의 유효기간 개수 <= 20
    - A <= 약관 종류 <= Z
    - 1 <= 유효기간 <= 100
    - 1 <= 수집된 개인정보 <= 100
    - 2000 <= 연 <= 2022
    - 1 <= 월 <= 12
    - 1 <= 일 <= 28

풀이
1. 아이디어
    - 먼저 각 약관의 유효기간을 int[] 배열에 저장함
    - privacies를 돌면서 하나씩 비교
        - 먼저 공백을 기준으로 분리
            - 앞에는 날짜, 뒤에는 약관 종류
            - 날짜를 다시 .을 기준으로 분리
            - 약관의 유효기간을 월에 더함
                - 이때 (유효기간 + 현재 월) / 12를 구해 연에 더해주고
                - (유효기간 + 현재 월) % 12 부분에 1을 추가하여 더해줌
            - 날짜를 다시 합치고 today와 비교
                - today보다 작거나 같다면 파기해야할 대상
                - result에 해당 idx 추가
*/