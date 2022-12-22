package bfs.boj5427;

import static java.lang.System.lineSeparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static Queue<int[]> fire;
    static Queue<int[]> sanguen;
    static int[][] visited;

    static int[][] visitedSanguen;

    public static void main(String[] args) throws IOException {
        int testcaseCount, width, height;

        // 입력부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        testcaseCount = Integer.parseInt(s);

        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < testcaseCount; t++) {
            sanguen = new LinkedList<>();
            fire = new LinkedList<>();

            String[] str = br.readLine().split(" ");
            width = Integer.parseInt(str[0]);
            height = Integer.parseInt(str[1]);

            visited = new int[height][width];
            visitedSanguen = new int[height][width];

            for(int i = 0; i < height; i++) {
                Arrays.fill(visited[i], Integer.MAX_VALUE);
                Arrays.fill(visitedSanguen[i], -1);
            }


            char[][] board = new char[height][width];

            for (int i = 0; i < height; i++) {
                String tmp = br.readLine();
                for (int j = 0; j < width; j++) {
                    board[i][j] = tmp.charAt(j);
                    if (tmp.charAt(j) == '*') { // 불
                        fire.offer(new int[]{i, j});
                        visited[i][j] = 0;
                    } else if(tmp.charAt(j) == '@') {
                        sanguen.offer(new int[]{i, j});
                        visitedSanguen[i][j] = 0;
                    }
                }
            }


            // 불에 대한 bfs 수행
            while (!fire.isEmpty()) {
                int[] cur = fire.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];
                    // 범위 벗어남, 이미 방문함, 벽
                    if (nx < 0 || nx >= height || ny < 0 || ny >= width || visited[nx][ny] != Integer.MAX_VALUE || board[nx][ny] == '#' ) {
                        continue;
                    }

                    visited[nx][ny] = visited[cur[0]][cur[1]] + 1;
                    fire.offer(new int[] {nx, ny});
                }
            }

            int answer = -1;

            while(!sanguen.isEmpty()) {
                int[] cur = sanguen.poll();

                int cx = cur[0];
                int cy = cur[1];

                if(cx == 0 || cx == height - 1 || cy == 0 || cy == width - 1) {
                    answer = visitedSanguen[cx][cy] + 1;
                    break;
                }

                for(int dir = 0; dir < 4; dir++) {
                    int nx = cx + dx[dir];
                    int ny = cy + dy[dir];
                    int nextTime =  visitedSanguen[cx][cy] + 1;

                    if(nx < 0 || nx >= height || ny < 0 || ny >= width || board[nx][ny] == '#' || visited[nx][ny] <= nextTime || visitedSanguen[nx][ny] != -1) {
                        continue;
                    }
                    visitedSanguen[nx][ny] = nextTime;
                    sanguen.offer(new int[] {nx, ny});
                }
            }

            if(answer == -1) {
                sb.append("IMPOSSIBLE").append(lineSeparator());
            } else {
                sb.append(answer).append(lineSeparator());
            }
        }

        System.out.println(sb);
    }
}
