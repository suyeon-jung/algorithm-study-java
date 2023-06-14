package backtracking.boj16987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N, answer; //계란 수
    private static int[][] eggs; //계란 배열 (내구도/무게-0이하면 깨진 것)
    public static void main(String[] args) throws IOException {
        init();
        dfs(0, eggs);
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        eggs = new int[N + 1][2];

        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            eggs[i][0] = Integer.parseInt(tmp[0]); //내구도
            eggs[i][1] = Integer.parseInt(tmp[1]); //무게
        }
    }

    private static void dfs(int cur, int[][] eggs) {
        if (cur == N) {
            answer = Math.max(answer, getBreakedEggNum(eggs));
            return;
        }

        if (eggs[cur][0] <= 0) { //현재 계란이 깨진 경우
            dfs(cur + 1, eggs);
        } else {
            boolean isAllBreaked = true;
            for (int i = 0; i < N; i++) {
                if (eggs[i][0] > 0 && i != cur) {
                    isAllBreaked = false;
                    //부딪히기
                    eggs[cur][0] -= eggs[i][1];
                    eggs[i][0] -= eggs[cur][1];

                    dfs(cur + 1, eggs);

                    // 되돌리기
                    eggs[cur][0] += eggs[i][1];
                    eggs[i][0] += eggs[cur][1];
                }
            }

            // 계란이 모두 깨진 경우
            if (isAllBreaked) {
                answer = Math.max(answer, getBreakedEggNum(eggs));
            }
        }
    }

    // 깨진 계란의 개수 반환
    private static int getBreakedEggNum(int[][] eggs) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (eggs[i][0] <= 0) {
                cnt++;
            }
        }
        return cnt;
    }
}
