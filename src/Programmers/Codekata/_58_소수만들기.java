package Programmers.Codekata;

import java.util.*;

public class _58_소수만들기 {

    class Solution {
        public int solution(int[] nums) {
            int answer = 0;
            boolean[] isPrime = new boolean[3000];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;
            for(int i = 2 ; i * i < 3000 ; i++){
                if(isPrime[i]){
                    for(int j = i * i; j < 3000 ; j += i){
                        isPrime[j] = false;
                    }
                }
            }

            for(int i = 0 ; i < nums.length - 2 ; i++){
                for(int j = i + 1 ; j < nums.length - 1 ; j++){
                    for(int k = j + 1 ; k < nums.length ; k++){
                        if(isPrime[nums[i] + nums[j] + nums[k]]){
                            answer++;
                        }
                    }
                }
            }

            return answer;
        }
    }

}
