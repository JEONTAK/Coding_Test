package Programmers.Codekata;

public class _69_성격유형검사하기 {

    class Solution {
        public String solution(String[] survey, int[] choices) {
            int[] score = new int[26];

            for (int i = 0; i < survey.length; i++) {
                int left = survey[i].charAt(0) - 'A';
                int right = survey[i].charAt(1) - 'A';
                switch (choices[i]) {
                    case (1) -> score[left] += 3;
                    case (2) -> score[left] += 2;
                    case (3) -> score[left] += 1;
                    case (5) -> score[right] += 1;
                    case (6) -> score[right] += 2;
                    case (7) -> score[right] += 3;
                }
            }

            StringBuilder sb = new StringBuilder();
            if(score['R' - 'A'] >= score['T' - 'A']){
                sb.append("R");
            } else {
                sb.append("T");
            }

            if(score['C' - 'A'] >= score['F' - 'A']){
                sb.append("C");
            } else {
                sb.append("F");
            }

            if(score['J' - 'A'] >= score['M' - 'A']){
                sb.append("J");
            } else {
                sb.append("M");
            }

            if(score['A' - 'A'] >= score['N' - 'A']){
                sb.append("A");
            } else {
                sb.append("N");
            }

            return sb.toString();
        }
    }

}
/*
문제 분석
1. 정보
     - 성격 유형 검사는 다음과 같은 4개 지표로 셩격 유형을 구분함
        - 1번 지표 : 라이언형(R), 튜브형(T)
        - 2번 지표 : 콘형(C), 프로도형(F)
        - 3번 지표 : 제이지형(J), 무지형(M)
        - 4번 지표 : 어피치형(A), 네오형(N)
    - 4개의 지표가 있으므로, 성격 유형은 총 16가지가 나올 수 있음
    - 검사지에는 총 N개의 질문이 있고, 아래와 같은 7개의 선택지가 존재
        - 매우 비동의
        - 비동의
        - 약간 비동의
        - 모르겠음
        - 약간 동의
        - 동의
        - 매우 동의
    - 각 질문은 1가지 지표로 성격 유형 점수를 판단
    - 예를 들어,
        - 매우 비동의 : 네오형 3점
        - 비동의 : 네오형 2점
        - 약간 비동의 : 네오형 1점
        - 모르겠음 : 0점
        - 약간 동의 : 어피치형 1점
        - 동의 : 어피치형 2점
        - 매우 동의 : 어피치형 3점
    - 질문에 따라 비동의와 동의의 형이 주어짐
    - 검사 결과는 모든 질문의 성격 유형 점수를 더하여 각 지표에서 더 높은 점수를 받은 성격 유형이 검사자의 성격 우형
        - 만약 하나의 지표에서 각 성격 유형 점수가 같다면, 사전순으로 빠른 성격 유형을 선택
2. 목표
    - 검사자의 성격 유형 검사 결과를 지표 번호 순서대로 return
3. 제약 조건
    - 1 <= 검사 질문 지표 <= 1000
    - "AB"형태로 들어옴
        - 왼쪽 char가 비동의 관련, 오른쪽 char가 동의 관련
    - 검사의 길이 = 검사 질문 지표의 길이


풀이
1. 아이디어
    - 단순 구현
        - 먼저 int[] 배열을 만들음. 대문자 알파벳의 개수를 길이로 하는 배열을 만들어 점수를 담음
        - survey의 길이 만큼 for문 수행
            - i번째의 choice 값을 가져옴.
            - case문을 만들어 수행
                - 1 : 왼쪽 char값에 해당하는 배열에 3 추가
                - 2 : 왼쪽 char값에 해당하는 배열에 2 추가
                - 3 : 왼쪽 char값에 해당하는 배열에 1 추가
                - 4 : 점수 추가 없음
                - 5 : 오른쪽 char값에 해당하는 배열에 1 추가
                - 6 : 오른쪽 char값에 해당하는 배열에 2 추가
                - 7 : 오른쪽 char값에 해당하는 배열에 3 추가
        - 모든 survey 가 수행되었으면,
            - R과 T 비교
            - C와 F 비교
            - J와 M 비교
            - A와 N 비교
                - 하여 더 큰값, 값이 같다면 사전순으로 빠른 값을 answer에 추가
        - answer return
*/
