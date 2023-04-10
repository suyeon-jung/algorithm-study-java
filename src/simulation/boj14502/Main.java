package simulation.boj14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        //0.입력
        init();

        //1.좌표 3개 고르기
        combination(0);

        //5. 최대 영역크기 출력
        System.out.println(answer);
    }


    private static void combination(int wallCnt) {
        if (wallCnt == 3) {
            bfs();
            return;
        }
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (board[row][col] == 0) {
                    board[row][col] = 1;
                    combination(wallCnt + 1);
                    board[row][col] = 0;
                }
            }
        }
    }


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    //BFS 진행 - 영역 넓이 반환
    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();

        //바이러스인경우 추가
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(board[i][j] == 2) {
                    q.offer(new int[] {i, j});
                }
            }
        }

        int cloneBoard[][] = new int[N][M];

        for(int i = 0; i < N; i++) {
            cloneBoard[i] = board[i].clone();
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];

            for (int dir = 0; dir < 4; dir++) {
                int nextX = nowX + dx[dir];
                int nextY = nowY + dy[dir];

                if (isOOB(nextX, nextY)) continue;
                if (cloneBoard[nextX][nextY] == 1) continue;
                if (cloneBoard[nextX][nextY] == 2) continue; //이미 바이러스 퍼진 경우

                q.offer(new int[]{nextX, nextY});
                cloneBoard[nextX][nextY] = 2;

            }
        }

        //안전구역 영역 넓이 구하기
        calculateArea(cloneBoard);
    }

    private static void calculateArea(int[][] map) {
        int area = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    area++;
                }
            }
        }
        answer = Math.max(area, answer);
    }

    private static boolean isOOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }

}

