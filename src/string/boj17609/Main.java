package string.boj17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            int end = word.length() - 1;
            if(isPalindrome(word, 0, end)) {//1.회문여부 검사
                sb.append(0).append('\n');
            } else {//2.유사회문 검사
                if(similarPalindrome(word, 0, end, true)) {
                    sb.append(1).append('\n');
                } else {
                    sb.append(2).append('\n');
                }

            }
        }

        System.out.println(sb);
    }

    /**
     * 회문 - 0
     * 유사 회문(한 문자를 삭제하여 회문으로 만들 수 있는 문자열) - 1
     * 둘 다 아닌 경우 - 2
     */
    private static boolean isPalindrome(String str, int start, int end) {
        while(start <= end) {
            if(str.charAt(start++) != str.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    private static boolean similarPalindrome(String str, int start, int end, boolean skipPossible) {
        while(start <= end) {
            if(str.charAt(start) != str.charAt(end)) { //유사회문 파악
                if(skipPossible) {
                    boolean endSkip = similarPalindrome(str, start, end - 1, false);
                    boolean startSkip = similarPalindrome(str, start + 1, end, false);
                    return endSkip || startSkip;
                } else {
                    return false;
                }
            }
            start++;
            end--;
        }
        return true;
    }
}
