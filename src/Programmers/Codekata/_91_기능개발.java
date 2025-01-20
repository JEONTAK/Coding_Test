package Programmers.Codekata;

import java.util.ArrayList;
import java.util.List;

public class _91_기능개발 {

    public static void main(String[] args) {
        Solution.solution(new int[]{93, 30, 55}, new int[]{1, 30, 5});
        Solution.solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1,1,1,1,1,1});
    }

    static class Solution {
        public static int[] solution(int[] progresses, int[] speeds) {
            for (int i = 0; i < progresses.length; i++) {
                int remain = 100 - progresses[i];
                int time = 0;
                if (remain % speeds[i] == 0) {
                    time = remain / speeds[i];
                }else{
                    time = remain / speeds[i] + 1;
                }
                progresses[i] = time;
            }

            int[] result = new int[101];
            result[progresses[0]]++;

            for (int i = 1; i < progresses.length; i++) {
                if (progresses[i - 1] > progresses[i]) {
                    progresses[i] = progresses[i - 1];
                }
                result[progresses[i]]++;
            }

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < result.length; i++) {
                if (result[i] > 0) {
                    list.add(result[i]);
                }
            }

            int[] answer = new int[list.size()];

            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
            return answer;
        }
    }
}

/*
문제 분석
1. 정보
    - 기능 개선 작업을 수행중. 각 기능은 진도가 100%일 때 서비스에 반영이 가능
    - 각 기능의 개발속도는 모두 다르기 때문에, 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포 됨
    - 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 데이터가 주어지고, 각 작업의 개발 속도가 적힌 데이터가 주어짐

2. 출력
    - 각 배포마다 몇 개의 기능이 배포되는지를 return

3. 제약 조건
    - 작업의 개수는 100개 이하
    - 1 <= 작업 진도 < 100
    - 1 <= 작업 속도 <= 100
    - 배포는 하루에 한번만 가능, 하루의 끝에 배포가 가능
        - 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 2일 뒤에 이루어짐.

풀이
1. 아이디어
    - 각 진도의 남은 진도율을 속도로 나누어 남은 시간을 구함
        - 만약 남은 진도율이 7이고, 속도가 3이라면, 7 / 3 = 2 + 1 = 3 3일이 필요함
        - 이때, 속도로 나누었을때 나머지가 0이라면 +1을 해주지 않고, 나머지가 존재한다면 +1을 해줌
        - 해당 데이터를 배열에 저장
        - 앞에서 순차적으로 본인의 왼쪽보다 자신이 작다면, 왼쪽 값으로 업데이트하고, 날짜 배열을 생성해 해당 값에 +1해줌
            - 날짜 배열은 해당 날짜에 배포하는 작업의 개수를 담음
        - 이후 날짜 배열 값이 0이 아니라면, list에 저장
        - 저장한 list를 배열로 바꾸어 return
*/