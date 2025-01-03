package Programmers.Codekata;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class _38_직사각형별찍기 {

    class Solution_for {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());


            for(int i = 0 ; i < m ; i++){
                for(int j = 0 ; j < n ; j++){
                    sb.append('*');
                }
                sb.append("\n");
            }

            System.out.println(sb.toString());
        }
    }

    class Solution_stream {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            IntStream.range(0, m)
                    .forEach(i -> {
                        sb.append("*".repeat(n)).append("\n");
                    });

            System.out.println(sb.toString());
        }
    }
}
