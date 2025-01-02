package Programmers.Codekata;

import java.util.stream.*;

public class _35_부족한금액계산하기 {

    class Solution {
        public long solution(int price, int money, int count) {
            long answer = price * (count * (count + 1)) / 2;
            return (money - answer) >= 0 ? 0 : (answer - money);
        }
    }
}
