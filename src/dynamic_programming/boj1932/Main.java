package dynamic_programming.boj1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = Integer.parseInt(str);

        int[][] triangle = new int[n][n];
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            for(int j = 0; j < tmp.length; j++) {
                triangle[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        dp[0][0] = triangle[0][0];
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < n; i++) {
            answer = Math.max(dp[n-1][i], answer);
        }

        System.out.println(answer);

    }

}
