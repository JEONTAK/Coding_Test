package Programmers.Codekata;

import java.util.*;
import java.time.*;

public class _54_2016년 {

    class Solution_first {
        public String solution(int a, int b) {
            Map<Integer, String> map = new HashMap<>();
            map.put(1, "MON");
            map.put(2, "TUE");
            map.put(3, "WED");
            map.put(4, "THU");
            map.put(5, "FRI");
            map.put(6, "SAT");
            map.put(7, "SUN");

            LocalDate date = LocalDate.of(2016, a, b);
            DayOfWeek dow = date.getDayOfWeek();
            int dowNumber = dow.getValue();

            return map.get(dowNumber);
        }
    }

    class Solution_second {
        public String solution(int a, int b) {
            LocalDate date = LocalDate.of(2016, a, b);
            DayOfWeek dow = date.getDayOfWeek();
            return String.valueOf(dow).substring(0, 3);
        }
    }
}
