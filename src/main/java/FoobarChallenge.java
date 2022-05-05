import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;

public class FoobarChallenge {
    public static void main(String[] args) {
        int[] x = new int[]{4, 3, 10, 2, 8};
        int[] y = new int[]{5, 2, 5, 13};
        int[] result = stationCodedMsg(x, 12);

        int stairs = grandestStaircase(3);
        System.out.println(stairs);
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

        for (String salute : str) {
            if (salute.equalsIgnoreCase(">")) {
                toRight++;
            } else if (salute.equalsIgnoreCase("<")) {
                result += toRight;
            }
        }

        return result * 2;
    }

    /*
    Elevator Maintenance
====================

You've been assigned the onerous task of elevator maintenance -- ugh! It wouldn't be so bad, except that all the elevator documentation has been lying in a disorganized pile at the bottom of a filing cabinet for years, and you don't even know what elevator version numbers you'll be working on.

Elevator versions are represented by a series of numbers, divided up into major, minor and revision integers. New versions of an elevator increase the major number, e.g. 1, 2, 3, and so on. When new features are added to an elevator without being a complete new version, a second number named "minor" can be used to represent those new additions, e.g. 1.0, 1.1, 1.2, etc. Small fixes or maintenance work can be represented by a third number named "revision", e.g. 1.1.1, 1.1.2, 1.2.0, and so on. The number zero can be used as a major for pre-release versions of elevators, e.g. 0.1, 0.5, 0.9.2, etc (Commander Lambda is careful to always beta test her new technology, with her loyal henchmen as subjects!).

Given a list of elevator versions represented as strings, write a function solution(l) that returns the same list sorted in ascending order by major, minor, and revision number so that you can identify the current elevator version. The versions in list l will always contain major numbers, but minor and revision numbers are optional. If the version contains a revision number, then it will also have a minor number.

For example, given the list l as ["1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"], the function solution(l) would return the list ["1.0", "1.0.2", "1.0.12", "1.1.2", "1.3.3"]. If two or more versions are equivalent but one version contains more numbers than the others, then these versions must be sorted ascending based on how many numbers they have, e.g ["1", "1.0", "1.0.0"]. The number of elements in the list l will be at least 1 and will not exceed 100.

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
solution.solution(["1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"])
Output:
    0.1,1.1.1,1.2,1.2.1,1.11,2,2.0,2.0.0

Input:
solution.solution(["1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"])
Output:
    1.0,1.0.2,1.0.12,1.1.2,1.3.3

-- Java cases --
Input:
Solution.solution({"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"})
Output:
    0.1,1.1.1,1.2,1.2.1,1.11,2,2.0,2.0.0

Input:
Solution.solution({"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"})
Output:
    1.0,1.0.2,1.0.12,1.1.2,1.3.3
     */

    @NotNull
    public static String[] elevationMantain(String[] l) {
        List<String[]> list = new ArrayList<>();

        for (String version : l) {
            String[] rawVersion = version.split("\\.");
            list.add(rawVersion);
        }

        list.sort((first, second) -> {
                    int idx = 0;
                    while (idx < Math.min(first.length, second.length)) {
                        int parse = Integer.compare(
                                Integer.parseInt(first[idx]),
                                Integer.parseInt(second[idx])
                        );

                        if (parse != 0) {
                            return parse;
                        }
                        idx++;
                    }
                    return Integer.compare(first.length, second.length);
                }
        );

        return list.stream()
                .map(strings -> String.join(".", strings))
                .toArray(String[]::new);
    }

    public static String[] elevatorMaintenance(String[] l) {
        Comparator<String> comparator = (o1, o2) -> {
            String[] first = o1.split("\\.");
            String[] second = o2.split("\\.");

            int parse = Integer.parseInt(first[0]) - Integer.parseInt(second[0]);

            if (parse != 0) {
                return parse;
            }

            if (first.length == 1 || second.length == 1) {
                return first.length - second.length;
            }

            int minorVersion = Integer.parseInt(first[1]) - Integer.parseInt(second[1]);
            if (minorVersion != 0) {
                return minorVersion;
            }

            if (first.length == 2 || second.length == 2) {
                return first.length - second.length;
            }

            return Integer.parseInt(first[2]) - Integer.parseInt(second[2]);
        };
        Arrays.sort(l, comparator);
        return l;
    }

    /*
    The Grandest Staircase Of Them All
==================================

With the LAMBCHOP doomsday device finished, Commander Lambda is preparing to debut on the galactic stage -- but in order to make a grand entrance, Lambda needs a grand staircase! As the Commander's personal assistant, you've been tasked with figuring out how to build the best staircase EVER.

Lambda has given you an overview of the types of bricks available, plus a budget. You can buy different amounts of the different types of bricks (for example, 3 little pink bricks, or 5 blue lace bricks). Commander Lambda wants to know how many different types of staircases can be built with each amount of bricks, so they can pick the one with the most options.

Each type of staircase should consist of 2 or more steps.  No two steps are allowed to be at the same height - each step must be lower than the previous one. All steps must contain at least one brick. A step's height is classified as the total amount of bricks that make up that step.
For example, when N = 3, you have only 1 choice of how to build the staircase, with the first step having a height of 2 and the second step having a height of 1: (# indicates a brick)

#
##
21

When N = 4, you still only have 1 staircase choice:

#
#
##
31

But when N = 5, there are two ways you can build a staircase from the given bricks. The two staircases can have heights (4, 1) or (3, 2), as shown below:

#
#
#
##
41

#
##
##
32

Write a function called solution(n) that takes a positive integer n and returns the number of different staircases that can be built from exactly n bricks. n will always be at least 3 (so you can have a staircase at all), but no more than 200, because Commander Lambda's not made of money!
     */

    public static int grandestStaircase(int n) {
        Integer[][] stair = new Integer[n + 2][n + 2];
        initStair(n+2, stair);
        return staircaseHelper(1, n, stair) - 1;
    }

    private static void initStair(int n, Integer[][] stair) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                stair[i][j] = 0;
            }
        }
    }

    private static int staircaseHelper(int i, int j, Integer[][] stair) {
        if (stair[i][j] != 0) {
            return stair[i][j];
        }

        if (j == 0) {
            return 1;
        }

        if (j < i) {
            return 0;
        }

        stair[i][j] = staircaseHelper(i + 1, j - i, stair) + staircaseHelper(i + 1, j, stair);
        return stair[i][j];
    }
}
