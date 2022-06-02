import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;

public class FoobarChallenge {
    public static void main(String[] args) {
        int[] x = new int[]{4, 3, 10, 2, 8};
        int[] y = new int[]{5, 2, 5, 13};
        int[] result = stationCodedMsg(x, 12);

        int[] stairs = doomsDayFuel(
                new int[][]{{0, 2, 1, 0, 0}, {0, 0, 0, 3, 4}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}}
        );
        System.out.println(Arrays.toString(stairs));
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
        String[] str = s.split("");

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
        initStair(n + 2, stair);
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

    /*
    Find the Access Codes
=====================

In order to destroy Commander Lambda's LAMBCHOP doomsday device, you'll need access to it. But the only door leading to
the LAMBCHOP chamber is secured with a unique lock system whose number of passcodes changes daily. Commander Lambda gets
a report every day that includes the locks' access codes, but only the Commander knows how to figure out which of
several
 lists contains the access codes. You need to find a way to determine which list contains the access codes once you're
 ready to go in.

Fortunately, now that you're Commander Lambda's personal assistant, Lambda has confided to you that all the access codes
 are "lucky triples" in order to make it easier to find them in the lists. A "lucky triple" is a tuple (x, y, z) where
 x divides y and y divides z, such as (1, 2, 4). With that information, you can figure out which list contains the
 number of access codes that matches the number of locks on the door when you're ready to go in (for example, if there's
  5 passcodes, you'd need to find a list with 5 "lucky triple" access codes).

Write a function solution(l) that takes a list of positive integers l and counts the number of "lucky triples"
of (li, lj, lk) where the list indices meet the requirement i < j < k.  The length of l is between 2 and 2000 inclusive.
  The elements of l are between 1 and 999999 inclusive.  The solution fits within a signed 32-bit integer. Some of the
  lists are purposely generated without any access codes to throw off spies, so if no triples are found, return 0.

For example, [1, 2, 3, 4, 5, 6] has the triples: [1, 2, 4], [1, 2, 6], [1, 3, 6], making the solution 3 total.
     */

    public static int findAccessCode(int[] l) {
        int result = 0;
        int[] arr = new int[l.length];

        for (int i = 1; i < l.length - 1; ++i) {
            for (int j = 0; j < i; ++j) {
                if (l[i] % l[j] == 0) {
                    ++arr[i];
                }
            }
        }

        for (int i = 2; i < l.length; i++) {
            for (int j = 1; j < i; ++j) {
                if (l[i] % l[j] == 0) {
                    result += arr[j];
                }
            }
        }

        return result;
    }

    /*
    Doomsday Fuel
=============

Making fuel for the LAMBCHOP's reactor core is a tricky process because of the exotic matter involved. It starts as raw
ore, then during processing, begins randomly changing between forms, eventually reaching a stable form. There may be
multiple stable forms that a sample could ultimately reach, not all of which are useful as fuel.

Commander Lambda has tasked you to help the scientists increase fuel creation efficiency by predicting the end state of
 a given ore sample. You have carefully studied the different structures that the ore can take and which transitions it
 undergoes. It appears that, while random, the probability of each structure transforming is fixed. That is, each time
 the ore is in 1 state, it has the same probabilities of entering the next state (which might be the same state).
 You have recorded the observed transitions in a matrix. The others in the lab have hypothesized more exotic forms that
 the ore can become, but you haven't seen all of them.

Write a function solution(m) that takes an array of array of nonnegative ints representing how many times that state has
 gone to the next state and return an array of ints for each terminal state giving the exact probabilities of each
 terminal state, represented as the numerator for each state, then the denominator for all of them at the end and in
 simplest form. The matrix is at most 10 by 10. It is guaranteed that no matter which state the ore is in, there is a
 path from that state to a terminal state. That is, the processing will always eventually end in a stable state.
 The ore starts in state 0. The denominator will fit within a signed 32-bit integer during the calculation, as long as
 the fraction is simplified regularly.

For example, consider the matrix m:
[
  [0,1,0,0,0,1],  # s0, the initial state, goes to s1 and s5 with equal probability
  [4,0,0,3,2,0],  # s1 can become s0, s3, or s4, but with different probabilities
  [0,0,0,0,0,0],  # s2 is terminal, and unreachable (never observed in practice)
  [0,0,0,0,0,0],  # s3 is terminal
  [0,0,0,0,0,0],  # s4 is terminal
  [0,0,0,0,0,0],  # s5 is terminal
]
So, we can consider different paths to terminal states, such as:
s0 -> s1 -> s3
s0 -> s1 -> s0 -> s1 -> s0 -> s1 -> s4
s0 -> s1 -> s0 -> s5
Tracing the probabilities of each, we find that
s2 has probability 0
s3 has probability 3/14
s4 has probability 1/7
s5 has probability 9/14
So, putting that together, and making a common denominator, gives an answer in the form of
[s2.numerator, s3.numerator, s4.numerator, s5.numerator, denominator] which is
[0, 3, 2, 9, 14].

refferences: https://github.com/ivanseed/google-foobar-help/blob/master/challenges/doomsday_fuel/doomsday_fuel.md
     */

    private static int[] doomsDayFuel(int[][] m) {
        // Your code here
        double[][] mDouble = toDouble(m);
        // GOAL ONE: find Q matrix :
        // 1:seperate the input into two 2d arrays
        ArrayList<double[]> ters = new ArrayList<>();
        ArrayList<double[]> nonTers = new ArrayList<>();
        for (int i = 0; i < mDouble.length; i++) {
            boolean isTerminal = true;
            int sum = 0;
            for (int j = 0; j < mDouble[0].length; j++) {
                sum += mDouble[i][j];
                if (mDouble[i][j] != 0) {
                    isTerminal = false;
                }
            }

            if (isTerminal) {
                ters.add(mDouble[i]);
            } else {
                for (int j = 0; j < mDouble[0].length; j++) {
                    mDouble[i][j] = mDouble[i][j] / sum;
                }
                nonTers.add(mDouble[i]);
            }
        }
        double[][] terminalStates = new double[ters.size()][m.length];
        double[][] nonTerminalStates = new double[nonTers.size()][m.length];

        for (int i = 0; i < ters.size(); i++) {
            terminalStates[i] = ters.get(i);
        }
        for (int i = 0; i < nonTers.size(); i++) {
            nonTerminalStates[i] = nonTers.get(i);
        }
        // 2: Plug into a function that will create the 2d array
        double[][] QMatrix = getQMatrix(nonTerminalStates);
        // GOAL TWO: find I matrix
        double[][] IMatrix = makeIMatrix(QMatrix.length);
        //GOAL 3: Find F matrix
        //1: subtract the q matrix from the I matrix
        double[][] AMatrix = subtractMatrices(IMatrix, QMatrix);
        //2: find the inverse TODO WRITE FUNCTION
        double[][] FMatrix = invert(AMatrix);
        //GOAL 4: multiply by R Matrix
        //1: find r Matrx
        double[][] RMatrix = getRMatrix(nonTerminalStates, terminalStates.length);
        //2: use multiply function to get FR Matrix
        double[][] FRMatrix = multiplyMatrices(FMatrix, RMatrix);
        //GOAL 5: find answer array
        //1: get the one dimensional answer
        double[] unsimplifiedAns = FRMatrix[0];
        //2: get fractions for the answers
        return fractionAns(unsimplifiedAns);
    }

    private static int[] fractionAns(double[] uAns) {
        int[] ans = new int[uAns.length + 1];
        int[] denominators = new int[uAns.length];
        int[] numerators = new int[uAns.length];
        for (int i = 0; i < uAns.length; i++) {
            denominators[i] = convertDecimalToFraction(uAns[i])[1];
            numerators[i] = convertDecimalToFraction(uAns[i])[0];
        }
        int lcm = (int) lcm_of_array_elements(denominators);
        for (int i = 0; i < uAns.length; i++) {
            ans[i] = numerators[i] * (lcm / convertDecimalToFraction(uAns[i])[1]);
        }
        ans[ans.length - 1] = lcm;
        return ans;
    }

    static private int[] convertDecimalToFraction(double x) {
        double tolerance = 1.0E-10;
        double h1 = 1;
        double h2 = 0;
        double k1 = 0;
        double k2 = 1;
        double b = x;
        do {
            double a = Math.floor(b);
            double aux = h1;
            h1 = a * h1 + h2;
            h2 = aux;
            aux = k1;
            k1 = a * k1 + k2;
            k2 = aux;
            b = 1 / (b - a);
        } while (Math.abs(x - h1 / k1) > x * tolerance);

        return new int[]{(int) h1, (int) k1};
    }

    private static long lcm_of_array_elements(int[] element_array) {
        long lcm_of_array_elements = 1;
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;

            for (int i = 0; i < element_array.length; i++) {

                // lcm_of_array_elements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcm_of_array_elements.

                if (element_array[i] == 0) {
                    return 0;
                } else if (element_array[i] < 0) {
                    element_array[i] = element_array[i] * (-1);
                }
                if (element_array[i] == 1) {
                    counter++;
                }

                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (element_array[i] % divisor == 0) {
                    divisible = true;
                    element_array[i] = element_array[i] / divisor;
                }
            }

            // If divisor able to completely divide any number
            // from array multiply with lcm_of_array_elements
            // and store into lcm_of_array_elements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            } else {
                divisor++;
            }

            // Check if all element_array is 1 indicate
            // we found all factors and terminate while loop.
            if (counter == element_array.length) {
                return lcm_of_array_elements;
            }
        }
    }

    private static double[][] toDouble(int[][] ma) {
        double[][] retArr = new double[ma.length][ma.length];
        for (int i = 0; i < retArr.length; i++) {
            for (int j = 0; j < retArr[0].length; j++) {
                retArr[i][j] = (ma[i][j]);
            }
        }
        return retArr;
    }

    private static double[][] getRMatrix(double[][] nonTerminals, int terminalLength) {
        double[][] retArr = new double[nonTerminals.length][terminalLength];
        for (int i = 0; i < retArr.length; i++) {
            if (nonTerminals[0].length - nonTerminals.length >= 0)
                System.arraycopy(
                        nonTerminals[i],
                        nonTerminals.length,
                        retArr[i], 0, nonTerminals[0].length - nonTerminals.length);
        }
        return retArr;
    }

    private static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        int r1 = firstMatrix.length;
        int c1 = firstMatrix[0].length;
        int c2 = secondMatrix[0].length;
        double[][] product = new double[r1][c2];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }

        return product;
    }

    public static double[][] inverseMatrix(double[][] amatrix) {
        return null;
    }

    private static double[][] subtractMatrices(double[][] I, double[][] Q) {
        double[][] retArr = new double[I.length][I.length];
        for (int i = 0; i < retArr.length; i++) {
            for (int j = 0; j < retArr.length; j++) {
                retArr[i][j] = I[i][j] - Q[i][j];
            }
        }
        return retArr;
    }

    private static double[][] getQMatrix(double[][] qArr) {
        int size = qArr.length;
        double[][] retArr = new double[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(qArr[i], 0, retArr[i], 0, size);
        }
        return retArr;
    }

    private static double[][] makeIMatrix(int size) {
        double[][] retArr = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    retArr[i][j] = 1;
                } else {
                    retArr[i][j] = 0;
                }
            }
        }
        return retArr;
    }

    private static double[][] invert(double[][] a) {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];

        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.

    private static void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double[] c = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }
}
