package dijkstra.boj1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int distance;
    static int[] parent;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        init();

        kruskal();

        System.out.println(distance);
    }

    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V + 1];
        pq = new PriorityQueue<>();

        for(int i = 1; i < V + 1; i++) {
            parent[i] = i; //부모노드
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.add(new Node(start, end, weight));
        }

    }

    private static void kruskal() {
        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(find(now.from) != find(now.to)) { //부모노드가 다르고 방문하지 않았을 때
                union(now.from, now.to);
                distance += now.weight;
            }
        }
    }

    private static int find(int a) {
        if(parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }

    }

    static class Node implements Comparable<Node> {
        int from;
        int to;
        int weight;

        Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

}
