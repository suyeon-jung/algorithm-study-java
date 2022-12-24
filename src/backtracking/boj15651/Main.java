package backtracking.boj15651;

import static java.lang.System.lineSeparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = new StringBuilder();
        backtrack(0, new StringBuilder());
        System.out.println(answer);
    }

    private static void backtrack(int depth, StringBuilder cur) {
        if (depth == M) {
            // 선택된 수열 출력
            answer.append(cur).append(lineSeparator());
            return;
        }

        for (int i = 0; i < N; i++) {
            // 유망한 경우(아직 선택하지 않은 경우)
            cur.append(i + 1).append(" ");
            backtrack(depth + 1, cur);
            cur.delete(cur.length() - 2, cur.length());
        }
    }
}
