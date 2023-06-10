package bfs.boj2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int N, answer = 1;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];


        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        for (int i = 1; i <= 100; i++) {
            answer = Math.max(solution(i), answer);
        }

        System.out.println(answer);
    }

    private static int solution(int rain) {
        // map 컬러링(비에 잠기는 곳 색칠하기)
        int[][] board = new int[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            board[i] = map[i].clone();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] <= rain) {
                    board[i][j] = -1;
                }
            }
        }
        // 영역의 개수 구하기 위한 BFS
        return bfs(rain, board);
    }

    private static int bfs(int rain, int[][] board) {

        initVisited();

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && board[i][j] != -1) {
                    count++;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int nx = cur[0] + dx[dir];
                            int ny = cur[1] + dy[dir];

                            if (isOOB(nx, ny)) continue;
                            if (visited[nx][ny]) continue;
                            if (board[nx][ny] == -1) continue;

                            q.offer(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
        }

        return count;
    }

    private static void initVisited() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    private static boolean isOOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
