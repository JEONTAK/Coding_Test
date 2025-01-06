package BaekJoon.Silver5.그룹단어체커;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1316 {

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        for (int i = 0; i < N; i++) {
            String curStr = br.readLine();
            boolean[] alphabet = new boolean[26];
            boolean flag = true;
            for (int j = 0; j < curStr.length(); j++) {
                if(!alphabet[curStr.charAt(j) - 'a']){
                    alphabet[curStr.charAt(j) - 'a'] = true;

                }else{
                    if(curStr.charAt(j - 1) != curStr.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) result++;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ1316.solution();
    }
}
/*
문제 분석

1. 정보
    - 그룹 단어 : 단어에 존재하는 모든 문자에 대해서 각 문자가 연속해서 나타나는 경우
        - ex : ccazzzzbb -> c,a,z,b가 모두 연속해서 나타남 -> 그룹 단어 O
        - ex : aabbbccb -> b가 떨어져서 나타남 -> 그룹 단어 X
2. 목표
    - 단어 N개를 입력 받아 그룹 단어의 개수 출력
    
3. 제약 조건
    - N : 1 <= N <= 100
    - 단어는 알파벳 소문자로만 구성
    - 단어의 길이 최대 100

풀이

1. 탐색 과정
    - 최대 100 * 100 = 10000이어서 for문 사용해도 괜찮을 것이라 생각
    - 따라서 0 ~ N까지 for문으로 구현
    - boolean[] alphabet 생성
        - 해당 단어의 처음부터 끝까지 체크
            - 만약 알파벳이 이전에 들어오지 않았다면,
                - alphabet[해당 알파벳] = true;
                - 해당 알파벳 끝날때까지 자리 옮김
            - 만약 알파벳이 이전에 들어왔다면, 그룹 단어 X -> 다음 단어로 넘어감
        - 해당 단어의 끝까지 체크 되었다면 그룹단어++;
    - 결과 return
*/

