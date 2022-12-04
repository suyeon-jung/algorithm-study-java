package dijkstra.boj1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static int n, m, x, ans;

    static int INF = Integer.MAX_VALUE;

    static List<List<Node>> graph = new ArrayList<>();

    static int[] distances; // 최단거리배열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        x = Integer.parseInt(str[2]);

        distances = new int[n + 1];

        for (int i = 0; i < m + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] tmp = br.readLine().split(" ");
            int start = Integer.parseInt(tmp[0]);
            int end = Integer.parseInt(tmp[1]);
            int weight = Integer.parseInt(tmp[2]);

            graph.get(start).add(new Node(end, weight));
        }

        for (int i = 1; i <= n; i++) {
            int tmpSum = 0;
            if (i == x) {
                continue;
            }
            dijkstra(i);
            tmpSum += distances[x];

            dijkstra(x);
            tmpSum += distances[i];
            ans = Math.max(ans, tmpSum);
        }

        System.out.println(ans);
    }

    static void dijkstra(int start) {

        Arrays.fill(distances, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 갱신노드저장
        distances[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int index = node.index;
            int distance = node.distance;

            if (distance > distances[index]) {
                continue;
            }

            for (Node adjacent : graph.get(index)) {
                if (distances[index] + adjacent.distance < distances[adjacent.index]) {
                    distances[adjacent.index] = distances[index] + adjacent.distance;
                    pq.offer(new Node(adjacent.index, distances[index] + adjacent.distance));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {

        int index;
        int distance;

        Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node n) {
            return distance - n.distance;
        }
    }
}
