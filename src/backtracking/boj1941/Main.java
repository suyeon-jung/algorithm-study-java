package backtracking.boj1941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {

    static int N = 5;
    static int SEVEN = 7;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static char[][] board = new char[N][N];
    static Set<Integer> selected = new HashSet<>(); // 선택 여부 판단 배열
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        // 1. 입력부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        getCombinations(0, 0, 0);

        System.out.println(ans);

    }

    static void getCombinations(int index, int depth, int count) {

        if (depth == SEVEN) {
            if(bfs()) {
                ans++;
            }
            return;
        }

        // 탈출 조건
        if (index >= N * N) {
            return;
        }

        // 0 ~ 24 에서 7개 뽑기
        getCombinations(index + 1, depth, count); // 안뽑는경우
        selected.add(index);
        if (board[index / 5][index % 5] == 'S') { // 이다솜
            getCombinations(index + 1, depth + 1, count);
        } else {
            if(count < 3) {
                getCombinations(index + 1, depth + 1, count + 1);
            }
        }
        selected.remove(index);


    }

    // 넓이가 7이어야 만족, 인접한지 판단
    static boolean bfs() {
        int start = 0;
        for (int i = 0; i < N * N; i++) {
            if (selected.contains(i)) {
                start = i;
                break;
            }
        }

        int area = 0;

        boolean[] visited = new boolean[N*N];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int curRow = cur / 5;
            int curCol = cur % 5;
            area++;

            for (int dir = 0; dir < 4; dir++) {
                int nextRow = curRow + dx[dir];
                int nextCol = curCol + dy[dir];
                int next = nextRow * N + nextCol;

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N
                    || visited[next] || !selected.contains(next)) {
                    continue;
                }

                // 후보군에 있어야 다음 탐색 노드로 설정 가능
                q.offer(next);
                visited[next] = true;

            }

        }

        // 이경우에만 모든 노드가 인접한 것임!
        return area == 7;
    }

}
