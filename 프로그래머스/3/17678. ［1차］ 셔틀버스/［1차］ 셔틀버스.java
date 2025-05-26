import java.util.*;

class Solution {
    
    class Bus {
        int time;
        int max;
        List<Integer> seatList;
        
        public Bus (int time, int max, List<Integer> seatList) {
            this.time = time;
            this.max = max;
            this.seatList = seatList;
        }
    }
    
    public String solution(int n, int t, int m, String[] timetable) {
        Bus[] busList = new Bus[n];
        //버스 첫 배차 시간 -> 09:00
        int curTime = 540;
        //배차 작성
        for(int i = 0; i < n; i++) {
            busList[i] = new Bus(curTime, m, new ArrayList<>());
            curTime += t;
        }
        
        //23:59분터 순서대로 탑승 가능하게 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i = 0 ; i < timetable.length; i++) {
            String[] split = timetable[i].split(":");
            curTime = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]); 
            pq.add(curTime);
        }
        
        int curBusIdx = 0;
        int curSeatCount = 0;
        
        //버스 배치에 따라 집어 넣음
        while(!pq.isEmpty()) {
            int curCrew = pq.poll();
            
            if(busList[curBusIdx].time >= curCrew) {
                busList[curBusIdx].seatList.add(curCrew);
                curSeatCount++;
                if(curSeatCount == m) {
                    curBusIdx++;
                    curSeatCount = 0;
                    if(curBusIdx == n) {
                        break;
                    }
                }
            }else {
                pq.add(curCrew);
                curBusIdx++;
                curSeatCount = 0;
                if(curBusIdx == n) {
                    break;
                }
            }
           
        }
        
        //마지막 배차 버스 리스트 가져오기
        List<Integer> lastBus = new ArrayList<>(busList[n - 1].seatList);
        
        String answer = "";
        
        //만약 마지막 버스가 꽉차있지 않다면 해당 버스 출발 시간으로 설정
        if(lastBus.size() != m) {
            int hour = busList[n - 1].time / 60;
            int minute = busList[n - 1].time % 60;
            answer = String.format("%02d:%02d", hour, minute);
        } else { //꽉 차 있다면 가장 마지막 탑승하는 사람의 시간 - 1분을 결과 값으로 설정.
            int temp = lastBus.get(lastBus.size() - 1);
            if(temp == 0){
                temp = 23*60 + 59;
            } else {
                temp--;
            }
            int hour = temp / 60;
            int minute = temp % 60;
            answer = String.format("%02d:%02d", hour, minute);
        }
        
        return answer;
    }
}

/*
일단 총 운행 횟수 n, 운행 간격 t, 버스 탑승 인원 m을 가지고 배차를 만든다.
예를 들어, 1, 1, 5이면
배차는 9시에 5명을 태울 수 있다.
2 10 2면
9시에 2명 9시 10분에 2명을 태울 수 있다.
이런 식으로 Bus class를 만들어 저장하기
해당 버스에 List<String>으로 몇시에 도착한 사람이 탔는지 저장.

timetable에는 각 크루가 도착하는 시간이 적혀 있음.
23:59분에는 집에 돌아가기 때문에, 23:59분 부터 줄을 설 수 있다.
23:59분부터 차례대로 줄을 서도록 할 수 있음.

콘은 최대한 늦게 타고 싶기 때문에, 최대한 마지막 배차를 타야 한다.
일단 timetable을 이용하여 버스에 크루들을 태운다. 
버스에 태운 결과를 가지고, 마지막 버스 배차에 들어가는 경우중 최대한 늦은 시간을 구한다.
1. 최대 버스 정원보다 탄 사람이 적다면, 해당 버스 출발 시간이 answer
2. 꽉 차 있다면, 가장 늦게 온 사람보다 1분 일찍 와서 타기
*/