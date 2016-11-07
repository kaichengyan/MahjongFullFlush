import java.util.ArrayList;
import java.util.List;

public class Mahjong {

    private static final int NUM_TYPES = 9;
    private static final int NUM_CARDS = 14;

    private List<Integer> cards;
    private int[] counters;

    // Pre:
    //  - "s" should be a string of length 14 consisting only of digits from
    //      0-9.
    // Post:
    //  - Constructs a Mahjong hand consisting of all digits in "s".
    public Mahjong(String s) {
        if (s.length() != NUM_CARDS) {
            throw new IllegalArgumentException();
        }
        this.cards = new ArrayList<>();
        this.counters = new int[NUM_TYPES];
        for (char c : s.toCharArray()) {
            this.counters[Character.getNumericValue(c) - 1]++;
        }
        for (int i = 0; i < NUM_TYPES; i++) {
            for (int j = 0; j < this.counters[i]; j++) {
                this.cards.add(i+1);
            }
        }
    }

    // Post:
    //  - Returns true if the hand of Mahjong wins by a full flush.
    public boolean isFullFlush() {
        boolean result = false;
        if (this.currentTotalCount() < 3) {
            return this.remainsAPair();
        }
        for (int i = 0; i < NUM_TYPES; i++) {
            if (i + 2 < NUM_TYPES && this.counters[i] > 0
                    && this.counters[i+1] > 0 && this.counters[i+2] > 0) {
                this.counters[i]--;
                this.counters[i+1]--;
                this.counters[i+2]--;
                if (isFullFlush()) result = true;
                this.counters[i]++;
                this.counters[i+1]++;
                this.counters[i+2]++;
            }
            if (this.counters[i] >= 3) {
                this.counters[i] -= 3;
                if (isFullFlush()) result = true;
                this.counters[i] += 3;
            }
        }
        return result;
    }

    // Post:
    //  - Returns the current total number of cards in the hand.
    private int currentTotalCount() {
        int sum = 0;
        for (int count : this.counters) {
            sum += count;
        }
        return sum;
    }

    // Post:
    //  - Returns true if there is only a pair left in the hand.
    private boolean remainsAPair() {
        int pairCounts = 0;
        for (int count : this.counters) {
            if (count != 0 && count != 2) return false;
            if (count == 2) pairCounts++;
        }
        return pairCounts == 1;
    }

    // Post:
    //  - Returns a String representation of the Mahjong hand, sorted by
    //      natrual order.
    @Override
    public String toString() {
        return this.cards.toString();
    }
}
