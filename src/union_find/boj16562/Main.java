package union_find.boj16562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, k;
    private static int[] parents;
    private static int[] costs;

    public static void main(String[] args) throws IOException {
        init();

        solution();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] t = br.readLine().split(" ");
        N = Integer.parseInt(t[0]);
        M = Integer.parseInt(t[1]);
        k = Integer.parseInt(t[2]);

        parents = new int[N + 1];
        costs = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        t = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(t[i - 1]);
        }

        for (int i = 0; i < M; i++) {
            t = br.readLine().split(" ");
            int t1 = Integer.parseInt(t[0]);
            int t2 = Integer.parseInt(t[1]);
            if(parents[t1] != parents[t2]) {
                union(t1, t2);
            }
        }
    }

    private static void solution() {
        int sum = Arrays.stream(parents)
                .filter(n -> n != 0)
                .map(Main::find)
                .distinct()
                .map(i -> costs[i])
                .sum();

        System.out.println(sum > k ? "Oh no" : sum);
    }

    private static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (costs[a] <= costs[b]) { //친구를 만드는 비용이 더 작은 친구 기준으로 union
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }
}
