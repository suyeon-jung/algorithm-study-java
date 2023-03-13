package bfs.boj2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int N;
    private static int[][] board;
    private static boolean[][] visited;
    private static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        visited = new boolean[N][N];

        //visited 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }


        List<Integer> counts = new ArrayList<>();
        q = new LinkedList<>();


        //board 초기화
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        //bfs 수행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    counts.add(bfs());
                }
            }
        }
        //결과 정렬
        Collections.sort(counts);
        //결과 출력부
        System.out.println(counts.size());
        for (int count : counts) {
            System.out.println(count);
        }
    }

    private static int bfs() {

        int area = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            area++;

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];

                if (isOOB(nx, ny) || visited[nx][ny] || board[nx][ny] != 1) {
                    continue;
                }

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }

        return area;
    }

    private static boolean isOOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
