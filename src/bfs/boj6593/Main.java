package bfs.boj6593;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// # 금(지나갈 수 없음)
// S 시작
// E 탈출
// . 비어있음
public class Main {

    private static int L, R, C;
    private static char[][][] board;
    private static boolean[][][] visited;
    private static int[] start;

    private static int[] dh = {0, 0, 0, 0, 1, -1};
    private static int[] dx = {1, 0, -1, 0, 0, 0};
    private static int[] dy = {0, 1, 0, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String[] tmp = br.readLine().split(" ");

            L = Integer.parseInt(tmp[0]);
            R = Integer.parseInt(tmp[1]);
            C = Integer.parseInt(tmp[2]);

            if(L == 0 && R == 0 && C == 0) break;

            board = new char[L + 1][R + 1][C + 1];
            visited = new boolean[L + 1][R + 1][C + 1];
            start = new int[3];

            for(int h = 0; h < L; h++) {
                for(int i = 0; i < R; i++) {
                    String t = br.readLine();
                    for(int j = 0; j < C; j++) {
                        board[h][i][j] = t.charAt(j);
                        if(t.charAt(j) == 'S') {
                            start[0] = h;
                            start[1] = i;
                            start[2] = j;
                        }
                    }
                }
                br.readLine();
            }

            int result = bfs();

            if(result == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + result + " minute(s).");
            }
        }
    }

    private static int bfs() {

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start[0], start[1], start[2], 0});
        visited[start[0]][start[1]][start[2]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if(board[cur[0]][cur[1]][cur[2]] == 'E') {
                return cur[3];
            }

            for (int dir = 0; dir < 6; dir++) {
                int nh = cur[0] + dh[dir];
                int nx = cur[1] + dx[dir];
                int ny = cur[2] + dy[dir];

                if(isOOB(nh, nx, ny)) continue;
                if(board[nh][nx][ny] == '#') continue;
                if(visited[nh][nx][ny]) continue;

                q.offer(new int[]{nh, nx, ny, cur[3] + 1});
                visited[nh][nx][ny] = true;
            }
        }

        return -1;
    }

    private static boolean isOOB(int h, int x, int y) {
        return h < 0 || h >= L || x < 0 || x >= R || y < 0 || y >= C;
    }
}
