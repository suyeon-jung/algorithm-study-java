package dynamic_programming.boj11057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] dp; // dp[i][j] = i 자리수일때 뒷자리가 j인 경우 오르막수 개수

    public static void main(String[] args) throws IOException {
        // 입력부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());

        dp = new int[length + 1][10];
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        // 2자리 수 부터 배열 채워감
        for (int i = 2; i <= length; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) { // 마지막 자리수가 j인 경우 그 앞에 올 수 있는 숫자는 0~j
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= 10007;
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < 10; i++) {
            ans += dp[length][i];
        }
        System.out.println(ans % 10007);
    }
}
