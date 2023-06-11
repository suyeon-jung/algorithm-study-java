package bfs.boj2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] t = br.readLine().split(" ");

        N = Integer.parseInt(t[0]);
        M = Integer.parseInt(t[1]);
        board = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        for (int i = 0; i < N; i++) {
            t = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(t[j]);
            }
        }

        int year = 1;
        while (true) {
            // board -> copyBoard 복사
            int[][] copyBoard = new int[N + 1][M + 1];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    copyBoard[i][j] = board[i][j];
                }
            }

            // 1. 인접한 칸의 개수만큼 줄이기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] != 0) {
                        // 인접한 수 구하기
                        copyBoard[i][j] -= getOcean(i, j);
                        if (copyBoard[i][j] < 0) {
                            copyBoard[i][j] = 0;
                        }
                    }
                }
            }
            // copyBoard -> board 복사
            boolean isFade = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    board[i][j] = copyBoard[i][j];
                    if (board[i][j] != 0) {
                        isFade = false;
                    }
                }
            }

            // board에 0밖에 안남은 경우 처리
            if (isFade) {
                System.out.println(0);
                break;
            }


            // 2. 영역 개수 구하기
            q.clear();
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && board[i][j] != 0) {
                        cnt++;
                        q.offer(new int[]{i, j});
                        visited[i][j] = true;
                        bfs(board);
                    }
                }
            }

            if (cnt >= 2) {
                System.out.println(year);
                break;
            }
            year++;
        }
    }

    private static int getOcean(int x, int y) {
        int count = 0;

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (isOOB(nx, ny)) continue;
            if (board[nx][ny] != 0) continue;

            count++;
        }
        return count;
    }

    private static void bfs(int[][] board) {
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if (isOOB(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (board[nx][ny] == 0) continue;

                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    private static boolean isOOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
