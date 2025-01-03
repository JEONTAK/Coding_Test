package Programmers.Codekata;

import java.util.stream.*;

public class _37_행렬의덧셈 {

    class Solution_for {
        public int[][] solution(int[][] arr1, int[][] arr2) {
            int[][] answer = new int[arr1.length][arr1[0].length];

            for(int i = 0 ; i < answer.length ; i++){
                for(int j = 0 ; j < answer[i].length ; j++){
                    answer[i][j] = arr1[i][j] + arr2[i][j];
                }
            }

            return answer;
        }
    }

    class Solution_stream {
        public int[][] solution(int[][] arr1, int[][] arr2) {
            return IntStream.range(0, arr1.length)
                    .mapToObj(i -> IntStream.range(0, arr1[i].length)
                            .map(j -> arr1[i][j] + arr2[i][j])
                            .toArray())
                    .toArray(int[][]::new);
        }
    }
}
