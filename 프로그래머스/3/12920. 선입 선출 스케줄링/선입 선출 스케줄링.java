import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        
        if(n < cores.length) {
            return n;
        }
        
        int maxCore = 0;
        for(int core : cores) {
            maxCore = Math.max(maxCore, core);
        }
        int right = maxCore * n;
        int left = 0;
        int time = 0;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            int curN = computeWork(mid, cores);
            if(curN >= n){
                time = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        int prevCount = 0;
        
        for(int core : cores) {
            prevCount += (time - 1) / core + 1;
        }
        
        int remain = n - prevCount;
        for(int i = 0 ; i < cores.length ; i++) {
            if(time % cores[i] == 0) {
                remain--;
                if(remain == 0) {
                    return i + 1;
                }
            }
        }
        
        return -1;
    }
    
    private int computeWork(int curN, int[] cores) {
        int sum = 0;
        for(int core : cores) {
            sum += (curN / core) + 1; 
        }
        return sum;
    }
}

/*
코어별 처리시간이 다름

먼저 n이 코어의 개수보다 작다면, n을 return

이분탐색을 통해 구하면 될듯
처리시간은 n * 최댓값
left와 right로 나누어 이분탐색 진행
mid값을 시간으로 주어졌을때, 해당 시간에 처리할수 있는 작업의 수를 구함
해당 수가 n보다 크다면 right = mid - 1
해당 수가 n보다 작다면 left = mid + 1
mid -> 해당 시간에 모든 작업이 완료 가능


*/