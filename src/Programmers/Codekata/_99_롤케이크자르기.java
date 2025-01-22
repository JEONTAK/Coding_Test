package Programmers.Codekata;

import java.util.HashSet;
import java.util.Set;

public class _99_롤케이크자르기 {

    class Solution {
        public int solution(int[] topping) {
            int[] A = new int[topping.length];
            int[] B = new int[topping.length];
            Set<Integer> set = new HashSet<>();
            for (int i = 0, count = 0; i < topping.length; i++) {
                if (!set.contains(topping[i])) {
                    set.add(topping[i]);
                    count++;
                }
                A[i] = count;
            }

            set.clear();
            for (int i = topping.length - 1, count = 0; i >= 0; i--) {
                if (!set.contains(topping[i])) {
                    set.add(topping[i]);
                    count++;
                }
                B[i] = count;
            }

            int answer = 0;
            for (int i = 0; i < topping.length - 1; i++) {
                if (A[i] == B[i + 1]) {
                    answer++;
                }
            }

            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 철수는 롤케이크를 두 조각으로 잘라 동생과 한 조각씩 나눠 먹으려고 한다.
    - 해당 롤케이크에는 여러가지 토핑들이 일렬로 올려져 있다.
    - 철수와 동생은 롤케이크를 나눠먹으려 하는데, 그들은 크기보다 위에 올려진 토핑의 종류에 더 관심이 많다.
    - 그래서 잘린 조각들의 크기와 올려진 토핑의 개수에 상관없이, 각 조각에 동일한 가짓수의 토핑이 올라가면 공평하게 롤케이크가 나눠진 것으로 간주한다.
    
2. 목표
     - 롤케이크에 올려진 토핑들의 번호를 저장한 정수 배열이 주어질 때, 롤케이크를 공평하게 자르는 방법의 수를 return
     
3. 제약 조건
    - 1 <= 토핑의 길이 <= 1000000
        - 1 <= 토핑의 원소 <= 10000

풀이
1. 아이디어
    - 누적합을 사용해 토핑의 개수를 구하여 푼다
        - sum[i] = i번째 토핑까지 포함 했을 때의 토핑의 개수
            - 예를 들어 [1,2,2,3,1,4,1,2] 가 들어왔을 경우
                - 누적합은 A[] = [1,2,2,3,3,4,4,4]이다.
                - 거꾸로도 구해보면 B[] = [1,2,3,3,4,4,4,4] 이다.
                - 이걸 뒤집으면 [4,4,4,4,3,3,2,1]이 나온다.
                - A[] = [1,2,2,3,3,4,4,4]
                - B[] = [4,4,4,4,3,3,2,1]을 통해 구할 수 있겠다.
                - 여기서 i번째를 기준으로 자른다면, A[i]의 값과 B[i + 1]의 값이 같으면 동일한 양의 토핑이 들어가 있으므로, count++해준다.
        - 이후 count를 return한다.
*/