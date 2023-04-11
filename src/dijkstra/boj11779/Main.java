package dijkstra.boj11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int INF = Integer.MAX_VALUE;
    static int vertexCount, edgeCount, startVertex, endVertex;
    static List<List<Node>> graph = new ArrayList<>();
    static List<Integer> buffer = new ArrayList<>();
    static int[] distances, pre;

    public static void main(String[] args) throws IOException {
        init();

        dijkstra();

        System.out.println(distances[endVertex]);

        makePath();

        for(int i = buffer.size() - 1; i >= 0; i--) {
            System.out.print(buffer.get(i) + " ");
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        vertexCount = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        edgeCount = Integer.parseInt(st.nextToken());

        for(int i = 0; i < vertexCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        distances = new int[vertexCount + 1];
        pre = new int[vertexCount + 1];
        Arrays.fill(distances, INF);

        //간선 입력부
        for(int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, weight));
        }

        st = new StringTokenizer(br.readLine());
        startVertex = Integer.parseInt(st.nextToken());
        endVertex = Integer.parseInt(st.nextToken());
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distances[startVertex] = 0;
        pq.offer(new Node(startVertex, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int vertexIndex = node.index;
            int distance = node.distance;

            if(distance > distances[vertexIndex]) continue; //이미 방문한 경우

            for(Node adjacentNode : graph.get(vertexIndex)) {
                int possibleDistance = distance + adjacentNode.distance;
                if(possibleDistance < distances[adjacentNode.index]) {
                    distances[adjacentNode.index] = possibleDistance;
                    pq.offer(new Node(adjacentNode.index, possibleDistance));
                    pre[adjacentNode.index] = vertexIndex;
                }
            }
        }
    }

    private static void makePath() {
        int count = 0;
        while(endVertex != 0) {
            count++;
            buffer.add(endVertex);
            endVertex = pre[endVertex];
        }
        System.out.println(count);
    }

    static class Node implements Comparable<Node> {
        int index; //정점
        int distance; //이동정점까지의 거리
        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

}
