//미로 탈출
package programmers.problem2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    boolean[][] visited;
    int W, H;
    int answer = -1;
    Queue<Position> q;

    public int solution(String[] maps) {
        W = maps[0].length();
        H = maps.length;
        q = new LinkedList<>();
        visited = new boolean[H][W];

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                if(maps[i].charAt(j) == 'S') {
                    q.offer(new Position(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        if(bfs('L', maps)) {
            //visited 초기화
            for(int i = 0; i < H; i++) {
                Arrays.fill(visited[i], false);
            }
            if(!bfs('E', maps)) {
                answer = -1;
            }
        }

        return answer;
    }

    private boolean isOOB(int x, int y) {
        return x < 0 || x >= H || y < 0 || y >= W;
    }

    private boolean bfs(char destination, String[] maps) {

        while(!q.isEmpty()) {
            Position now = q.poll();

            if(maps[now.x].charAt(now.y) == destination) {
                q.clear();
                q.offer(new Position(now.x, now.y, now.dist));
                answer = now.dist;
                return true;
            }

            for(int dir = 0; dir < 4; dir++) {
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];
                int ndist = now.dist + 1;

                if(isOOB(nx, ny)) continue;
                if(maps[nx].charAt(ny) == 'X') continue; //벽인 경우
                if(visited[nx][ny] == true) continue;

                q.offer(new Position(nx, ny, ndist));
                visited[nx][ny] = true;
            }
        }

        return false;
    }


    class Position {
        int x;
        int y;
        int dist;

        Position(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
