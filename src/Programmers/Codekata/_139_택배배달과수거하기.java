package Programmers.Codekata;


public class _139_택배배달과수거하기 {

    class Solution {

        long answer = 0;

        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            int dIdx = n - 1;
            int pIdx = n - 1;

            while (dIdx >= 0 || pIdx >= 0) {
                int maxDist = 0;

                int remainCap = cap;

                while (dIdx >= 0 && remainCap > 0) {
                    if (deliveries[dIdx] > 0) {
                        int deliver = Math.min(remainCap, deliveries[dIdx]);
                        deliveries[dIdx] -= deliver;
                        remainCap -= deliver;
                        maxDist = Math.max(maxDist, dIdx + 1);
                    }

                    while(deliveries[dIdx] == 0) {
                        dIdx--;
                        if (dIdx < 0) {
                            break;
                        }
                    }
                }

                remainCap = cap;

                while (pIdx >= 0 && remainCap > 0) {
                    if (pickups[pIdx] > 0) {
                        int pickup = Math.min(remainCap, pickups[pIdx]);
                        pickups[pIdx] -= pickup;
                        remainCap -= pickup;
                        maxDist = Math.max(maxDist, pIdx + 1);
                    }

                    while (pickups[pIdx] == 0) {
                        pIdx--;
                        if (pIdx < 0) {
                            break;
                        }
                    }
                }

                if (maxDist > 0) {
                    answer += maxDist * 2L;
                }
            }

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 일렬로 나열된 n개의 집에 택배를 배달하려 함.
    - 배달할 물건은 모두 크기가 같은 재활용 택배 상자에 담아 배달하고, 배달을 다니며 빈 재활용 택배 상자들을 수거하려 함
    - 배달할 택배들은 모두 재활용 택배 상자에 담겨 물류창고에 보관되어 있음
    - i번째 집은 물류창고에서 거리 i만큼 떨어져 있음
    - i번째 집은 j번째 집과 j - i 거리만큼 떨어져 있음
    - 트럭에는 재활용 택배 상자를 최대 cap개 실을 수 있음
    - 배달할 재활용 택배 상자들을 실어 물류 창고에서 출발해 각 집에 배달하면서, 빈 재활용 택배상자들을 수거해 물류창고에 내림
    - 각 집마다 배달할 재활용 택배 상자의 개수와 수거할 빈 재활용 택배 상자의 개수를 알고 있을 때, 트럭 하나로 모든 배달과 수거를 마치고 물류 창고까지 돌아올 수 있는 최소 이동 거리를 구하려 함
    - 각 집에 배달 및 수거할 때, 원하는 개수만큼 택배를 배달 및 수거 가능
2. 목표
    - 트럭에 실을 수 있는 재활용 택배 상자의 최대 개수를 나타내는 정수 cap, 배달할 집의 개수를 나타내는 정수 n, 각 집에 배달할 재활용 택배 상자의 개수를 담은 1차원 정수 배열 deliveries와 각 집에서 수거할 빈 재활용 택배 상자의 개수를 담은 1차원 정수 배열 pickups가 매개변수로 주어짐
    - 이때 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 return

3. 제약 조건
    - 1 ≤ cap ≤ 50
    - 1 ≤ n ≤ 100,000
    - deliveries의 길이 = pickups의 길이 = n
        - deliveries[i]는 i+1번째 집에 배달할 재활용 택배 상자의 개수
        - pickups[i]는 i+1번째 집에서 수거할 빈 재활용 택배 상자의 개수
        - 0 ≤ deliveries의 원소 ≤ 50
        - 0 ≤ pickups의 원소 ≤ 50
    - 트럭의 초기 위치는 물류창고

풀이
1. 아이디어
    - 가장 먼곳 부터 차례대로 배달 및 수거
        - 이때, cap에 허용되는 범위만큼 배달을 하고, cap에 허용되는 범위만큼 상자를 가져오도록 함
    - deliveries[]에서 cap 허용범위 만큼 택배를 쌓음
        - deliveries를 업데이트 및 가장 먼곳의 거리를 저장
    - pickups에서 cap 허용범위 만큼 택배를 쌓음
        - pickups를 업데이트 및 가장 먼곳의 거리를 저장
    - 두 거리중 더 긴 거리 X 2를 종합 거리에 추가
    - 해당 메서드를 deliveries와 pickup의 모든 택배가 없어질 때 까지 진행
*/
