package williamsadeho.parentalcontrol.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

/**
 * Unit test cases for {@link ParentalControlLevelComparator}
 *
 * @author williamsa
 */
public class ParentalControlLevelComparatorTest {

    @Test
    public void compare_shouldOrderValuesAccordingToProvidedList() throws Exception {
        //Given
        final ParentalControlLevelComparator testSubject = new ParentalControlLevelComparator();

        // Then
        assertThat(testSubject.compare(ParentalControlLevel.TWELVE, ParentalControlLevel.FIFTEEN), is(lessThan(0)));
        assertThat(testSubject.compare(ParentalControlLevel.TWELVE, ParentalControlLevel.EIGHTEEN), is(lessThan(0)));
        assertThat(testSubject.compare(ParentalControlLevel.FIFTEEN, ParentalControlLevel.UNDERAGE), is(greaterThan(0)));
        assertThat(testSubject.compare(ParentalControlLevel.PARENTAL_GUIDANCE, ParentalControlLevel.PARENTAL_GUIDANCE), is(equalTo(0)));
    }
}