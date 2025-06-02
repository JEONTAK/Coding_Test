import java.util.*;

class Solution {
    
    private Map<Integer, List<Integer>> map = new HashMap<>();
    
    public int solution(int N, int number) {
        for(int i = 1 ; i <= 8 ; i++) {
            List<Integer> list = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for(int j = 0 ; j < i ; j++) {
                sb.append(N);
            }
            list.add(Integer.parseInt(sb.toString()));
            map.put(i, list);
        }
        
        if(map.get(1).contains(number)) {
            return 1;
        }
        
        return compute(N, 2, number);
    }
    
    private int compute(int N, int idx, int number) {
        List<Integer> curList = map.get(idx);
        
        if(idx > 8) return -1;
        //3 -> 2
        //4 -> 3, 2
        for(int i = idx - 1; i > idx / 2 - 1 ; i--) {
            System.out.println(i + " " + (idx - i));
            List<Integer> leftList = map.get(i);
            List<Integer> rightList = map.get(idx - i);
            
            for(int left : leftList) {
                for(int right : rightList) {
                    curList.add(left * right);
                    curList.add(left + right);
                    curList.add(left - right);
                    curList.add(right - left);
                    if(right != 0) {
                        curList.add(left / right);
                    }
                    if(left != 0) {
                        curList.add(right / left);
                    }
                }
            }
            System.out.println();
        }
        
        if(curList.contains(number)) {
            return idx;
        }
        
        map.put(idx, curList);
        
        return compute(N, idx + 1, number);
    }
}

/*
dp방식
이전 횟수의 값들을 사용해서 곱하기, 더하기, 뺴기, 나누기를 진행
Map<Integer, List<Integer>> map을 통해 Integer 횟수에 해당하는 값들을 리스트로 저장
i -> 3일때는
map.get(i - 1)을 가져와 해당 값에 곱한 값, 더한값 ,뺀 값, 나눈 값을 저장함.
해당 값에 number가 존재한다면 i값을 바로 return 만약 8이 되어서도 값이 나타나지 않는다면, -1을 return
*/