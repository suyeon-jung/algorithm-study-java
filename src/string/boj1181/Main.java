package string.boj1181;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<String> words = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            // 중복 단어 추가 안함
            if(words.contains(word)){
                continue;
            }
            words.add(word);
        }

        // 1. 길이순 정렬
        // 2. 사전순 정렬
        Collections.sort(words, Comparator.comparing(String::length)
                .thenComparing(String.CASE_INSENSITIVE_ORDER));
        for (String word : words) {
            System.out.println(word);
        }
    }
}
