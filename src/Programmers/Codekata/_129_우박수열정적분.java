package Programmers.Codekata;

import java.util.ArrayList;
import java.util.List;

public class _129_우박수열정적분 {

    class Solution {

        List<Integer> collatz = new ArrayList<>();
        double[] size;

        public double[] solution(int k, int[][] ranges) {
            getCollatz(k);
            size = new double[collatz.size()];
            getSize();
            double[] answer = new double[ranges.length];

            for (int i = 0; i < ranges.length; i++) {
                int a = ranges[i][0];
                int b = collatz.size() + ranges[i][1] - 1;
                if (a > b) {
                    answer[i] = -1;
                }else{
                    answer[i] = computeSize(a, b);
                }
            }
            return answer;
        }

        private double computeSize(int a, int b) {
            double sum = 0;
            for (int i = a; i < b; i++) {
                sum += size[i];
            }
            return sum;
        }

        private void getSize() {
            for (int i = 0; i < collatz.size() - 1; i++) {
                size[i] = (double) (collatz.get(i) + collatz.get(i + 1)) / 2;
            }
        }

        private void getCollatz(int k) {
            while (k != 1) {
                collatz.add(k);
                if (k % 2 == 0) {
                    k /= 2;
                }else{
                    k = k * 3 + 1;
                }
            }
            collatz.add(k);
        }
    }

}

/*
문제 분석
1. 정보
    - 콜라츠 추측이란 로타르 콜라츠가 제기한 추측으로 모든 자연수 k에 대해 다음 작업을 반복하면 항상 1로 만들 수 있다는 추측
    - 규칙은 다음과 같음
        - 1. 입력된 수가 짝수라면 2로 나눔
        - 1. 입력된 수가 홀수라면 3을 곱하고 1을 더함
        - 2. 결과로 나온 수가 1보다 크다면 1번 작업을 반복
    - 은지는 콜라즈 추측(우박수열)을 좌표 평면 위에 꺾은선 그래프로 나타내 보려고 함
    - 초항이 k인 우박수열이 있다면, x = 0 일때 y = k이고 다음 우박수는 x = 1에 표시함.
    - 이런 식으로 우박수가 1이 될 때까지 점들을 찍고 직선으로 연결하여 꺾은선 그래프를 만들음.
    - 또한 은지는 이렇게 만든 꺾은선 그래프를 정적분 해보고 싶음
    - x에 대한 어떤 범위 [a,b]가 주어진다면 이 범위에 대한 정적분 결과는 꺾은선 그래프와 x = a, x = b, y = 0으로 둘러 쌓인 공간의 면적과 같음
    - 은지는 이것을 우박수열 정적분이라고 정의하였고 다양한 구간에서 정적분을 해보려고 함.
    - 0 이상의 수 b에 대해 [a, -b] 에 대한 정적분 결과는 x = a, x = n -b, y = 0으로 둘러 쌓인 공간의 면적으로 정의하며, 이때 n은 k가 초항인 우박수열이 1이 될때 까지의 횟수를 의미

2. 목표
    - 우박수의 초항 k와 정적분을 구하는 구간들의 목록 ranges가 주어질 때 결과 목록을 return
    
3. 제약 조건
    - 2 <= k <= 10000
    - 1 <= ranges <= 10000
        - range의 원소는 [a,b] 형식이며 0 <= a < 200, -200 < b <= 0
    - 모든 입력에 대해 정적분의 결과는 2^27을 넘지 않음
    

풀이
1. 아이디어
    - 일단 정적분을 하기 위해선 우박수열을 구해야 함
    - 따라서 k가 규칙을 통한 과정을 거쳐 1이 될때까지 좌표 값을 구함
    - 좌표값을 구한 이후
        - 0부터 200까지의 각 (i, i + 1)에 해당하는 크기를 구하여 갑을 저장
        - ranges 배열을 돌면서
            - a ~ n - b까지의 크기를 result배열에 저장
*/
