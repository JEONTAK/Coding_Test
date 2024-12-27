package Programmers.Codekata;

public class _19_정수제곱근판별 {

    class Solution {
        public long solution(long n) {
            double root = Math.sqrt(n);
            long root_int = (long)root;
            if(root != root_int){
                return -1;
            }
            return (long)Math.pow(root_int + 1, 2);
        }
    }
}
