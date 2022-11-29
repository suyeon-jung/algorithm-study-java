package dijkstra.boj1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static int INF = Integer.MAX_VALUE;

    static List<List<Node>> graph = new ArrayList<>(); // 그래프

    static int[] distances; // 최단거리배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int vertexCount = Integer.parseInt(str[0]); // 정점의 개수
        int edgeCount = Integer.parseInt(str[1]); // 간선의 개수
        int startVertex = Integer.parseInt(br.readLine()); // 시작 정점의 번호

        // 그래프 셋팅
        for (int i = 0; i < vertexCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        // 최단거리배열
        distances = new int[vertexCount + 1];
        Arrays.fill(distances, INF); // 최대값으로 초기화

        // 간선 입력(u, v, w)
        for (int i = 0; i < edgeCount; i++) {
            String[] tmp = br.readLine().split(" ");
            int firstVertex = Integer.parseInt(tmp[0]);
            int secondVertex = Integer.parseInt(tmp[1]);
            int weight = Integer.parseInt(tmp[2]);

            graph.get(firstVertex).add(new Node(secondVertex, weight));
        }

        // 다익스트라 알고리즘 수행
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 갱신된 노드 담을 우선순위큐
        distances[startVertex] = 0; // 시작점 0으로 초기화
        pq.offer(new Node(startVertex, 0));

        while (!pq.isEmpty()) { // 갱신된 노드가 없어질때까지 반복
            Node node = pq.poll();
            int vertexIndex = node.index;
            int distance = node.distance;

            if (distance > distances[vertexIndex]) { // 추춛된 노드의 거리가 최단거리배열에서의 값보다 크면 이전에 방문한 노드
                continue;
            }

            for (Node adjacentNode : graph.get(vertexIndex)) {
                if (distance + adjacentNode.distance < distances[adjacentNode.index]) {
                    // 최단거리배열 갱신
                    distances[adjacentNode.index] = distance + adjacentNode.distance;
                    pq.offer(new Node(adjacentNode.index, distances[adjacentNode.index]));
                }
            }
        }

        for (int i = 1; i < vertexCount + 1; i++) {
            if (distances[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(distances[i]);
            }
        }
    }

    static class Node implements Comparable<Node> {

        int index; // 정점
        int distance; // 이동 정점까지의 거리

        Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.distance, n.distance);
        }
    }
}
