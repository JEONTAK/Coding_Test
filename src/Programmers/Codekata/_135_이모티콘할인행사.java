package Programmers.Codekata;

public class _135_이모티콘할인행사 {

    class Solution {

        boolean[] check;
        int[] answer = new int[2];
        int[] discountRatio;

        public int[] solution(int[][] users, int[] emoticons) {
            check = new boolean[emoticons.length];
            discountRatio = new int[emoticons.length];
            setDiscountEmoticons(0, 0, emoticons, users);
            return answer;
        }

        private void setDiscountEmoticons(int idx, int cnt, int[] emoticons, int[][] users) {
            if (cnt == emoticons.length) {
                computeUser(users, emoticons);
                return;
            }

            for (int i = idx; i < emoticons.length; i++) {
                if (!check[i]) {
                    check[i] = true;
                    for (int j = 1; j <= 4; j++) {
                        discountRatio[i] = j * 10;
                        setDiscountEmoticons(i, cnt + 1, emoticons, users);
                    }
                    check[i] = false;
                }
            }
        }

        private void computeUser(int[][] users, int[] emoticons) {
            int totalService = 0;
            int totalPrice = 0;
            for (int i = 0; i < users.length; i++) {
                int curUserService = 0 ;
                int curUserPrice = 0;
                for (int j = 0; j < emoticons.length; j++) {
                    if (users[i][0] <= discountRatio[j]) {
                        curUserPrice += emoticons[j] * (100 - discountRatio[j]) / 100;
                    }
                }

                if (curUserPrice >= users[i][1]) {
                    curUserPrice = 0;
                    curUserService = 1;
                }

                totalService += curUserService;
                totalPrice += curUserPrice;
            }

            if (answer[0] < totalService) {
                answer[0] = totalService;
                answer[1] = totalPrice;
                return;
            }

            if (answer[0] == totalService) {
                answer[1] = Math.max(answer[1], totalPrice);

            }
        }
    }

}

/*
문제 분석
1. 정보
    - 카카오톡에서는 이모티콘을 무제한으로 사용할 수 있는 이모티콘 플러스 서비스 가입자 수를 늘리려고 한다,
    - 이를 위해 카카오톡에서는 이모티콘 할인 행사를 하는데, 목표는 다음과 같다.
        - 1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
        - 2. 이모티콘 판매액을 최대한 늘리는 것.
    - 1번 목표가 우선이고, 2번 목표가 그 다음이다.
    - 이모티콘 할인 행사는 다음과 같은 방식으로 진행 된다.
        - n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매
        - 이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정
    - 카카오톡 사용자들은 다음과 같은 기준을 따라 이모티콘을 사거나, 이모티콘 플러스 서비스에 가입한다.
        - 각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매
        - 각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입
2. 목표
    - 카카오톡 사용자 n명의 구매 기준을 담은 2차원 정수 배열 users, 이모티콘 m개의 정가를 담은 1차원 정수 배열 emoticons가 주어짐.
    - 이때 행사 목적을 최대한으로 달성했을 때의 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액을 1차원 정수 배열에 담아 return

3. 제약 조건
    - 1 <= users의 길이 = n <= 100
        - users의 원소는 [비율, 가격]의 형태
        - users[i]는 i+1번 고객의 구매 기준
        - 비율% 이상의 할인이 있는 이모티콘을 모두 구매한다는 의미
            - 1 <= 비율 <= 40
        - 가격 이상의 돈을 이모티콘 구매에 사용한다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입
            - 100 <= 가격 <= 1,000,000
            - 가격읜 100의 배수
    - 1 <= emoticons의 길이 = m <= 7
        - emoticons[i]는 i+1번 이모티콘의 정가를 의미
        - 100 <= emoticons의 원소 <= 1,000,000
        - emoticons의 원소는 100의 배수

풀이
1. 아이디어
    - 이모티콘의 길이는 7이므로, 할인율을 각각 10, 20, 30, 40으로 적용할 경우의 수는
        - 4^7 = 2^14 = 16,384가지 -> 브루트 포스 사용
    - 할인율을 적용한 이모티콘을 사용하여 각 유저의 서비스 가입 유무 및 판매액 구함
    - 모든 유저의 서비스 가입 유무와 판매액의 합을 저장
        - 만약 서비스 가입 유무가 더 크다면 해당 값으로 저장
        - 서비스 가입 유무가 같다면 판매액이 더 큰 것으로 저장
        - 서비스 가입 유무가 작다면 기존 값 사용
    
    - 1. 각 이모티콘의 할인율의 모든 경우의 수 구하기(브루트 포스)
    - 2. 모든 이모티콘의 할인율이 정해졌다면, 모든 유저의 서비스 가입 유무와 판매액 구하기
        - 전체 유저 수만큼 반복
            - 해당 유저의 구입 금액을 구함(만약 할인율이 비율보다 높을 경우 할인된 가격으로 구매)
            - 만약 구입 금액이 유저의 금액을 초과할 경우 서비스 가입 유무를 + 1 시키고, 구입 금액은 0으로 설정
            - 전체 서비스 가입 유무 + 해당 유저 서비스 가입유무
            - 전체 구입 금액 + 해당 유저 구입 금액
        - 전체 서비스 가입 유무와 전체 구입 금액을 사용하여 기존 값과 비교 후 업데이트
    - 3. 저장한 값 return
    
2. 주의할 점
    - 할인율 적용시 계산 순서를 잘 정해야 한다.
        - 처음 시도는 가격 X (double)(1 - 할인율 / 100)
        - 이후 시도는 가격 X (100 - 할인율) / 100 -> 모든 값을 int로 처리 가능
*/
