import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

public class FoobarChallenge {
    public static void main(String[] args) {
        int[] x = new int[]{4, 3, 10, 2, 8};
        int[] y = new int[]{5, 2, 5, 13};
        int[] result = stationCodedMsg(x, 12);

        int msg = enrouteSaluteV2("<<>><");
        System.out.println(msg);
    }

    public static int solution(int[] x, int[] y) {
        int result = 0;
        if (x.length > y.length) {
            for (int ids : x) {
                if (!isContains(y, ids)) {
                    result = ids;
                }
            }
        } else {
            for (int ids : y) {
                if (!isContains(x, ids)) {
                    result = ids;
                }
            }
        }

        return result;
    }

    public static boolean isContains(int[] x, int key) {
        return IntStream.of(x).anyMatch(value -> value == key);
    }

    public static int[] stationCodedMsg(int[] l, int t) {
        int[] result = new int[]{-1, -1};
        int sum = 0;

        for (int i = 0; i < l.length; i++) {

            for (int j = i; j < l.length; j++) {
                sum += l[j];

                if (sum == t) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
            sum = 0;
        }

        return result;
    }

    /*
    En Route Salute
===============

Commander Lambda loves efficiency and hates anything that wastes time. The Commander is a busy lamb, after all! Henchmen who identify sources of inefficiency and come up with ways to remove them are generously rewarded. You've spotted one such source, and you think solving it will help you build the reputation you need to get promoted.

Every time the Commander's employees pass each other in the hall, each of them must stop and salute each other -- one at a time -- before resuming their path. A salute is five seconds long, so each exchange of salutes takes a full ten seconds (Commander Lambda's salute is a bit, er, involved). You think that by removing the salute requirement, you could save several collective hours of employee time per day. But first, you need to show the Commander how bad the problem really is.

Write a program that counts how many salutes are exchanged during a typical walk along a hallway. The hall is represented by a string. For example:
"--->-><-><-->-"

Each hallway string will contain three different types of characters: '>', an employee walking to the right; '<', an employee walking to the left; and '-', an empty space. Every employee walks at the same speed either to right or to the left, according to their direction. Whenever two employees cross, each of them salutes the other. They then continue walking until they reach the end, finally leaving the hallway. In the above example, they salute 10 times.

Write a function solution(s) which takes a string representing employees walking along a hallway and returns the number of times the employees will salute. s will contain at least 1 and at most 100 characters, each one of -, >, or <.

Languages
=========

To provide a Python solution, edit solution.py
To provide a Java solution, edit Solution.java

Test cases
==========
Your code should pass the following test cases.
Note that it may also be run against hidden test cases not shown here.

-- Python cases --
Input:
solution.solution(">----<")
Output:
    2

Input:
solution.solution("<<>><")
Output:
    4

-- Java cases --
Input:
Solution.solution("<<>><")
Output:
    4

Input:
Solution.solution(">----<")
Output:
    2
     */

    public static int enrouteSalute(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '>') {
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) == '<') {
                        result++;
                    }
                }
            }
        }
        return result * 2;
    }

    public static int enrouteSaluteV2(String s) {
        int result = 0;
        int toRight = 0;
        String str[] = s.split("");

        for(String salute: str) {
            if(salute.equalsIgnoreCase(">")) {
                toRight++;
            } else if (salute.equalsIgnoreCase("<")) {
                result += toRight;
            }
        }

        return result* 2;
    }
}
