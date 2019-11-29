package williamsadeho.parentalcontrol.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import williamsadeho.parentalcontrol.exception.TechnicalFailureException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test case for {@link ParentalControlLevel}
 *
 * @author williamsa
 */
public class ParentalControlLevelTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetParentalControlLevelByValue() throws Exception {
        //When
        final ParentalControlLevel result = ParentalControlLevel.getByValue("18");

        //Then
        assertThat(result, is(ParentalControlLevel.EIGHTEEN));
    }

    @Test
    public void shouldThrowTechnicalFailureExceptionIfControlLevelDoesNotExist() throws Exception {
        //Expect
        exception.expect(TechnicalFailureException.class);
        exception.expectMessage("Could not find parental control level for the given value: unknown");

        //When
        ParentalControlLevel.getByValue("unknown");
    }
}