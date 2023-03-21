package simulation.boj16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int N;
    static int babySharkSize = 2;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[][] board;
    static boolean[][] visited;
    static int time = 0;
    static int fishCount = 0;
    static int eatingCount = 0;
    static Queue<Position> q = new PriorityQueue<>((o1, o2) -> {
        if (o1.distance == o2.distance) {
            if (o1.row == o2.row) return Integer.compare(o1.col, o2.col);
            return Integer.compare(o1.row, o2.row);
        }
        return Integer.compare(o1.distance, o2.distance);

    });
    static Position start;

    public static void main(String[] args) throws IOException {
        init();

        while (!q.isEmpty()) {
            bfs();
        }


        System.out.println("fishCount = " + fishCount);
        System.out.println("time = " + time);


    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(row[j]);
                visited[i][j] = false;
                if (board[i][j] > 0 && board[i][j] < 9) fishCount++;
                else if (board[i][j] == 9) {
                    q.offer(new Position(i, j, 0));
                    visited[i][j] = true;
                    board[i][j] = 0;
                }
            }
        }
    }

    //    가장 위, 가장 왼쪽
    private static void bfs() {

        while (!q.isEmpty()) {

            Position cur = q.poll();
            int cx = cur.row;
            int cy = cur.col;
            int cDistance = cur.distance;

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];

                /** 지나갈 수 없는 경우 */
                if (isOOB(nx, ny) || visited[nx][ny] || board[nx][ny] > babySharkSize) continue;

                /** 먹을 수 있는 경우 - 여기서 이번 BFS 좋료 */
                if (board[nx][ny] < babySharkSize && board[nx][ny] > 0) {
                    start = new Position(nx, ny, cDistance + 1);

                    eatingCount++;
                    if (eatingCount == babySharkSize) {
                        babySharkSize++;
                        eatingCount = 0;
                    }
                    board[nx][ny] = 0;
                    fishCount--;


                    time += cDistance + 1;

                    q.clear();
                    clearVisited();

                    q.offer(new Position(nx, ny, 0));
                    visited[nx][ny] = true;

                    return;
                }

                q.offer(new Position(nx, ny, cDistance + 1));
                visited[nx][ny] = true;
            }
        }
    }

    private static void clearVisited() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    private static boolean isOOB(int x, int y) {
        return x >= N || x < 0 || y >= N || y < 0;
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    static class Position {
        int row;
        int col;
        int distance;

        public Position(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
}


