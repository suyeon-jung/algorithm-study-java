package greedy.boj7570;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int childrenCount = 0;
    static int[] line;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        childrenCount = Integer.parseInt(str);
        line = new int[childrenCount + 1];
        dp = new int[childrenCount + 1];
        String[] tmp = br.readLine().split(" ");

        for (int i = 0; i < childrenCount; i++) {
            line[i + 1] = Integer.parseInt(tmp[i]);
        }

        // 로직
        int maxLength = 1;
        for(int i = 1; i < childrenCount + 1; i++) {
            dp[line[i]] = dp[line[i] - 1] + 1;
            maxLength = Math.max(maxLength, dp[line[i]]);
        }

        // 출력부
        System.out.println(childrenCount - maxLength);

    }

}
