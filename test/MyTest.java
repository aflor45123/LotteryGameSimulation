import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

class MyTest {

    private final KenoGame game = new KenoGame();

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 8, 10})
    @DisplayName("Auto-pick returns exactly {spots} unique numbers in range 1..80")
    void autoPick_sizeAndRange(int spots) {
        Set<Integer> picks = game.autoPick(spots, new Random(12345)); // seeded for determinism
        assertEquals(spots, picks.size(), "wrong pick count");
        for (int n : picks) {
            assertTrue(n >= 1 && n <= 80, "pick out of range: " + n);
        }
        // ensure uniqueness
        assertEquals(spots, new HashSet<>(picks).size(), "duplicates in autoPick");
    }

    @Test
    @DisplayName("Random numbers selected by system: draw20 returns 20 unique numbers in range 1..80")
    void draw20_uniqueAndInRange() {
        List<Integer> drawn = game.draw20(new Random(999));
        assertEquals(20, drawn.size(), "must draw 20 numbers");

        Set<Integer> uniq = new HashSet<>(drawn);
        assertEquals(20, uniq.size(), "duplicates in draw20");

        for (int n : drawn) {
            assertTrue(n >= 1 && n <= 80, "draw out of range: " + n);
        }
    }

    @Test
    @DisplayName("User selected number matching system selected -> correct hit count")
    void userSelections_matchCount_positive() {
        // user picks
        Set<Integer> picks = new LinkedHashSet<>(Arrays.asList(3, 7, 11, 42));
        // system draws (has 2 matches: 7 and 42)
        List<Integer> drawn = Arrays.asList(1, 2, 7, 9, 42, 55, 60, 61, 62, 63, 64, 65, 10, 11, 12, 13, 14, 15, 16, 17);
        int hits = game.countMatches(picks, drawn);
        assertEquals(3, hits, "expected 3 matches (7, 11, 42)");
    }

    @Test
    @DisplayName("User selected number not matching system selected -> zero hits")
    void userSelections_noMatches_zeroHits() {
        Set<Integer> picks = new LinkedHashSet<>(Arrays.asList(71, 72, 73, 74));
        List<Integer> drawn = Arrays.asList(1, 2, 7, 9, 42, 55, 60, 61, 62, 63, 64, 65, 10, 11, 12, 13, 14, 15, 16, 17);
        int hits = game.countMatches(picks, drawn);
        assertEquals(0, hits, "expected zero matches");
    }

    @Test
    @DisplayName("Auto-pick + draw20 integration: intersection never exceeds min(spots, 20)")
    void integration_autoPickVsDraw20_intersectionBound() {
        int spots = 10;
        Set<Integer> picks = game.autoPick(spots, new Random(42));
        List<Integer> drawn = game.draw20(new Random(42));

        int hits = game.countMatches(picks, drawn);
        assertTrue(hits >= 0 && hits <= Math.min(spots, 20));
    }
}
