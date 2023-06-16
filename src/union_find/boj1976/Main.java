package union_find.boj1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N, M;
    private static String answer = "YES";
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        solution();
    }

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            String[] t = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(t[j]);
                if (tmp == 1) {
                    union(i + 1, j + 1);
                }
            }
        }

        String[] tmp = br.readLine().split(" ");
        int root = find(Integer.parseInt(tmp[0]));
        for (int i = 1; i < M; i++) {
            int other = find(Integer.parseInt(tmp[i]));
            if(root != other) {
                answer = "NO";
                break;
            }
        }

        System.out.println(answer);
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
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

}
