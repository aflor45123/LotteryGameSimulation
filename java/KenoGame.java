
import java.util.*;

public class KenoGame {
	
    // Randomly pick spots unique numbers based on number of spots
    public Set<Integer> autoPick(int spots) {
    	
        return autoPick(spots, new Random());
    }
    
    
    // Checks if number of spots matches spot choices
    public Set<Integer> autoPick(int spots, Random rng) {
    	
        if (!(spots == 1 || spots == 4 || spots == 8 || spots == 10)) {
            throw new IllegalArgumentException("spots must be 1, 4, 8, or 10");
        }
        
        Set<Integer> picks = new LinkedHashSet<>();
        
        while (picks.size() < spots) {
            picks.add(rng.nextInt(80) + 1);
        }
        
        
        return picks;
    }

    
    // Draw random numbers
    public List<Integer> draw20() {
    	
        return draw20(new Random());
    }
    
    
    // This method shuffles a list of integers into a random order
    public List<Integer> draw20(Random rng) {
    	
        List<Integer> pool = new ArrayList<>(80);
        
        for (int i = 1; i <= 80; i++) {
        	pool.add(i);
        }
        
        Collections.shuffle(pool, rng);
        
        
        return new ArrayList<>(pool.subList(0, 20));
    }

    
    // Count matches between user spot selections and drawn numbers
    public int countMatches(Set<Integer> picks, Collection<Integer> drawn) {
    	
        int hits = 0;
        
        for (int n : drawn) if (picks.contains(n)) hits++;
        
        
        return hits;
    }
}
