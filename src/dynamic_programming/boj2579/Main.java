package dynamic_programming.boj2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] stairs;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stairs = new int[N + 1];
        dp = new int[N + 1][3];

        for(int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        // 초기값
        dp[1][1] = stairs[1];
        dp[1][2] = 0;

        if(N >= 2) {
            dp[2][1] = stairs[2];
            dp[2][2] = stairs[1] + stairs[2];

            for(int j = 2; j <= N; j++) {
                dp[j][1] = Math.max(dp[j-2][1], dp[j-2][2]) + stairs[j];
                dp[j][2] = dp[j-1][1] + stairs[j];
            }
        }

        System.out.println(Math.max(dp[N][1], dp[N][2]));


    }
}
