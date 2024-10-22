package BaekJoon.Gold2.선분교차2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17387 {

    static class Point{
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static Point A, B, C, D;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        B = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        C = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        D = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        int ccw1 = compute(A, B, C) * compute(A, B, D);
        int ccw2 = compute(C, D, A) * compute(C, D, B);

        if (ccw1 == 0 && ccw2 == 0) {
            if (isCross()) {
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        } else if (ccw1 <= 0 && ccw2 <= 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static boolean isCross(){
        return Math.max(A.x, B.x) >= Math.min(C.x, D.x) && Math.max(C.x, D.x) >= Math.min(A.x, B.x)
                && Math.max(A.y, B.y) >= Math.min(C.y, D.y) && Math.max(C.y, D.y) >= Math.min(A.y, B.y);
    }

    static int compute(Point p1, Point p2, Point p3) {
        long result = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.y * p2.x + p2.y * p3.x + p3.y * p1.x);
        return Long.compare(result, 0);
    }

    public static void main(String[] args) throws IOException {
        BOJ17387.solution();
    }
}

//문제 해결의 가장 핵심은 CCW를 사용하는 것이다.
//CCW > 0 -> 시계방향
//CCW = 0 -> 일직선
//CCW < 0 -> 반시계방향
//이다.
//따라서 점 A, B를 지나는 선분과 C, D를 지나는 선분이 있다고 가정하면
//CCW(A, B, C)와 CCW(A, B, D)의 곱 -> ccw1
//CCW(C, D, A)와 CCW(C, D, B)의 곱 두가지를 구하면 된다. -> ccw2
//만약 ccw1, ccw2 가 둘다 음수라면 두 선분은 확실히 교차하고 있다.
//하지만 둘 중 하나 혹은 둘다 0인 경우의 수가 있다.
//우선 하나만 0이면 세점이 동일 직선에 존재하는 경우이므로 교차하는 것으로 판단할 수 있다.
//하지만 둘다 0인 경우에는 두 직선이 일직선에 놓여있지만, 안겹쳐 있는 경우의 수가 있을 수 있다.
//따라서 A, B의 x중 큰 값이 C,D의 x중 작은 값보다 크거나 같아야 하고
//C, D의 x중 큰 값이 A, B의 x중 작은 값보다 크거나 같아야 한다.
//y 좌표에서도 동일하게 적용된다
//해당 조건이 만족할 경우 두 선분은 겹쳐져 있으므로 해당 조건에 맞게 출력해주면 된다.
