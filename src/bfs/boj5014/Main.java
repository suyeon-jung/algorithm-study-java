package bfs.boj5014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int F, S, G, U, D, answer = Integer.MAX_VALUE;
    private static int[] ds = new int[2];
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");

        F = Integer.parseInt(st[0]);
        S = Integer.parseInt(st[1]);
        G = Integer.parseInt(st[2]);
        U = Integer.parseInt(st[3]);
        D = Integer.parseInt(st[4]);

        ds[0] = U;
        ds[1] = -1 * D;

        visited = new boolean[1_000_005];

        if(S == G) {
            System.out.println(0);
            return;
        }

        bfs();

        if(answer == Integer.MAX_VALUE) {
            System.out.print("use the stairs");
        } else {
            System.out.print(answer);
        }

    }

    private static void bfs() {
        Queue<State> q = new LinkedList<>();
        q.offer(new State(S, 0));
        visited[S] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();

            if(cur.fcnt == G) {
                answer = Math.min(answer, cur.count);
                continue;
            }

            if(cur.count > answer) {
                continue;
            }

            for(int i = 0; i < 2; i++) {
                if(ds[i] == 0) continue;
                int nxt = cur.fcnt + ds[i];
                if(nxt <= 0 || nxt > F) continue;
                if(visited[nxt]) continue;

                q.offer(new State(nxt, cur.count + 1));
                visited[nxt] = true;
            }
        }

    }

    static class State {
        int fcnt;
        int count;

        public State(int fcnt, int count) {
            this.fcnt = fcnt;
            this.count = count;
        }
    }
}
