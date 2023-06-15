package kruskal.boj1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    private static int N, M;
    private static int[] parent;
    private static PriorityQueue<Node> pq;
    private static int answer;
    public static void main(String[] args) throws IOException{
        init();

        kruskal();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        pq = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            String[] t = br.readLine().split(" ");
            int from = Integer.parseInt(t[0]);
            int to = Integer.parseInt(t[1]);
            int cost = Integer.parseInt(t[2]);

            pq.offer(new Node(from, to, cost));
        }
    }

    private static void kruskal() {

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (find(cur.from) != find(cur.to)) {
                union(cur.from, cur.to);
                answer += cur.cost;
            }
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
    static class Node implements Comparable<Node> {
        int from, to;
        int cost;

        Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.cost, node.cost);
        }
    }

}
