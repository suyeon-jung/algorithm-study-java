package bfs.boj1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int v = Integer.parseInt(str[0]);
        int e = Integer.parseInt(str[1]);
        int startV = Integer.parseInt(str[2]);

        boolean[][] adj = new boolean[v + 1][v + 1]; // 인접행렬
        boolean[] visited = new boolean[v + 1];

        for(int i = 0; i < e; i++) {
            String[] vertex = br.readLine().split(" ");
            int start = Integer.parseInt(vertex[0]);
            int end = Integer.parseInt(vertex[1]);

            adj[start][end] = true;
            adj[end][start] = true;
        }

        // 1. DFS
        Stack<Integer> s = new Stack<>();
        s.push(startV);
        visited[startV] = true;
        System.out.print(startV + " ");

        while(!s.isEmpty()) {
            int cur = s.pop();

            if(!visited[cur]) {
                System.out.print(cur + " ");
            }
            visited[cur] = true;


            for(int i = v; i > 0; i--) {
                if(i == cur) {
                    continue;
                }
                // 정점이 연결되지 않거나 방문했다면 continue
                if(!adj[cur][i] || visited[i]) {
                    continue;
                }

                s.push(i);
            }
        }
        System.out.println();

        // 2. BFS
        Arrays.fill(visited, false);

        Queue<Integer> q = new LinkedList<>();
        q.offer(startV);
        visited[startV] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            System.out.print(cur + " ");
            for(int i = 1; i <= v; i++) {
                if(i == cur) {
                    continue;
                }
                // 정점이 연결되지 않거나 방문했다면 continue
                if(!adj[cur][i] || visited[i]) {
                    continue;
                }

                visited[i] = true;
                q.offer(i);
            }
        }
        System.out.println();
    }
}