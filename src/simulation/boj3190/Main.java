package simulation.boj3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    // 시계방향으로 정의
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int curPosIndex = 0;
    static int second = 0; // 현재 초

    static Queue<Direction> directions = new LinkedList<>();

    // 다음 방향의 dx, dy 인덱스 반환
    static void getNextIndex(char direction) {
        switch (direction) {
            case 'D':
                curPosIndex += 1;
                curPosIndex = curPosIndex == 4 ? 0 : curPosIndex;
                break;
            case 'L':
                curPosIndex -= 1;
                curPosIndex = curPosIndex == -1 ? 3 : curPosIndex;
                break;
        }
    }

    // 자기 몸에 부딪히는지 확인
    static boolean check(Deque<Pos> d, int row, int col) {
        boolean answer = false;
        for (Pos p : d) {
            if (p.row == row && p.col == col) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmp = br.readLine();

        int n = Integer.parseInt(tmp); // 보드 크기

        boolean[][] board = new boolean[n][n];
        Deque<Pos> snake = new LinkedList<>();

        tmp = br.readLine();
        int k = Integer.parseInt(tmp); // 사과의 개수
        for (int i = 0; i < k; i++) {
            String[] s = br.readLine().split(" ");
            int row = Integer.parseInt(s[0]);
            int col = Integer.parseInt(s[1]);

            board[row - 1][col - 1] = true; // 사과 위치
        }
        tmp = br.readLine();
        int l = Integer.parseInt(tmp);

        for (int i = 0; i < l; i++) {
            String[] s = br.readLine().split(" ");
            directions.offer(new Direction(Integer.parseInt(s[0]), s[1].charAt(0)));
        }

        snake.add(new Pos(0, 0)); // 뱀의 첫 머리 위치
        second = 0;

        // 뱀 이동 시작
        while (true) {
            second++; // 시간 증가

            // 현재 머리 위치
            Pos curPos = snake.getFirst();
            int curRow = curPos.row;
            int curCol = curPos.col;

            int nextRow = curRow + dy[curPosIndex];
            int nextCol = curCol + dx[curPosIndex];

            // 종료조건1(벽을 만나는 경우) 확인
            if (nextRow >= n || nextCol >= n || nextRow < 0 || nextCol < 0) {
                break;
            }

            // 종료조건2(자기 몸에 부딪히는 경우) 확인
            if (check(snake, nextRow, nextCol)) {
                break;
            }

            snake.addFirst(new Pos(nextRow, nextCol)); // 좌표 이동

            // 다음칸에 사과가 없다면 꼬리 위치한 칸 비우기
            if (!board[nextRow][nextCol]) {
                snake.removeLast();
            } else {
                // 사과가 있다면 먹기
                board[nextRow][nextCol] = false;
            }

            // 방향 전환
            if (!directions.isEmpty() && directions.peek().second == second) {
                Direction di = directions.poll();
                getNextIndex(di.direction);
            }
        }
        System.out.println(second);
    }

}

class Pos {

    int row;
    int col;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Direction {

    int second;
    char direction;

    public Direction(int second, char direction) {
        this.second = second;
        this.direction = direction;
    }
}