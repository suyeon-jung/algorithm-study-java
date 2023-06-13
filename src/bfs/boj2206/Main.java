package bfs.boj2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static boolean[][][] visited;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        init();

        bfs(0, 0);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        visited = new boolean[N + 1][M + 1][2]; //0: 부시지 않았을때 방문배열, 1: 부셨을때 방문배열
        map = new int[N + 1][M + 1];


        for (int i = 0; i < N; i++) {
            String t = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(t.charAt(j));
            }
        }
    }

    private static void bfs(int x, int y) {

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{x, y, 1, 0}); // x, y, 거리, 벽부셨는지여부
        visited[x][y][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int breakCount = cur[3];

            if(cur[0] == N - 1 && cur[1] == M - 1) {
                System.out.println(cur[2]);
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                int nDist = cur[2] + 1;

                if(isOOB(nx, ny)) continue;
                if(visited[nx][ny][breakCount]) continue;
                if(map[nx][ny] == 1) {
                    if(breakCount == 0) { //깰 수 있을때
                        visited[nx][ny][1] = true;
                        q.offer(new int[]{nx, ny, nDist, 1});
                    }
                    continue;
                }
                q.offer(new int[]{nx, ny, nDist, breakCount});
                visited[nx][ny][breakCount] = true;
//                if(breakCount == 0) { // 벽X - 벽을 부순적 없음
//                    q.offer(new int[]{nx, ny, nDist, breakCount});
//                    visited[nx][ny][breakCount] = true;
//                } else { // 벽X - 벽을 부순적 있음
//                    q.offer(new int[]{nx, ny, nDist, breakCount});
//                    visited[nx][ny][breakCount] = true;
//                }

            }
        }

        System.out.println(-1);
    }

    private static boolean isOOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
