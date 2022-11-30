package dijkstra.boj1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static List<List<Node>> graph = new ArrayList<>();

    static int[] distances;

    public static void main(String[] args) throws IOException {

        // 입력부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cityCount = Integer.parseInt(br.readLine());
        int busCount = Integer.parseInt(br.readLine());

        distances = new int[cityCount + 1];

        for (int i = 0; i <= cityCount; i++) {
            graph.add(new ArrayList<>());
        }

        // 출발도시, 도착지, 비용
        for (int i = 0; i < busCount; i++) {
            String[] tmp = br.readLine().split(" ");
            int firstIndex = Integer.parseInt(tmp[0]);
            int secondIndex = Integer.parseInt(tmp[1]);
            int weight = Integer.parseInt(tmp[2]);

            graph.get(firstIndex).add(new Node(secondIndex, weight));
        }

        String[] s = br.readLine().split(" ");
        int departure = Integer.parseInt(s[0]);
        int destination = Integer.parseInt(s[1]);

        Arrays.fill(distances, Integer.MAX_VALUE); // 최단거리배열 초기화

        distances[departure] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(departure, 0)); // 시작 노드 추가

        while (!pq.isEmpty()) { // 갱신노드가 없을때까지 반복
            Node pos = pq.poll();
            int index = pos.index;
            int weight = pos.weight;

            if (distances[index] < weight) {
                continue;
            }


            for (Node adjacent : graph.get(index)) {
                if (distances[adjacent.index] > distances[index] + adjacent.weight) {
                    distances[adjacent.index] = distances[index] + adjacent.weight;
                    pq.offer(new Node(adjacent.index, distances[index] + adjacent.weight));
                }
            }
        }

        System.out.println(distances[destination]);

    }

    public static class Node implements Comparable<Node> {

        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return weight - n.weight;
        }
    }

}
