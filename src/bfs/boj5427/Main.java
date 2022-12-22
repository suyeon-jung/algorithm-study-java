package bfs.boj5427;

import static java.lang.System.lineSeparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static Queue<int[]> fire;
    static Queue<int[]> sanguen;
    static int[][] visited;
    static int[][] visitedSanguen;

    public static void main(String[] args) throws IOException {
        // 입력부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String line;
        int tcNum, w, h;
        char[][] board;

        tcNum = Integer.parseInt(br.readLine());

        for(int t = 0; t < tcNum; t++) {
            sanguen = new LinkedList<>();
            fire = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            board = new char[h][w];
            visited = new int[h][w];
            visitedSanguen = new int[h][w];

            for(int i = 0; i < h; i++) {
                Arrays.fill(visited[i], Integer.MAX_VALUE);
                Arrays.fill(visitedSanguen[i], -1);
            }

            for (int i = 0; i < h; i++) {
                line = br.readLine();
                for (int j = 0; j < w; j++) {
                    board[i][j] = line.charAt(j);
                    if (line.charAt(j) == '*') { // 불
                        fire.offer(new int[]{i, j});
                        visited[i][j] = 0;
                    } else if(line.charAt(j) == '@') { // 상근
                        sanguen.offer(new int[]{i, j});
                        visitedSanguen[i][j] = 0;
                    }
                }
            }

            // 불에 대한 bfs 수행
            while (!fire.isEmpty()) {
                int[] cur = fire.poll();
                int cx = cur[0];
                int cy = cur[1];

                for (int dir = 0; dir < 4; dir++) {
                    int nx = cx + dx[dir];
                    int ny = cy + dy[dir];

                    // 범위 벗어남, 이미 방문함, 벽
                    if (isOOB(w, h, nx, ny) || visited[nx][ny] != Integer.MAX_VALUE || board[nx][ny] == '#' ) {
                        continue;
                    }

                    visited[nx][ny] = visited[cx][cy] + 1;
                    fire.offer(new int[] {nx, ny});
                }
            }

            int answer = -1;

            while(!sanguen.isEmpty()) {
                int[] cur = sanguen.poll();
                int cx = cur[0];
                int cy = cur[1];

                if(cx == 0 || cx == h - 1 || cy == 0 || cy == w - 1) {
                    answer = visitedSanguen[cx][cy] + 1;
                    break;
                }

                for(int dir = 0; dir < 4; dir++) {
                    int nx = cx + dx[dir];
                    int ny = cy + dy[dir];
                    int nextTime =  visitedSanguen[cx][cy] + 1;

                    if(isOOB(w, h, nx, ny) || board[nx][ny] == '#' || visited[nx][ny] <= nextTime || visitedSanguen[nx][ny] != -1) {
                        continue;
                    }

                    visitedSanguen[nx][ny] = nextTime;
                    sanguen.offer(new int[] {nx, ny});
                }
            }

            sb.append(answer == -1 ?  "IMPOSSIBLE" : answer).append(lineSeparator());
        }

        System.out.println(sb);
        br.close();
    }

    private static boolean isOOB(int w, int h, int x, int y) {
        return x < 0 || x >= h || y < 0 || y >= w;
    }
}
