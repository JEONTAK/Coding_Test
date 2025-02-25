package Programmers.Codekata;

import java.util.Arrays;

public class _136_요격시스템 {

    class Solution {
        public int solution(int[][] targets) {

            Arrays.sort(targets, ((o1, o2) -> Integer.compare(o1[1], o2[1])));
            int answer = 1;
            int curEnd = targets[0][1];

            for (int i = 1; i < targets.length; i++) {
                if (canInterception(curEnd, targets[i])) {
                    continue;
                }

                answer++;
                curEnd = targets[i][1];
            }
            return answer;
        }

        private boolean canInterception(int curEnd, int[] target) {
            return curEnd > target[0];
        }
    }

}

/*
문제 분석
1. 정보
    - A 나라가 B 나라를 침공. B 나라의 대부분의 전략 자원은 아이기스 군사기지에 집중되어 있기 때문에 A 나라는 B 나라의 아이기스 군사 기지에 융단 폭격을 가했음.
    - A 나라의 공격에 대항하여 아이기스 군사 기지 에서는 무수히 쏟아지는 폭격 미사일들을 요격하려고 함
    - 이곳에는 백발백중을 자랑하는 요격 시스템이 있지만 운용 비용이 상당하기 때문에 미사일을 최소로 사용해서 모든 폭격 미사일을 요격하려 함.
    - A 나라와 B 나라가 싸우고 있는 이 세계는 2차원 공간으로 이루어짐
    - A 나라가 발사한 폭격 미사일은 x 축에 평행한 직선 형태의 모양이며 개구간을 나타내는 정수 쌍 (s,e) 형태로 표현 됨
    - B 나라는 특정 x 좌표에서 y축에 수평이 되도록 미사일을 발사하며, 발사된 미사일은 해당 x 좌표에 걸쳐있는 모든 폭격 미사일을 관통하여 한 번에 요격할 수 있음
    - 단 ,개구간 (s,e)로 표현되는 폭격 미사일은 s와 e에서 발사하는 요격 미사일로는 요격할 수 없음
    - 요격 미사일은 실수인 x 좌표에서도 발사 가능
2. 목표
    - 각 폭격 미사일의 x 좌표 범위 목록 targets이 매개 변수로 주어질 때, 모든 폭격 미사일을 요격하기 위해 필요한 요격 미사일 수의 최솟값을 return

3. 제약 조건
    - 1 <= targets의 길이 <= 500000
    - targets의 각 행은 [s,e] 형태
        - 한 폭격 미사일의 x 좌표 범위를 나타내며, 개구간 (s,e)에서 요격해야 함.
        - 0 <= s < e <= 100,000,000

풀이
1. 아이디어
    - 끝점 기준 오름차순으로 정렬
    - 가장 왼쪽 끝점부터 시작
        - 현재 구간과 겹치는 모든 구간을 처리
        - 다음 구간이 겹치지 않는다면, 해당 구간을 기준으로 다시 탐색(미사일 발사 개수++)
        - 모든 구간을 탐색한 후 미사일 발사 개수를 return
*/