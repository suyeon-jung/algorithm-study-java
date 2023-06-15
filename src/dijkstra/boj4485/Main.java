package dijkstra.boj4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    private static int INF = Integer.MAX_VALUE;
    private static int N;
    private static int testCnt = 1;
    private static int[][] map;
    private static int[][] distances;
    private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st;

        while(!(st = br.readLine()).isEmpty()) {
            N = Integer.parseInt(st);
            init(br);

            if(N == 0) break;

            dijkstra();

            testCnt++;
        }

        System.out.println(sb);

    }

    private static void init(BufferedReader br) throws IOException {


        map = new int[N][N];
        distances = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distances[i], INF);
        }

        for (int i = 0; i < N; i++) {
            String[] t = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(t[j]);
            }
        }
    }

    private static void dijkstra() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, map[0][0]));

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            if (cur.x == N - 1 && cur.y == N - 1) {
                sb.append("Problem ").append(testCnt).append(": ").append(cur.dist).append("\n");
                break;
            }

            if (distances[cur.x][cur.y] < cur.dist) continue;

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(isOOB(nx, ny)) continue;
                if(distances[nx][ny] != Integer.MAX_VALUE) continue;

                int nDist = cur.dist + map[nx][ny];


                if(nDist < distances[nx][ny]) {
                    distances[nx][ny] = nDist;
                    pq.offer(new Node(nx, ny, distances[nx][ny]));
                }

            }
        }

    }

    private static boolean isOOB(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }

    static class Node implements Comparable<Node> {
        int x, y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.dist, node.dist);
        }
    }
}
