package BaekJoon.Gold2.모노미노도미노2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20061 {

    static int N;
    static int[][] green = new int[6][4];
    static int[][] blue = new int[4][6];
    static int result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            set(t, x, y);
        }

        int sum = sumOfMap();
        System.out.println(result);
        System.out.println(sum);
    }

    static void set(int t, int x, int y){
        if(t == 1){
            setBlue(x, 1);
            setGreen(y, 1);
        }else if(t == 2){
            setBlue(x, 1);
            setGreen(y, 2);
            setBlue(x, 1);
        }else{
            setBlue(x, 2);
            setGreen(y, 1);
            setGreen(y, 1);
        }

        computeBlue();
        computeGreen();
    }

    static void setBlue(int x, int len){
        for(int i = x ; i < x + len ; i++){
            if (blue[i][0] == 1) {
                return;
            }
        }

        for(int i = 1 ; i < blue[x].length ; i++){
            for(int j = x ; j < x + len ; j++){
                if(blue[j][i] == 1){
                    for(int k = x ; k < x + len ; k++){
                        blue[k][i - 1] = 1;
                    }
                    return;
                }
            }
        }

        for(int i = x ; i < x + len ; i++){
            blue[i][5] = 1;
        }
    }

    static void setGreen(int y, int len){
        for(int i = y ; i < y + len ; i++){
            if (green[0][i] == 1) {
                return;
            }
        }
        for(int i = 1 ; i < green.length ; i++){
            for(int j = y ; j < y + len ; j++){
                if(green[i][j] == 1){
                    for(int k = y ; k < y + len ; k++){
                        green[i - 1][k] = 1;
                    }
                    return;
                }
            }
        }

        for(int i = y ; i < y + len ; i++){
            green[5][i] = 1;
        }
    }

    static void computeBlue(){
        for(int i = blue[0].length - 1 ; i >= 0 ; i--){
            int sum = 0;
            for(int j = 0 ; j < blue.length; j++){
                if(blue[j][i] == 1){
                    sum++;
                }
            }
            if(sum == 4){
                result++;
                for(int k = i;  k > 0 ; k--){
                    for(int j = 0 ; j < blue.length ; j++){
                        int temp = blue[j][k];
                        blue[j][k] = blue[j][k - 1];
                        blue[j][k - 1] = temp;
                    }
                }
                for(int j = 0 ; j < blue.length ; j++){
                    blue[j][0] = 0;
                }
                i++;
            }
        }

        for(int i = 0 ; i < 2 ; i++){
            for(int j = 0 ; j < blue.length ; j++){
                if(blue[j][i] == 1){
                    int t = i == 0 ? 2 : 1;
                    for(int k = blue[0].length - 1 ; k >= t ; k--){
                        for(int l = 0 ; l < blue.length ; l++){
                            int temp = blue[l][k];
                            blue[l][k] = blue[l][k - t];
                            blue[l][k - t] = temp;
                        }
                    }
                    for(int k = 0 ; k < t ; k++){
                        for(int l = 0 ; l < blue.length ; l++){
                            blue[l][k] = 0;
                        }
                    }
                    break;
                }
            }
        }
    }

    static void computeGreen(){
        for(int i = green.length - 1 ; i >= 0 ; i--){
            int sum = 0;
            for(int j = 0 ; j < green[i].length; j++){
                if(green[i][j] == 1){
                    sum++;
                }
            }
            if(sum == 4){
                result++;
                for(int k = i;  k > 0 ; k--){
                    for(int j = 0 ; j < green[k].length ; j++){
                        int temp = green[k][j];
                        green[k][j] = green[k - 1][j];
                        green[k - 1][j] = temp;
                    }
                }
                Arrays.fill(green[0], 0);
                i++;
            }
        }

        for(int i = 0 ; i < 2 ; i++){
            for(int j = 0 ; j < green[i].length ; j++){
                if(green[i][j] == 1){
                    int t = i == 0 ? 2 : 1;
                    for(int k = green.length - 1 ; k >= t ; k--){
                        for(int l = 0 ; l < green[k].length ; l++){
                            int temp = green[k][l];
                            green[k][l] = green[k - t][l];
                            green[k - t][l] = temp;
                        }
                    }
                    for(int k = 0 ; k < t ; k++){
                        Arrays.fill(green[k], 0);
                    }
                    break;
                }
            }
        }
    }

    static int sumOfMap(){
        int sum = 0;
        for(int i = 0 ; i < blue.length ; i++){
            for(int j = 0 ; j < blue[0].length ; j++){
                if (blue[i][j] == 1) {
                    sum++;
                }
            }
        }

        for(int i = 0 ; i < green.length ; i++){
            for(int j = 0 ; j < green[0].length ; j++){
                if (green[i][j] == 1) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BOJ20061.solution();
    }
}
//단순한 구현 문제이다.
//단 주의할 점은 2 X 1이나 1 X 2 형태의 블록은 두 위치중 하나만 걸리더라도 해당위치에 놓여야 하기때문에 해당 조건을 잘 구현해야 할듯 하다.
