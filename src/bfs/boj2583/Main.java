package bfs.boj2583;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int m = Integer.parseInt(str[0]);
        int n = Integer.parseInt(str[1]);
        int k = Integer.parseInt(str[2]);

        // visited 배열(true: 직사각형 영역 아닌 부분/false: 직사각형 영역인 부분)
        boolean[][] visited = new boolean[n][m];

        for (int ct = 0; ct < k; ct++) {
            String[] pos = br.readLine().split(" ");
            int x1 = Integer.parseInt(pos[0]);
            int y1 = Integer.parseInt(pos[1]);
            int x2 = Integer.parseInt(pos[2]);
            int y2 = Integer.parseInt(pos[3]);

            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    visited[x][y] = true;
                }
            }
        }

        int count = 0; // 영역의 개수
        List<Integer> ans = new ArrayList<>(); // 분리된 각 영역의 넓이(오름차순 정렬)
        Queue<int[]> q = new LinkedList<>();


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    continue;
                }

                count += 1;
                q.offer(new int[]{i, j}); // 탐색 시작점
                visited[i][j] = true;
                int area = 0;

                while (!q.isEmpty()) {

                    int[] cur = q.poll();
                    area += 1;

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = cur[0] + dx[dir];
                        int ny = cur[1] + dy[dir];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
                            continue;
                        }

                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }

                }
                ans.add(area);
            }
        }


        System.out.println(count);

        Collections.sort(ans);

        for (int ar : ans) {
            System.out.print(ar + " ");
        }

        br.close();
    }
}