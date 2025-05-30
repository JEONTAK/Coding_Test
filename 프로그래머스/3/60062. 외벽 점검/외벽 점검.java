import java.util.*;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int weakLen = weak.length;
        // 1. weak 배열을 두 배로 확장
        int[] extendedWeak = new int[weakLen * 2];
        for (int i = 0; i < weakLen; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + weakLen] = weak[i] + n;
        }
        
        // 2. dist 내림차순 정렬
        Integer[] distBoxed = Arrays.stream(dist).boxed().toArray(Integer[]::new);
        Arrays.sort(distBoxed, Collections.reverseOrder());
        dist = Arrays.stream(distBoxed).mapToInt(Integer::intValue).toArray();
        
        int answer = Integer.MAX_VALUE;
        
        // 3. 모든 시작 지점 탐색
        for (int start = 0; start < weakLen; start++) {
            // 4. 모든 dist 순열 탐색
            List<int[]> permutations = getPermutations(dist);
            for (int[] perm : permutations) {
                int friendCount = 1; // 현재 사용하는 친구 수
                int pos = extendedWeak[start] + perm[0]; // 첫 친구가 커버할 수 있는 최대 위치
                int weakIndex = start + 1; // 다음 점검할 취약 지점
                
                // 5. 모든 취약 지점을 커버할 때까지
                while (weakIndex < start + weakLen) {
                    if (pos < extendedWeak[weakIndex]) {
                        friendCount++;
                        if (friendCount > perm.length) break; // 친구 부족
                        pos = extendedWeak[weakIndex] + perm[friendCount - 1];
                    }
                    weakIndex++;
                }
                
                // 모든 취약 지점 커버 시 최소 친구 수 갱신
                if (weakIndex == start + weakLen) {
                    answer = Math.min(answer, friendCount);
                }
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    // 순열 생성
    private List<int[]> getPermutations(int[] arr) {
        List<int[]> result = new ArrayList<>();
        boolean[] used = new boolean[arr.length];
        int[] current = new int[arr.length];
        generatePermutations(arr, used, current, 0, result);
        return result;
    }
    
    private void generatePermutations(int[] arr, boolean[] used, int[] current, int depth, List<int[]> result) {
        if (depth == arr.length) {
            result.add(current.clone());
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current[depth] = arr[i];
                generatePermutations(arr, used, current, depth + 1, result);
                used[i] = false;
            }
        }
    }
}