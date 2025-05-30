import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String [s.length];
        
        for(int i = 0 ; i < s.length ; i++) {
            int count = 0;
            String temp = s[i];
            Stack<Character> stack = new Stack<>();
            
            for(char c : temp.toCharArray()) {
                stack.push(c);
                
                if(stack.size() >= 3) {
                    char third = stack.pop();
                    char second = stack.pop();
                    char first = stack.pop();
                    
                    if(!(first == '1' && second == '1' && third == '0')) {
                        stack.push(first);
                        stack.push(second);
                        stack.push(third);
                    } else{
                        count++;
                    }
                }
            }
            
            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()) {
                sb.insert(0, stack.pop());
            }
            String remain = sb.toString();
            StringBuilder result = new StringBuilder();
            
            //1 위치 찾기
            int findZero = -1;
            for(int j = 0; j < remain.length(); j++) {
                if(remain.charAt(j) == '0') {
                    findZero = j;
                }
            }
            
            //0이 없다면 -> 다 1이거나 비어있으므로, 110삽입하고 remain 삽입
            if(findZero == -1) {
                for(int j = 0 ; j < count ; j++) {
                    result.append("110");
                }
                result.append(remain);
            } else { //0이 있다면 -> 가장 마지막 0의 위치임. 해당 0 뒤에 110 넣음
                result.append(remain.substring(0, findZero + 1));
                for(int j = 0 ; j < count ; j++) {
                    result.append("110");
                }
                result.append(remain.substring(findZero + 1));
            }
            
            answer[i] = result.toString();
        }
        
        return answer;
    }
}


/*
110패턴을 찾아 앞으로 옮기기
1. 110 패턴을 찾아 몇번 제거했는지 저장.
2. 사전순으로 가장 작은 문자열이 되도록 삽입
    - 문자열에서 맨앞의 1의 위치를 찾음
    - 1이 없으면 문자열 맨 앞에 110을 삽입
    - 1이 있으면 해당 1 앞에 110을 삽입

*/