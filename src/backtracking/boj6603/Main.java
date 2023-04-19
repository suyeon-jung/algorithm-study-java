package backtracking.boj6603;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static int k;
    static final int N = 6; //골라야 하는 숫자의 개수
    static int[] numbers, selected;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while(true) {
            k = Integer.parseInt(sc.next());

            numbers = new int[k];
            selected = new int[N];

            for(int i = 0; i < k; i++) {
                numbers[i] = Integer.parseInt(sc.next());
            }

            if(k == 0) break;

            backtrack(0, 0);

            answer.append("\n");
        }
        System.out.print(answer);
    }

    private static void backtrack(int depth, int cur) {
        if(depth == N) {
            StringBuilder s = new StringBuilder();
            for(int tmp : selected) {
                s.append(tmp).append(" ");
            }
            answer.append(s).append("\n");
            return;
        }

        for(int i = cur; i < k; i++) {
            selected[depth] = numbers[i];
            backtrack(depth + 1, i + 1);
        }
    }

}
