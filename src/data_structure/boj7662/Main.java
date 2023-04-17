package data_structure.boj7662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    private static int T, k;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            TreeMap<Integer, Integer> dict = new TreeMap<>();

            for(int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if ("I".equals(command)) {
                    dict.put(value, dict.getOrDefault(value, 0) + 1);
                } else {
                    if (value == -1) { //최소값 삭제
                        if (!dict.isEmpty()) {
                            if(dict.get(dict.firstKey()) == 1) {
                                dict.remove(dict.firstKey());
                            } else {
                                dict.put(dict.firstKey(), dict.get(dict.firstKey()) - 1);
                            }
                        }
                    } else { //최대값 삭제
                        if (!dict.isEmpty()) {
                            if(dict.get(dict.lastKey()) == 1) {
                                dict.remove(dict.lastKey());
                            } else {
                                dict.put(dict.lastKey(), dict.get(dict.lastKey()) - 1);
                            }

                        }
                    }
                }

            }

            if(dict.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(dict.lastKey() + " " + dict.firstKey()).append("\n");
            }
        }
        System.out.print(sb);
    }
}
