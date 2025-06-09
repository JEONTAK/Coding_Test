import java.util.*;

class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int N = beginning.length;
        int M = beginning[0].length;
        int answer = Integer.MAX_VALUE;
    
        // 첫 번째 행의 뒤집기 패턴 (0 ~ 2^N-1)
        for (int rowMask = 0; rowMask < (1 << N); rowMask++) {
            int[][] temp = copyArray(beginning, N, M); // 초기 상태 복사
            int flipCount = 0;
        
            // 1. 첫 번째 행의 뒤집기 적용
            for (int i = 0; i < N; i++) {
                if ((rowMask & (1 << i)) > 0) {
                    flipRow(temp, i, M); // i번째 행 뒤집기
                    flipCount++;
                }
            }
        
            // 2. 첫 번째 행이 target과 일치하도록 열 뒤집기
            for (int j = 0; j < M; j++) {
                if (temp[0][j] != target[0][j]) {
                    flipColumn(temp, j, N); // j번째 열 뒤집기
                    flipCount++;
                }
            }
        
            // 3. 나머지 행들이 target과 일치하는지 확인
            if (isEqual(temp, target, N, M)) {
                answer = Math.min(answer, flipCount);
            }
        }
    
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    // 행 뒤집기
    private void flipRow(int[][] arr, int row, int M) {
        for (int j = 0; j < M; j++) {
            arr[row][j] ^= 1; // 0 ↔ 1
        }
    }

    // 열 뒤집기
    private void flipColumn(int[][] arr, int col, int N) {
        for (int i = 0; i < N; i++) {
            arr[i][col] ^= 1; // 0 ↔ 1
        }
    }

    // 배열 복사
    private int[][] copyArray(int[][] src, int N, int M) {
        int[][] dest = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, M);
        }
        return dest;
    }

    // 배열 일치 확인
    boolean isEqual(int[][] arr1, int[][] arr2, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] != arr2[i][j]) return false;
            }
        }
        return true;
    }
}