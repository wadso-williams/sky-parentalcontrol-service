package williamsadeho.parentalcontrol.model;

import java.util.Comparator;

/**
 * Allow customers to watch movies with ratings less than the customer's preference.
 *
 * @author williamsa
 */
public class ParentalControlLevelComparator implements Comparator<ParentalControlLevel> {

    @Override
    public int compare(ParentalControlLevel level1, ParentalControlLevel level2) {
        return level1.ordinal() - level2.ordinal();
    }
}
