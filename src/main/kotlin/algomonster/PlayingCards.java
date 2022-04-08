package algomonster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayingCards {
    public static void main(String[] args) {

    }

    public static class Game {
        HashMap<String, String> deck;
        public Game() {
            deck = new HashMap<>();
        }

        public void addCard(String suit, String value) {
            deck.put(suit, value);
        }

        public String cardString(int card) {
            
            return "";
        }

        public boolean cardBeats(int cardA, int cardB) {
            // Implement function here
            return false;
        }
    }
}

