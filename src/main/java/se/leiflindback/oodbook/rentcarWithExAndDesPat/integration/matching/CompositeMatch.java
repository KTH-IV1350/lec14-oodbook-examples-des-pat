package se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.matching;

import java.util.ArrayList;
import java.util.List;
import se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.CarDTO;

/**
 * Contains a collection of <code>Matcher</code>s, and invokes all of them in the same order they
 * were added to the collection. The result of a match will be the first matching car found by any
 * of the <code>Matcher</code>s.
 */
class CompositeMatch implements Matcher {
    private List<Matcher> matchers = new ArrayList<>();

    CompositeMatch() {
    }
    
    /**
     * Invokes all matching algorithms added to this composite, in the same order they were added,
     * until an algorithm finds a car. When a matching algorithm has found a car, that car is
     * returned, and no more algorithms are called.
     *
     * @param searched  Search criteria
     * @param available Available cars
     * @return A matching car, or <code>null</code> if none was found.
     */
    @Override
    public CarDTO match(CarDTO searched, List<CarDTO> available) {
        for (Matcher matcher : matchers) {
            CarDTO found = matcher.match(searched, available);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    /**
     * Adds the specified <code>Matcher</code> to the collection of <code>Matcher</code>s to invoke.
     * The matcher will be invoked after previously added <code>Matcher</code>s.
     * 
     * @param matcher The matcher to add.
     */
    public void addMatcher(Matcher matcher) {
        matchers.add(matcher);
    }
}
