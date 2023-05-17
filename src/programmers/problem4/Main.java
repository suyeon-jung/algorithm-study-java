//카카오프렌즈 컬러링북
package programmers.problem4;

import java.util.*;

class Solution {

    private boolean[][] visited;
    private int area = 0, max = 0; //개수, 넓이
    private Queue<int[]> q;
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    private int num;
    private int count, maxArea;

    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];

        q = new LinkedList<>();
        visited = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && picture[i][j] != 0) {
                    q.offer(new int[]{i, j});
                    num = picture[i][j];
                    visited[i][j] = true;
                    maxArea = Math.max(maxArea, bfs(picture, m, n));
                    count++;

                }
            }
        }

        answer[0] = count;
        answer[1] = maxArea;


        return answer;
    }

    private int bfs(int[][] picture, int m, int n) {
        int result = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            result++;

            for(int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if(isOOB(nx, ny, m, n)) continue;
                if(visited[nx][ny]) continue;
                if(picture[nx][ny] != num) continue;  //같은 영역이 아닌 경우

                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        return result;
    }

    private boolean isOOB(int x, int y, int m, int n) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }
}