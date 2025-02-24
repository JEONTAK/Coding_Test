package Programmers.Codekata;

public class _134_혼자서하는틱택토 {

    class Solution {
        public int solution(String[] board) {
            int oCount = 0;
            int xCount = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length(); j++) {
                    if (board[i].charAt(j) == 'O') {
                        oCount++;
                    } else if (board[i].charAt(j) == 'X') {
                        xCount++;
                    }
                }
            }

            if (xCount > oCount || oCount >= xCount + 2) {
                return 0;
            }

            for (int i = 0; i < board.length; i++) {
                if (board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)) {
                    if (board[i].charAt(0) == 'O') {
                        if(oCount == xCount) return 0;
                    } else if (board[i].charAt(0) == 'X') {
                        if (oCount == xCount + 1) return 0;
                    }
                }

                if (board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)) {
                    if (board[0].charAt(i) == 'O') {
                        if(oCount == xCount) return 0;
                    } else if (board[0].charAt(i) == 'X') {
                        if (oCount == xCount + 1) return 0;
                    }
                }
            }

            if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)){
                if (board[0].charAt(0) == 'O') {
                    if(oCount == xCount) return 0;
                } else if (board[0].charAt(0) == 'X') {
                    if (oCount == xCount + 1) return 0;
                }
            }

            if (board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)){
                if (board[0].charAt(2) == 'O') {
                    if(oCount == xCount) return 0;
                } else if (board[0].charAt(2) == 'X') {
                    if (oCount == xCount + 1) return 0;
                }
            }

            return 1;
        }
    }

}

/*
문제 분석
1. 정보
    - 틱택토는 처음에 3x3의 빈칸으로 이루어진 게임판에 선공이 O, 후공이 X를 번갈아가면서 빈칸에 표시하는 게임
    - 가로 세로 대각선으로 3개가 같은 표시가 만들어지면 같은 표시를 만든 사람이 승리하고 게임이 종료되며 9칸이 모두 차서 더 이상 표시를 할 수 없는 경우에는 무승부로 게임이 종료 됨
    - 할 일이 없어 한가한 머쓱이는 두 사람이 하는 게임인 틱택토를 다음과 같이 혼자서 하려고 한다.
        - 혼자서 선공과 후공을 둘 다 맡는다.
        - 틱택토 게임을 시작한 후 O와 X를 번갈아 가면서 표시를 하면서 진행한다
    - 틱택토는 단순한 규칙으로 게임이 금방 끝나기에 머쓱이는 한 게임이 종료되면 다시 3x3 빈칸을 그린 뒤 다시 게임을 반복함.
    - 머쓱이는 게임 도중에 다음과 같이 규칙을 어기는 실수를 했을 수도 있음
        - O를 표시할 차례인데 X를 표시하거나 반대로 X를 표시할 차례인데 O를 표시한 경우
        - 선공이나 후공이 승리해서 게임이 종료되었음에도 그 게임을 진행
    - 게임 도중 게임판을 본 순간 머쓱이는 본인이 실수 했는지 의문이 생김
    - 게임판만 봤을 때 실제로 틱택토 규칙을 지켜서 진행했을 때 나올 수 있는 상황인지는 판단할 수 있을 것 같고, 문제가 없다면 게임을 이어서 하려고 함

2. 목표
    - 틱택토 게임판의 정보를 담고 있는 문자열 배열 board가 매개변수로 주어질 때, 게임판이 규칙을 지켜서 틱택토를 진행했을 때 나올 수 있는 게임 상황이면 1, 아니라면 0을 return
3. 제약 조건
    - board의 길이 = board[i]의 길이 = 3
        - board의 원소는 모두 O, X, . 으로 이루어짐
    - board[i][j]는 i + 1행, j + 1열에 해당하는 칸의 상태를 나타냄
        - .은 빈칸, O와 X는 해당 문자로 칸이 표시되어 있다는 의미
풀이
1. 아이디어
    - 나올 수 없는 게임 상황
        - 1. O보다 X가 많은 경우
        - 2. O가 X 보다 2개 이상 많은 경우
        - 3. O가 승리한 경우
            - O와 X의 수가 같을 경우
        - 4. X가 승리한 경우
            - O가 하나 더 많을 경우
    - 그 외 나머지 상황은 정상적인 게임으로 간주

    - 1. O의 개수와 X의 개수를 구함
        - X가 많거나, O의 개수가 X 보다 2개 이상 많을 경우 0
    - 2. O가 틱택토 완성했는지 구함
        - O와 X의 수가 같을 경우 0
    - 3. X가 틱택토 완성했는지 구함
        - O의 수가 1개 더 많을 경우 0
*/
