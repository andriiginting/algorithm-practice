package algomonster;

import java.util.ArrayDeque;
import java.util.HashMap;

public class ValidParent {
    public static void main(String[] args) {
        boolean result = validParent("([][]{}){}{");

        System.out.println(result);
    };

    public static boolean validParent(String s) {
        HashMap<Character, Character> validMap = new HashMap<>();
        validMap.put('(', ')');
        validMap.put('[', ']');
        validMap.put('{', '}');

        ArrayDeque<Character> deque = new ArrayDeque<>();

        //([][]{}){}{
        String bracketList = "{}()[]";
        for (char bracket: s.toCharArray()) {
            if (validMap.getOrDefault(deque.peekFirst(), ' ') == bracket) {
                deque.pop();
            } else {
                if (isContains(bracket)) {
                    deque.add(bracket);
                }
            }
        }

        return deque.isEmpty();
    };

    public static boolean isContains(char bracket) {
        String bracketList = "{}()[]";

        for(char item: bracketList.toCharArray()) {
            if (item != bracket) {
                return false;
            }
        }

        return true;
    }
}
