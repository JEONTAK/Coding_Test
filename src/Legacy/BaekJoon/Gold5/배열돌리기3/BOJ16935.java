package BaekJoon.Gold5.배열돌리기3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16935 {

    static int N, M, R;
    static int[][] arr;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            arr = compute(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int[][] compute(int r) {
        switch(r) {
            case 1:
                return updown();
            case 2:
                return leftright();
            case 3:
                return right90();
            case 4:
                return left90();
            case 5:
                return divideRight();
            case 6:
                return divideLeft();
            default:
                return arr;
        }
    }

    static int[][] updown(){
        int[][] temp = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                temp[i][j] = arr[arr.length - i - 1][j];
            }
        }
        return temp;
    }

    static int[][] leftright(){
        int[][] temp = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length; j++) {
                temp[j][i] = arr[j][arr[0].length - i - 1];
            }
        }
        return temp;
    }

    static int[][] right90() {
        int[][] temp = new int[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                temp[j][arr.length - i - 1] = arr[i][j];
            }
        }
        return temp;
    }

    static int[][] left90(){
        int[][] temp = new int[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                temp[arr[0].length - j - 1][i] = arr[i][j];
            }
        }
        return temp;
    }

    static int[][] divideRight() {
        int[][] temp = new int[arr.length][arr[0].length];
        int dN = arr.length / 2;
        int dM = arr[0].length / 2;
        for(int i = 0 ; i < dN ; i++) {
            for(int j = 0 ; j < dM ; j++) {
                temp[i][j + dM] = arr[i][j];
            }
        }
        for (int i = 0; i < dN; i++) {
            for (int j = dM; j < arr[0].length; j++) {
                temp[i + dN][j] = arr[i][j];
            }
        }
        for (int i = dN; i < arr.length; i++) {
            for (int j = dM; j < arr[0].length; j++) {
                temp[i][j - dM] = arr[i][j];
            }
        }
        for (int i = dN; i < arr.length; i++) {
            for (int j = 0; j < dM; j++) {
                temp[i - dN][j] = arr[i][j];
            }
        }
        return temp;
    }

    static int[][] divideLeft() {
        int[][] temp = new int[arr.length][arr[0].length];
        int dN = arr.length / 2;
        int dM = arr[0].length / 2;
        for (int i = 0; i < dN; i++) {
            for (int j = 0; j < dM; j++) {
                temp[i + dN][j] = arr[i][j];
            }
        }
        for (int i = dN; i < arr.length; i++) {
            for (int j = 0; j < dM; j++) {
                temp[i][j + dM] = arr[i][j];
            }
        }
        for (int i = dN; i < arr.length; i++) {
            for (int j = dM; j < arr[0].length; j++) {
                temp[i - dN][j] = arr[i][j];
            }
        }
        for (int i = 0; i < dN; i++) {
            for (int j = dM; j < arr[0].length; j++) {
                temp[i][j - dM] = arr[i][j];
            }
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BOJ16935.solution();
    }
}
