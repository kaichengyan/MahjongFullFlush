import java.util.ArrayList;
import java.util.List;

public class Mahjong {

    public static final int NUM_TYPES = 9;

    private List<Integer> cards;
    private int[] counters;

    public Mahjong(String s) {
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

    private int currentTotalCount() {
        int sum = 0;
        for (int count : this.counters) {
            sum += count;
        }
        return sum;
    }

    private boolean remainsAPair() {
        int pairCounts = 0;
        for (int count : this.counters) {
            if (count != 0 && count != 2) return false;
            if (count == 2) pairCounts++;
        }
        return pairCounts == 1;
    }

    @Override
    public String toString() {
        return this.cards.toString();
    }
}
