package backtracking.boj15655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] selected;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        init();

        backtrack(0, 0);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];
        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
    }

    private static void backtrack(int depth, int cur) {
        if(depth == M) {
            print(selected);
            return;
        }

        for(int i = cur; i < N; i++) {
            selected[depth] = numbers[i];
            backtrack(depth + 1, i + 1);
        }

    }

    private static void print(int[] arr) {
        for(int i = 0; i < M; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
