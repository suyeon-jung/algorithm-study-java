package samsung.code_tree_bread;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, time, arrivalPersonCount;
    static int[][] board;
    static boolean[][] visited; //전체 visited(선점된 베이스캠프, 편의점)
    static int[] dx = {0, -1, 1, 0}, dy = {1, 0, 0, -1};
    static Person[] personList;

    static Comparator<Position> comparator = (o1, o2) -> {
        if(o1.distance == o2.distance) {
            if(o1.r == o2.r) return Integer.compare(o1.c, o2.c);
            return Integer.compare(o1.r, o2.r);
        }
        return Integer.compare(o1.distance, o2.distance);
    };

    public static void main(String[] args) throws IOException {
        //0.입력
        init();

        //1. 베이스캠프 찾기
        for(int i = 0; i < M; i++) {
            Position basecamp = findBasecamp(personList[i]);
            personList[i].arrivalBasecamp = true;
            personList[i].pq.offer(basecamp);
            for(int j = 0; j <= i; j++) {
                if(!personList[j].arrivalStore) {
                    moveStore(personList[j]);
                }
            }
            time++;
        }

        //2. 편의점 찾기
        while(arrivalPersonCount < M) {
            for(int i = 0; i < M; i++) {
                if (!personList[i].arrivalStore) {
                    //편의점 찾기
                    moveStore(personList[i]);
                }
            }
            time++;
        }
        System.out.println(time);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());//격자크기
        M = Integer.parseInt(st.nextToken());//사람수

        board = new int[N][N];
        visited = new boolean[N][N];
        personList = new Person[M];

        // 격자 입력부(0-빈공간, 1-베이스캠프)
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 사람별로 가고 싶은 편의점 위치 행, 열 정보 입력부
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            personList[i] = new Person(new Position(r-1, c-1, 0), new PriorityQueue<>(comparator), new boolean[N][N]);
        }
    }

    //베이스캠프 찾기(r-편의점행/c-편의점열)
    static Position findBasecamp(Person person) {
        boolean[][] subVisited = new boolean[N][N];
        Queue<Position> q = new PriorityQueue<>(comparator);

        q.offer(person.store);
        subVisited[person.store.r][person.store.c] = true;

        while(!q.isEmpty()) {
            Position now = q.poll();

            int nowR = now.r;
            int nowC = now.c;
            int nowDistance = now.distance;
            //베이스캠프 찾으면 종료
            if(board[nowR][nowC] == 1) {
                visited[nowR][nowC] = true;
                person.visited[nowR][nowC] = true;
                return new Position(nowR, nowC, 0);
            }

            for(int dir = 0; dir < 4; dir++) {
                int nextR = nowR + dx[dir];
                int nextC = nowC + dy[dir];
                int nextDistance = nowDistance + 1;

                if(isOOB(nextR, nextC)) continue;
                if(visited[nextR][nextC] || subVisited[nextR][nextC]) continue;

                q.offer(new Position(nextR, nextC, nextDistance));
                subVisited[nextR][nextC] = true;
            }
        }
        return null;
    }

    //편의점으로 이동
    private static void moveStore(Person person) {
        Queue<Position> q = person.pq;
        int size = q.size();
        boolean[][] personVisited = person.visited;


        while(size-- > 0) {
            Position now = q.poll();
            int nowR = now.r;
            int nowC = now.c;
            int nowDistance = now.distance;

            //편의점 찾으면 종료
            if(nowR == person.store.r && nowC == person.store.c) {
                visited[nowR][nowC] = true;
                person.arrivalStore = true;
                arrivalPersonCount++;
                break;
            }


            for(int dir = 0; dir < 4; dir++) {
                int nextR = nowR + dx[dir];
                int nextC = nowC + dy[dir];
                int nextDistance = nowDistance + 1;

                if(isOOB(nextR, nextC)) continue;
                if(personVisited[nextR][nextC] || visited[nextR][nextC]) continue;

                q.offer(new Position(nextR, nextC, nextDistance));
                personVisited[nextR][nextC] = true;
            }
        }

    }

    private static boolean isOOB(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    static class Position {
        int r;
        int c;
        int distance;
        Position(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }
    }

    static class Person {
        Position store;
        Queue<Position> pq;
        boolean[][] visited;
        boolean arrivalStore; //편의점 도착여부
        boolean arrivalBasecamp; //베이스캠프 도착여부

        public Person(Position store, Queue<Position> pq, boolean[][] visited) {
            this.store = store;
            this.pq = pq;
            this.visited = visited;
            this.arrivalStore = false;
            this.arrivalBasecamp = false;
        }
    }


}
