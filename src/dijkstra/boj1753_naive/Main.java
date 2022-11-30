package dijkstra.boj1753_naive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        boolean[] visited = new boolean[vertexCount + 1]; // 방문여부저장배열
        distances[startVertex] = 0; // 시작점 0으로 초기화

        for (int i = 0; i < vertexCount; i++) {

            int minDistance = INF;
            int vertexIndex = 0;

            // 확정된 정점 뽑아내기
            for (int j = 1; j <= vertexCount; j++) {
                if (!visited[j] && minDistance > distances[j]) {
                    vertexIndex = j;
                    minDistance = distances[j];
                }
            }

            visited[vertexIndex] = true;

            for (Node adjacentNode : graph.get(vertexIndex)) {
                int nxtDistance = distances[vertexIndex] + adjacentNode.distance;
                distances[adjacentNode.index] = Math.min(distances[adjacentNode.index],
                    nxtDistance);
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
