// n : 지점의 개수 = 정점의 개수
// s : 출발 지점 = 출발 정점
// a : A의 도착 정점
// b : B의 도착 정점
// fares : 지점 사이 예상 택시요금(간선 weight)
// 무방향 그래프
package dijkstra.programmers.shared_taxi;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Main {

    static int INF = Integer.MAX_VALUE;

    static List<List<Node>> graph = new ArrayList<>();

    static int[] dist1; // 시작점 ~ 각 지점까지의 최단거리
    static int[] dist2; // 헤어지는 지점 ~ 각 지점까지의 최단거리


    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        // 거리배열, 그래프 초기화
        dist1 = new int[n + 1];
        dist2 = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 셋팅(무방향 그래프)
        for(int i = 0; i < fares.length; i++) {
            graph.get(fares[i][0]).add(new Node(fares[i][1], fares[i][2]));
            graph.get(fares[i][1]).add(new Node(fares[i][0], fares[i][2]));
        }

        // 다익스트라 수행        
        Arrays.fill(dist1, INF);
        dijkstra(s, dist1);
        answer = dist1[a] + dist1[b];


        for(int i = 1; i <= n; i++) {
            if(i == s) {
                continue;
            }

            Arrays.fill(dist2, INF);
            dijkstra(i, dist2);
            if(dist2[a] != INF && dist2[b] != INF) {
                answer = Math.min(answer, dist1[i] + dist2[a] + dist2[b]);
            }
        }


        return answer;
    }

    static void dijkstra(int start, int[] distance) {

        PriorityQueue<Node> pq = new PriorityQueue<>();

        distance[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            int index = current.index;
            int weight = current.weight;

            if(weight > distance[index]) {
                continue;
            }

            for(Node adjacent : graph.get(index)) {
                if(distance[index] + adjacent.weight < distance[adjacent.index] ) {
                    distance[adjacent.index] = distance[index] + adjacent.weight;
                    pq.offer(new Node(adjacent.index,distance[index] + adjacent.weight));
                }
            }

        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }

    }
}