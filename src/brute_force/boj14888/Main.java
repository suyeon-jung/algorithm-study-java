package brute_force.boj14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

public class Main {

    private static int N;
    private static Map<String, Integer> operandMap = new HashMap<>(){
        {
            put("+", 0);
            put("-", 0);
            put("*", 0);
            put("/", 0);
        }
    };
    private static String[] operand = new String[]{"+", "-", "*", "/"};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());




    }
}
