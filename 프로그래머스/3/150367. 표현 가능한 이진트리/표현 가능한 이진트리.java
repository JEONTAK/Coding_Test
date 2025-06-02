import java.util.*;

class Solution {
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            String current = decimalToBinary(numbers[i]);
            int length = current.length();
            long curDepth = findDepth(current);

            StringBuilder temp = new StringBuilder();
            for(int j = 0; j < curDepth - length; j++) {
                temp.append("0");
            }

            current = temp.toString() + current;
            length = current.length();

            if(current.charAt(length / 2) == '0') {
                answer[i] = 0;
                continue;
            }

            if(canMakeBTree(current)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }

        }

        return answer;
    }

    private String decimalToBinary(long number) {
        StringBuilder sb = new StringBuilder();

        while(number > 0) {
            long cur = number % 2;
            sb.append(cur);
            number /= 2;
        }

        return sb.reverse().toString();
    }

    private boolean canMakeBTree(String current) {
        int left = 0;
        int right = current.length() - 1;

        if(!current.contains("1")) {
            return true;
        }

        if(current.length() <= 3) {
            if(current.charAt((left + right) / 2) == '0') {
                if(current.contains("1")) {
                    return false;
                }
            }
            return true;
        }

        if(current.charAt((left + right) / 2) == '0') {
            if(current.contains("1")) {
                return false;
            } else {
                return true;
            }
        }

        String leftStr = current.substring(0 , (left + right) / 2);
        String rightStr = current.substring((left + right) / 2 + 1);
        boolean flag = true;
        flag = canMakeBTree(leftStr);
        if(!flag) {
            return flag;
        }
        flag = canMakeBTree(rightStr);
        return flag;
    }

    private long findDepth(String current) {
        long node = 1;
        int cnt = 2;
        long length = current.length();
        while(true) {
            if(length > node) {
                node += cnt;
            } else {
                return node;
            }
            cnt *= 2;
        }
    }
}

/*
7 -> 111
5 -> 101 루트가 0이므로 불가능
95 -> 1011111 왼쪽 부모노드가 0이므로 불가능
42 -> 32 + 8 + 2 -> 0101010
63 -> 32 + 16 + 8 + 4 + 2 + 1 -> 0111111

이진수로 변환했을때 개수가 짝수면 앞에 0을 추가하여 홀수개로 만들음 (X) 개수가 1, 2, 4, 8, 16 --- 2의 제곱수로 만들어야 함.
-> 가장 중앙 값이 0이면 0
-> 홀수번쨰에 0이 들어가 있으면 0

실패 -> 홀수번째에 0이 들어가 있어도 성공 가능성이 존재
-> 중앙값 기준으로 좌우로 나눠서 또 해야할듯
-> 만약 0001000 이면
-> 중앙값 기준으로 0 ~ 2 / 4 ~ 6로 나눔
-> 0 ~ 2에서 중앙값이 1이면 또 나눔
-> 만약 0인데, 아래에 1이 있다면 false

-> 1. current 값이 들어옴
-> 1. current의 길이가 3이다 -> 중앙값이 0이다 -> 아래 노드에 1이 있다면 false / 나머지 경우는 true
-> 2. 중앙값이 0이다 -> 아래 노드에 1이 있는지 확인 -> 1이 있다면 false
-> 3. 중앙값이 1이다 -> 양쪽으로 나누어 똑같이확인


*/