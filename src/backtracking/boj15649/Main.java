package backtracking.boj15649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] selected;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];
        visited = new boolean[N];

        backtrack(0);
    }

    private static void backtrack(int depth) {
        if(depth == M) {
            // 선택된 수열 출력
            printArr(selected);
            return;
        }

        for(int i = 0; i < N; i++) {
            // 유망한 경우(아직 선택하지 않은 경우)
            if(!visited[i]) {
                visited[i] = true; // 해당 노드를 선택하는 경우
                selected[depth] = i + 1;
                backtrack(depth + 1);
                visited[i] = false; // 이전 노드로 돌아감
            }
        }
    }

    private static void printArr(int[] arr) {
        for(int element : arr) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

}
