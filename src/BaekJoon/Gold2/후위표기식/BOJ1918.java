package BaekJoon.Gold2.후위표기식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1918 {

    static Stack<Character> s = new Stack<>();
    static StringBuilder sb = new StringBuilder();

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp = br.readLine();
        for (int i = 0; i < temp.length(); i++) {
            char cur = temp.charAt(i);
            if (cur == '+' || cur == '-' || cur == '*' || cur == '/') {
                while (!s.isEmpty() && operation(s.peek()) >= operation(cur)) {
                    sb.append(s.pop());
                }
                s.add(cur);
            } else if (cur == '(') {
                s.add(cur);
            } else if (cur == ')') {
                while (!s.isEmpty() && s.peek() != '(') {
                    sb.append(s.pop());
                }
                s.pop();
            } else{
                sb.append(cur);
            }
        }
        while(!s.isEmpty()){
            sb.append(s.pop());
        }
        System.out.println(sb.toString());
    }

    static int operation(char cur) {
        if(cur == '(' || cur == ')'){
            return 0;
        } else if(cur == '+' || cur == '-'){
            return 1;
        } else if (cur == '*' || cur == '/') {
            return 2;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BOJ1918.solution();
    }
}

//해결 핵심 내용은 Stack에는 연산자만 저장하고, 숫자는 바로 StringBuilder에 넣는것!
//현재 char가 숫자(알파벳)일 경우 바로 StringBuilder에 넣는다.
//현재 char가 연산자일 경우
//      연산자의 우선순위는 *, / -> +, - -> ( )이다.
//      따라서 우선순위가 높은 것 부터 처리를 해주어야 한다.
//      연산자를 stack에 저장해놓고, 현재의 연산자보다 우선순위가 높은 연산자가 있을때까지 stack에서 pop한다.
//      만약 )일 경우에는 (가 나올때 까지 StringBuilder에 넣는다. 어차피 숫자(알파벳)은 이미 StringBuilder에 들어가 있고, stack에는 연산자만 남아있기 때문이다.
//      (를 stack에서 제거한다.

