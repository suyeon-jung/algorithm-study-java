package backtracking.boj15654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] selected;
    static int[] numbers;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        init();

        backtrack(0);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];
        numbers = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
    }

    private static void backtrack(int depth) {
        if(depth == M) {
            print(selected);
            return;
        }

        for(int i = 0; i < N; i++) {
            if(visited[i] == false) {
                selected[depth] = numbers[i];
                visited[i] = true;
                backtrack(depth + 1);
                visited[i] = false;
            }
        }

    }

    private static void print(int[] arr) {
        for(int i = 0; i < M; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
