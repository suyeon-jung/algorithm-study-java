package backtracking.boj15663;

import static java.lang.System.lineSeparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[] visited;
    static int[] selected;
    static int[] deck;

    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        set = new LinkedHashSet<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        deck = new int[N];
        selected = new int[M];
        visited = new boolean[N];

        // N개의 수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            deck[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(deck);

        backtrack(0);

        StringBuilder answer = new StringBuilder();
        for(String s: set) {
            answer.append(s).append(lineSeparator());
        }
        System.out.println(answer);

    }

    private static void backtrack(int depth) {
        if (depth == M) {
            StringBuilder sb = new StringBuilder();
            for(int element : selected) {
                sb.append(element).append(" ");
            }
            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            selected[depth] = deck[i];
            backtrack(depth + 1);
            visited[i] = false;
        }
    }
}