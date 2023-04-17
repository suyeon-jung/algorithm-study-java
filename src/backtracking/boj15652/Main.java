package backtracking.boj15652;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] selected;

    public static void main(String[] args) throws IOException{
        init();

        backtrack(0, 1);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];
    }

    private static void backtrack(int depth, int cur) {
        if(depth == M) {
            print(selected);
            return;
        }
        for(int i = cur; i <= N; i++) {
            selected[depth] = i;
            backtrack(depth + 1, i);
        }

    }

    private static void print(int[] arr) {
        for(int i = 0; i < M; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
