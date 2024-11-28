package BaekJoon.Gold2.오름세;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3745 {

    static int N;
    static int[] seq, result;

    private static void solution() throws IOException {
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            String input = null;
            while ((input = br.readLine()) != null) {
                N = Integer.parseInt(input.trim());
                seq = new int[N];
                result = new int[N];
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < N; i++) {
                    seq[i] = Integer.parseInt(st.nextToken());
                }
                result[0] = seq[0];
                int len = 1;
                for (int i = 1; i < N; i++) {
                    int cur = seq[i];
                    if (result[len - 1] < cur) {
                        len++;
                        result[len - 1] = cur;
                    } else {
                        int left = 0;
                        int right = len;
                        while (left < right) {
                            int mid = (left + right) / 2;
                            if (result[mid] < cur) {
                                left = mid + 1;
                            } else {
                                right = mid;
                            }
                        }
                        result[left] = cur;
                    }
                }
                sb.append(len + "\n");
            }
        }catch (Exception e){

        }finally {
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ3745.solution();
    }
}

//최장 증가 수열을 구하는 알고리즘과 동일 하였다.