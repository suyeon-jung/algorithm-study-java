package backtracking.boj1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    static int alphabetCount; // 암호의 길이

    static int categoryCount; // 문자의 종류 개수

    static char[] passwords; // 만들어진 암호 배열

    static char[] dict; // 후보 문자

    static HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        alphabetCount = Integer.parseInt(str[0]);
        categoryCount = Integer.parseInt(str[1]);

        passwords = new char[alphabetCount];
        dict = new char[categoryCount];

        String[] tmp = br.readLine().split(" ");
        for (int i = 0; i < categoryCount; i++) {
            dict[i] = tmp[i].charAt(0);
        }

        Arrays.sort(dict); // 오름차순 정렬(암호의 오름차순 조건 만족을 위함)

        backtrack(0, 0);

    }

    private static void backtrack(int nxtIndex, int depth) {

        // 종료 조건 - 모두 선택한 경우
        if (depth == alphabetCount) {
            if (isPromising()) {
                for (char alphabet : passwords) {
                    System.out.print(alphabet);
                }
                System.out.println();
            }
            return;
        }

        // categoryCount + alphabetCount - depth + 1(생각해봐야함)
        for (int i = nxtIndex; i < categoryCount; i++) {
            passwords[depth] = dict[i];
            backtrack(i + 1, depth + 1);
        }
    }

    // 1. 최소 1개의 모음, 최소 2개의 자음
    // 2. 알파벳이 증가하는 순서로 배열(배열을 오름차순으로 정렬함으로써 만족)
    private static boolean isPromising() {

        int vowelCount = 0; // 모음
        int consonantsCount = 0; // 자음

        for (char passwordCharacter : passwords) {
            if (!vowels.contains(passwordCharacter)) {
                consonantsCount++;
            } else {
                vowelCount++;
            }
        }

        return vowelCount >= 1 && consonantsCount >= 2;
    }

}
