package dynamic_programming.boj14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] period;
    static int[] money;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        period = new int[N + 1];
        money = new int[N + 1];
        dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            period[i] = T;
            money[i] = P;
        }

        // 마지막 일자부터 최대금액 파악
        for (int i = N; i > 0; i--) {
            // 소요시간, 남은시간 비교
            // 소요시간 > 남은시간 => 진행하지 못함
            if (period[i] > N - i + 1) {
                dp[i] = dp[i + 1];
            } else { // 소요시간 <= 남은시간 => 진행가능
                // 진행했을때, 진행하지 않았을때의 값 중에 최대를 선택
                dp[i] = Math.max(dp[i + period[i]] + money[i], dp[i + 1]);
            }
        }
        System.out.println(dp[1]);
    }

}
