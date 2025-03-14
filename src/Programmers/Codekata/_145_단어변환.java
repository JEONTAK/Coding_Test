package Programmers.Codekata;

import java.util.LinkedList;
import java.util.Queue;

public class _145_단어변환 {

    class Solution {

        class Node{
            String word;
            int cnt;

            public Node(String word, int cnt) {
                this.word = word;
                this.cnt = cnt;
            }
        }

        public int solution(String begin, String target, String[] words) {
            boolean flag = false;
            for (String word : words) {
                if (target.equals(word)) {
                    flag = true;
                    break;
                }
            }

            if(!flag) return 0;

            boolean[] visited = new boolean[words.length];
            Queue<Node> q = new LinkedList<>();
            q.add(new Node(begin, 0));

            while (!q.isEmpty()) {
                Node cur = q.poll();

                if (cur.word.equals(target)) {
                    return cur.cnt;
                }

                for (int i = 0; i < words.length; i++) {
                    if (!visited[i]) {
                        int count = 0;
                        for (int j = 0; j < words[i].length(); j++) {
                            if (cur.word.charAt(j) != words[i].charAt(j)) {
                                count++;
                            }
                        }

                        if (count == 1) {
                            q.add(new Node(words[i], cur.cnt + 1));
                            visited[i] = true;
                        }
                    }
                }


            }

            return 0;
        }
    }

}

/*
문제 분석
1. 정보
    - 두 개의 단어 begin, target과 단어의 집합 words가 있음.
    - 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 함.
        - 1. 한 번에 한 개의 알파벳만 바꿀 수 있음.
        - 2. words의 있는 단어로만 변환할 수 있음.
2. 목표
    - 두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단개의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return
3. 제약 조건
    - 각 단어는 알파벳 소문자로 이루어짐
    - 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같음
    - words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없음
    - begin과 target은 같지 않음
    - 변환할 수 없는 경우에는 0을 return

풀이
1. 아이디어
    - 일단 words에 target이 들어있지 않다. -> 바로 0 return
    - BFS 사용
    - 시작 단어를 큐에 넣고 시작
        - 큐에서 뽑고 해당 단어와 words의 단어를 비교하며 한개의 알파벳만 다른 단어들을 큐에 집어넣음. 이때, 변환 횟수도 같이 저장해야함.
            - 단어 비교 방법
                - 단어의 길이는 같으므로, 처음부터 끝까지 비교
                    - 다르다면 count + 1 해줌
                - 만약 count가 1이라면 visited true 하고 큐에 저장
        - target 단어와 일치한다면, 해당 변환 횟수 return
*/
