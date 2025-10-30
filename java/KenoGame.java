
import java.util.*;

public class KenoGame {
	

    // Randomly pick `spots` unique numbers in [1..80]. Overload accepts seedable RNG for tests. //
    public Set<Integer> autoPick(int spots) {
        return autoPick(spots, new Random());
    }
    public Set<Integer> autoPick(int spots, Random rng) {
        if (!(spots == 1 || spots == 4 || spots == 8 || spots == 10)) {
            throw new IllegalArgumentException("spots must be 1, 4, 8, or 10");
        }
        Set<Integer> picks = new LinkedHashSet<>();
        while (picks.size() < spots) {
            picks.add(rng.nextInt(80) + 1); // 1..80
        }
        return picks;
    }

    /* Draw 20 unique numbers between 1-80. */
    public List<Integer> draw20() {
        return draw20(new Random());
    }
    public List<Integer> draw20(Random rng) {
        List<Integer> pool = new ArrayList<>(80);
        for (int i = 1; i <= 80; i++) {
        	pool.add(i);
        }
        Collections.shuffle(pool, rng);
        
        
        return new ArrayList<>(pool.subList(0, 20));
    }

    // Count matches between user picks and drawn numbers. //
    public int countMatches(Set<Integer> picks, Collection<Integer> drawn) {
        int hits = 0;
        for (int n : drawn) if (picks.contains(n)) hits++;
        return hits;
    }
}
