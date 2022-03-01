import java.util.stream.IntStream;

public class FoobarChallenge {
    public static void main(String[] args) {
        int[] x = new int[]{4, 3, 10, 2, 8};
        int[] y = new int[]{5, 2, 5, 13};
        int[] result = stationCodedMsg(x, 12);

        int msg = enrouteSalute("<<>><");
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
}
