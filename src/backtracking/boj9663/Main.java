package backtracking.boj9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int count = 0;
    static int[] queens;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        queens = new int[n]; // 같은 row에 퀸 못 놓음 -> 1차원 배열 (index : row, value : col)

        backtrack(0);

        System.out.println(count);
    }

    private static void backtrack(int depth) {
        // 종료 조건(n개 퀸의 자리를 모두 정한 경우) -> 성공적인 퀸 배열이 된 경우
        if (depth == n) {
            count++;
            return;
        }

        // queen 놓을 수 있으면 놓고 backtrack
        for (int i = 0; i < n; i++) {
            if (!isPossible(depth, i)) {
                continue;
            }
            queens[depth] = i;
            backtrack(depth + 1);
        }
    }

    // 이전에 배치한 퀸들과 공격이 가능한지 여부 판단
    private static boolean isPossible(int row, int col) {
        for (int i = 0; i < row; i++) { // 배치할 row까지 확인
            int opCol = queens[i]; // i행의 퀸을 놓는 열
            // 공격할 수 있는지 여부 확인(열 겹치는지, 대각선에 있는지)
            if (opCol == col || Math.abs(row - i) == Math.abs(opCol - col)) {
                return false;
            }
        }
        return true;
    }
}
