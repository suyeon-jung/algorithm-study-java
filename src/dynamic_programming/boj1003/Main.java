package dynamic_programming.boj1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int MAX = 42;
    static int[] dp = new int[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int testCase = Integer.parseInt(str);

        // 0의 개수(dp 초기화)
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 1;

        for (int i = 0; i < testCase; i++) {
            str = br.readLine();
            int n = Integer.parseInt(str);

            if(n == 0) {
                System.out.println(1 + " " + 0);

            }else if(n == 1) {
                System.out.println(0 + " " + 1);
            } else {
                for (int j = 3; j <= n + 1; j++) {
                    dp[j] = dp[j - 1] + dp[j - 2];
                }
                System.out.println(dp[n] + " " + dp[n + 1]);
            }
        }
    }
}
