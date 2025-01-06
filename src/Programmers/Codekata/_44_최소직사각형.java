package Programmers.Codekata;

public class _44_최소직사각형 {
    class Solution {

        public int solution(int[][] sizes) {
            for(int i = 0 ; i < sizes.length ; i++){
                if(sizes[i][0] < sizes[i][1]){
                    int temp = sizes[i][0];
                    sizes[i][0] = sizes[i][1];
                    sizes[i][1] = temp;
                }
            }

            int width = 0;
            int height = 0;
            for(int i = 0 ; i < sizes.length ; i++){
                width = Math.max(width, sizes[i][0]);
                height = Math.max(height, sizes[i][1]);
            }

            return width * height;
        }
    }
}
