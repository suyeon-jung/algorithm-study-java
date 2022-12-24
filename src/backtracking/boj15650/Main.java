package backtracking.boj15650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];

        backtrack(0, 0);
    }

    private static void backtrack(int idx, int depth) {
        if (depth == M) {
            // 선택된 수열 출력
            printArr(selected);
            return;
        }

        for (int i = idx; i < N; i++) {
            // 유망한 경우(아직 선택하지 않은 경우)
            selected[depth] = i + 1;
            backtrack(i + 1, depth + 1);
        }
    }

    private static void printArr(int[] arr) {
        for (int element : arr) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
