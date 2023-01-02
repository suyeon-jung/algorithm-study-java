package dynamic_programming.boj11727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;

    static int MOD = 10_007;

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        dp[1] = 1;

        if(N >= 2) {
            dp[2] = 3;
            for(int i = 3; i <= N; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2] * 2) % MOD;
            }
        }
        System.out.println(dp[N]);
    }

}
