package Programmers.Codekata;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class _96_주차요금계산 {

    public static void main(String[] args) {
        Solution.solution(new int[]{120, 0, 60, 591},
                new String[]{"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "22:59 5961 OUT",
                        "23:58 3961 IN"});
        System.out.println();
        Solution.solution(new int[]{180, 5000, 10, 600},
                new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
                        "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
    }

    static class Solution {

        static class Car{
            int number;
            LocalTime in;
            LocalTime out;

            public Car(int number, LocalTime in, LocalTime out) {
                this.number = number;
                this.in = in;
                this.out = out;
            }
        }

        public static int[] solution(int[] fees, String[] records) {
            List<Car> cars = new ArrayList<>();

            for (String record : records) {
                String[] split = record.split(" ");
                int number = Integer.parseInt(split[1]);
                LocalTime time = LocalTime.parse(split[0]);
                if ("IN".equals(split[2])) {
                    cars.add(new Car(number, time, LocalTime.parse("23:59")));
                }else{
                    for (int i = cars.size() - 1; i >= 0; i--) {
                        if (cars.get(i).number == number) {
                            cars.get(i).out = time;
                            break;
                        }
                    }
                }
            }

            Map<Integer, Integer> totalTime = new HashMap<>();

            for (Car car : cars) {
                if (totalTime.containsKey(car.number)) {
                    int time = totalTime.get(car.number);
                    time += (int) Duration.between(car.in, car.out).toMinutes();
                    totalTime.put(car.number, time);
                }else{
                    int time = (int) Duration.between(car.in, car.out).toMinutes();
                    totalTime.put(car.number, time);
                }
            }

            Map<Integer, Integer> sortedMap = new TreeMap<>(totalTime);

            int[] answer = new int[totalTime.size()];
            int idx = 0;

            for (Integer number : sortedMap.keySet()) {

                int time = sortedMap.get(number);
                int price = fees[1];
                if (time > fees[0]) {
                    price += (int)Math.ceil((double) (time - fees[0]) / fees[2]) * fees[3];
                }
                answer[idx++] = price;
            }

            return answer;
        }
    }

}
/*
문제 분석
1. 정보
    - 요금표는 다음과 같음
        기본시간(분) | 기본 요금(원) | 단위 시간(분) | 단위 요금(원)
        180        | 5000        | 10          | 600
    - 어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주
    - 00:00 부터 23:59까지의 입/출차 내역을 바탕으로 차랑별 누적 주차 시간을 계산하여 요금을 일괄로 정산
    - 누적 주차 시간이 기본 시간 이하라면, 기본 요금을 청구
    - 누적 주차 시간이 기본 시간을 초과하면, 기본 요금에 더해서 초과한 시간에 대해 단위 시간 마다 단위 요금을 청구
        - 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림
    
2. 목표
    - 주차 요금을 나타내는 정수 배열 fees, 자동차의 입/출차 내역을 나타내는 문자열 배열 records가 주어질 때, 차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아 return
    
3. 제약 조건
    - fees의 길이 = 4
        - fees[0] = 기본 시간
            - 1 <= fees[0] <= 1439
        - fees[1] = 기본 요금
            - 0 <= fees[1] <= 100000
        - fees[2] = 단위 시간
            - 0 <= fees[2] <= 1439
        - fees[3] = 단위 요금
            - 0 <= fees[3] <= 100000
    - 1 <= records의 길이 <= 1000
        - 각 원소는 시각, 차량번호, 내역이 공백으로 구분되어 들어옴
        - 시각은 입차 or 출차 시각
        - 차랑 변호는 0 ~ 9로 구성된 길이 4인 문자열
        - 내역 IN 또는 OUT임.
        - 시각을 기준으로 오름차순으로 정렬되어 주어짐

풀이
1. 아이디어
    - LocalTime 형식의 배열을 만들어 입차시간, 출차시간을 저장\
        - Car : number, in, out 을 저장
    - List<Car>를 저장해 각 차량 번호의 입차, 출차시간을 저장
        - IN일 경우, add
        - OUT일 경우 입차한 기록을 찾아 해당 기록에 OUT 업데이트
    - 이후 Car의 number를 기준으로 모든 시간 합산
        - Map<Integer, Integer>를 통해 total time 계산
            - 해당 값을 TreeMap을 통해 차량번호 오름차순으로 정렬
    - 합산한 시간을 사용하여 가격 계산
        - 만약 주차 시간이 기본 시간보다 작으면 기본 요금만 저장
        - 기본 시간보다 크다면, 기본 요금 + (주차 시간 - 기본 시간) / 단위시간 * 단위 요금
    - 계산한 가격을 answer에 차례대로 저장
*/