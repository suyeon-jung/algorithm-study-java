package dynamic_programming.boj17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int[][][] cases;
    static int W = 0, L = 1, D = 2; //가로, 세로, 대각선

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        cases = new int[N][N][3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        //0행 초기화
        for(int i = 1; i < N; i++) {
            if(map[0][i] == 1) break; //벽을 만나는 경우 가로 방향으로 더이상 진행할 수 없음
            cases[0][i][W] = 1;
        }


        for(int i = 1; i < N; i++) {
            for(int j = 1; j < N; j++) {
                if (isInitialPosition(i, j))
                    continue;
                if(isWall(i, j)) continue;
                //1.가로에서 오는 경우
                if (map[i][j - 1] != 1) {
                    cases[i][j][W] = cases[i][j - 1][W] + cases[i][j - 1][D];
                }
                //2.세로에서 오는 경우
                if (map[i - 1][j] != 1) {
                    cases[i][j][L] = cases[i - 1][j][L] + cases[i - 1][j][D];
                }
                //3.대각선에서 오는 경우
                if (map[i][j] != 1 && map[i - 1][j] != 1 && map[i][j - 1] != 1) {
                    cases[i][j][D] =
                        cases[i - 1][j - 1][W] + cases[i - 1][j - 1][L] + cases[i - 1][j - 1][D];
                }
            }
        }

        System.out.println(cases[N-1][N-1][W] + cases[N-1][N-1][L] + cases[N-1][N-1][D]);

    }
    private static boolean isInitialPosition(int r, int c) {
        return r == 0 && c < 2;
    }
    private static boolean isWall(int r, int c) {
        return map[r][c] == 1;
    }
}
