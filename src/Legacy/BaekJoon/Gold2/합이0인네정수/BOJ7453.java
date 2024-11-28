package BaekJoon.Gold2.합이0인네정수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7453 {

    static int N;
    static int[][] arr;
    static int[] AB, CD;
    static long result;

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[4][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        AB = new int[N * N];
        CD = new int[N * N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = arr[0][i] + arr[1][j];
                CD[idx++] = arr[2][i] + arr[3][j];
            }
        }
        Arrays.sort(AB);
        Arrays.sort(CD);

        int left = 0;
        int right = N * N - 1;
        while (left < N * N && right >= 0) {
            if (AB[left] + CD[right] < 0) {
                left++;
            }else if(AB[left] + CD[right] > 0) {
                right--;
            }else{
                long leftCount = 1;
                long rightCount = 1;
                while(left + 1 < N * N && (AB[left] == AB[left + 1])){
                    leftCount++;
                    left++;
                }
                while(right > 0 && (CD[right] == CD[right - 1])){
                    rightCount++;
                    right--;
                }
                result += leftCount * rightCount;
                left++;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BOJ7453.solution();
    }
}

//A, B, C, D 배열 하나당 최대 4000개가 들어갈 수 있다.
//해당 배열을 브루트포스방식으로 풀면 4000 * 4000 * 4000 * 4000이니 시간초과가 반드시 나올것.
//따라서 먼저 AB, CD의 모든 합을 먼저 구한다.
//이후 AB, CD배열을 각각 정렬하면 AB -> A배열과 B배열의 합에 대한 모든 가지수 정렬, CD -> C배열과 D배열의 합에 대한 모든 가지수 정렬이 된다.
//하지만 어쨌든 여기서도 일일히 비교하면 4000 * 4000 * 4000 * 4000인것은 변함 없다.
//따라서 투 포인터를 이용하여 구해야한다.
//방식은 left = 0, right = 배열의 마지막인 N * N - 1으로 설정한다.
//AB는 left, CD = right에서 부터 비교하면서 , 만약 AB[left] + CD[right] 가 0보다 크면 right를 줄이고, 0보다 작으면 left를 올린다
//만약 0이 나온다면 AB에서 AB[left]와 같은 값의 개수를 구하고, CD에서도 CD[right]와 같은 값의 개수를 구한다.
//그 이유는 각배열이 A B C D 배열의 모든 숫자를 더한것이기 때문에, 값이 같더라도 다른 숫자들을 조합한 결과이기 때문이다.
//따라서 left, right에서 각각 개수를 구하고, 해당 개수를 곱해준뒤 결과 값에 더해주면 해당 값의 합이 0이 되는 쌍의 개수를 구할 수 있다.
//해당 방식을 left 가 N * N - 1이 되거나, right가 0이 될때까지 반복하면 된다.
//따라서 해당 방식은 기존 N * N * N * N - > N ^ 4에서, N ^ 2logN 까지 복잡도를 낮출 수 있다.
