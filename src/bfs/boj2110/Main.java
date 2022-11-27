package bfs.boj2110;

import static java.lang.Math.max;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]); // 집의 개수
        int c = Integer.parseInt(str[1]); // 공유기 개수

        int[] arr = new int[n];

        // 집좌표 입력부
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            arr[i] = Integer.parseInt(s);
        }

        Arrays.sort(arr); // 집좌표 정렬

        int start = 1; // 최소 거리
        int end = arr[arr.length - 1] - arr[0]; // 최대 거리(첫번째집 - 마지막집)
        int ans = 0; // 공유기 사이 최대 거리

        while (start <= end) {
            int mid = (start + end) / 2;

            int count = 1;
            int position = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] - position >= mid) {
                    count += 1;
                    position = arr[i];
                }
            }

            if (count >= c) {
                ans = max(ans, mid);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
