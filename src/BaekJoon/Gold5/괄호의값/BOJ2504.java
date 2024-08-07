package BaekJoon.Gold5.괄호의값;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ2504 {

    static char[] input;
    static Stack<Character> s = new Stack<>();
    static int temp = 1, result = 0;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                s.push(input[i]);
                temp *= 2;
            }else if(input[i] == '['){
                s.push(input[i]);
                temp *= 3;
            }else if(input[i] == ')'){
                if(s.isEmpty() || s.peek() != '('){
                    result = 0;
                    break;
                } else if (input[i - 1] == '(') {
                    result += temp;
                }
                s.pop();
                temp /= 2;
            } else if (input[i] == ']') {
                if(s.isEmpty() || s.peek() != '[') {
                    result = 0;
                    break;
                }else if(input[i - 1] == '['){
                    result += temp;
                }
                s.pop();
                temp /= 3;
            }
        }
        if(!s.isEmpty()){
            result = 0;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ2504.solution();
    }
}
