//호텔 대실
package programmers.problem1;

import java.util.*;

public class Main {
    public static int solution(String[][] book_time) {
        int answer = 0;

        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        Arrays.sort(book_time, (t1, t2) -> {
            if(toMin(t1[0]) == toMin(t2[0])) {
                return Integer.compare(toMin(t1[1]), toMin(t2[1]));
            }
            return Integer.compare(toMin(t1[0]), toMin(t2[0]));
        });

        System.out.println(Arrays.deepToString(book_time));


        for(String[] str : book_time) {
            String st = str[0];
            String en = str[1];

            int start = toMin(st);
            int end = toMin(en) + 10;

            if(rooms.size() == 0) {
                rooms.add(end);
                continue;
            }

            int early = rooms.peek();
            if(early <= start) {
                rooms.poll();
                rooms.add(end);
            } else {
                rooms.add(end);
            }

        }

        answer = rooms.size();


        return answer;
    }

    private static int toMin(String str) {
        int min = 0;

        String[] t = str.split(":");

        min += 60 * Integer.parseInt(t[0]);
        min += Integer.parseInt(t[1]);

        return min;
    }
}
