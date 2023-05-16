//무인도 여행
package programmers.problem3;
import java.util.*;

public class Main {
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    private int X, Y;
    private boolean[][] visited;
    private Queue<int []> q = new LinkedList<>();
    private List<Integer> list = new ArrayList<>();

    public int[] solution(String[] maps) {
        int[] answer = {};

        X = maps.length;
        Y = maps[0].length();
        visited = new boolean[X][Y];

        for(int i = 0; i < X; i++) {
            for(int j = 0; j < Y; j++) {
                if(maps[i].charAt(j) != 'X' && !visited[i][j]){
                    q.offer(new int[] {i, j});
                    visited[i][j] = true;
                    int result = bfs(maps);
                    list.add(result);
                }
            }
        }



        if(list.size() == 0) {
            answer = new int[] {-1};
        } else {
            answer = list.stream()
                    .sorted(Comparator.naturalOrder())
                    .mapToInt(Integer::intValue)
                    .toArray();
        }



        return answer;
    }

    private int bfs(String[] maps) {
        int cnt = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            cnt += maps[cur[0]].charAt(cur[1]) - '0';

            for(int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if(isOOB(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                if(maps[nx].charAt(ny) == 'X') continue;

                q.offer(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
        return cnt;
    }

    private boolean isOOB(int x, int y) {
        return x < 0 || x >= X || y < 0 || y >= Y;
    }
}
