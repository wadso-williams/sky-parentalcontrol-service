package williamsadeho.parentalcontrol.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test cases for {@link ParentalControlInformation}
 *
 * @author williamsa
 */
public class ParentalControlInformationTest {

    @Test
    public void shouldDisplayCanWatchMovieMessageIfAllowedToWatchMovie() throws Exception {
        //Given
        final ParentalControlInformation testSubject = new ParentalControlInformation(ParentalControlLevel.FIFTEEN, "Avengers", true);

        //Then
        assertThat(testSubject.getMessage(), is("You can watch this movie"));
    }

    @Test
    public void shouldDisplayCannotWatchMovieMessageIfAllowedToWatchMovie() throws Exception {
        //Given
        final ParentalControlInformation testSubject = new ParentalControlInformation(ParentalControlLevel.TWELVE, "Avengers", false);

        //Then
        assertThat(testSubject.getMessage(), is("You cannot watch this movie"));
    }

}