package union_find.boj1368;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int N, count, answer;
    static int[] cost, parent; //우물팔때 드는 비용
    static PriorityQueue<Node> connectCost;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = Integer.parseInt(sc.nextLine());
        cost = new int[N + 1];
        parent = new int[N + 1];
        connectCost = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(sc.nextLine());
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            String[] tmp = sc.nextLine().split(" ");
            for (int j = 1; j <= N; j++) {
                if (i == j) { //1.우물을 파는 경우
                    connectCost.offer(new Node(0, j, cost[i]));
                } else { //2.논을 연결하는 경우
                    connectCost.offer(new Node(i, j, Integer.parseInt(tmp[j - 1])));
                }
            }
        }

        while (!connectCost.isEmpty()) {
            Node candidate = connectCost.poll();

            if (find(candidate.node1) != find(candidate.node2)) {
                union(candidate.node1, candidate.node2);
                answer += candidate.cost;
            }
        }

        System.out.println(answer);
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static class Node implements Comparable<Node> {

        int node1;
        int node2;
        int cost;

        Node(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }

    }


}
