package foobar;

import java.util.ArrayDeque;
import java.util.Arrays;

public class SolarDoomsday {
    public static void main(String[] args) {

    }

    public static int[] solution(int area) {
        int[] result = {};

        int idx = area;

        while(idx >= 1) {
            while (Arrays.stream(result).sum() < area){
                
            }
        }
        return result;
    }

    public static int helper(int value) {
        return (int) (Math.sqrt(value) + 0.5);
    }
}
