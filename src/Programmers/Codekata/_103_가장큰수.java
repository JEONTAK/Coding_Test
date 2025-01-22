package Programmers.Codekata;

import java.util.Arrays;

public class _103_가장큰수 {

    class Solution {
        public String solution(int[] numbers) {
            String[] nums = new String[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                nums[i] = String.valueOf(numbers[i]);
            }

            Arrays.sort(nums, ((o1, o2) -> {
                int left = Integer.parseInt(o1 + o2);
                int right = Integer.parseInt(o2 + o1);
                return right - left;
            }));

            StringBuilder sb = new StringBuilder();

            for (String num : nums) {
                sb.append(num);
            }

            return sb.toString().startsWith("0") ? "0" : sb.toString();
        }
    }

}

/*
문제 분석
1. 정보
    - 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 만들어 return
2. 목표
    - 0 또는 양의 정수가 담긴 배열이 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 만들어 return
3. 제약 조건
    - 1 <= 배열의 길이 <= 100000
    - 0 <= 배열의 원소 <= 1000
    - 문자열로 바꿔 return

풀이
1. 아이디어
    - 배열의 길이가 최대 100000만이라 브루트포스를 사용할 수는 없다고 판단.
    - 결국 얼마나 앞에 큰 숫자 순서대로 넣냐가 관건
        - 하지만 9 와 10이 있을 경우, 앞에 9를 넣어야 숫자가 더 커짐
        - 그렇다면 910 과 109를 비교하여 더 큰쪽이 앞으로 가게?
            - 즉, 두 숫자를 이어 붙여 더 큰 숫자가 앞으로 갈 수 있도록 정렬
    - 주의할 점 : 가장 앞의 숫자가 0이라면, 0을 return 해주어야함.
*/
