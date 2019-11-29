package williamsadeho.parentalcontrol.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import williamsadeho.parentalcontrol.exception.TechnicalFailureException;
import williamsadeho.parentalcontrol.exception.TitleNotFoundException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test cases for {@link FakeMovieServiceImpl}
 * Decided to break the tests into separate unit tests for readability.
 * Using a Data provider runner would reduce the number of similar tests.
 *
 * @author williamsa
 */
public class FakeMovieServiceImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetParentalControlLevel18ForMovieId() throws Exception {
        //Given
        final FakeMovieServiceImpl testSubject = new FakeMovieServiceImpl();
        final String movieId = "Dunkirk";

        //When
        final String result = testSubject.getParentalControlLevel(movieId);

        //Then
        assertThat(result, is("18"));
    }

    @Test
    public void shouldGetParentalControlLevel15ForMovieId() throws Exception {
        //Given
        final FakeMovieServiceImpl testSubject = new FakeMovieServiceImpl();
        final String movieId = "Avengers";

        //When
        final String result = testSubject.getParentalControlLevel(movieId);

        //Then
        assertThat(result, is("15"));
    }

    @Test
    public void shouldGetParentalControlLevel12ForMovieId() throws Exception {
        //Given
        final FakeMovieServiceImpl testSubject = new FakeMovieServiceImpl();
        final String movieId = "Spiderman";

        //When
        final String result = testSubject.getParentalControlLevel(movieId);

        //Then
        assertThat(result, is("12"));
    }

    @Test
    public void shouldGetParentalControlLevelPGForMovieId() throws Exception {
        //Given
        final FakeMovieServiceImpl testSubject = new FakeMovieServiceImpl();
        final String movieId = "mummy";

        //When
        final String result = testSubject.getParentalControlLevel(movieId);

        //Then
        assertThat(result, is("PG"));
    }

    @Test
    public void shouldGetParentalControlLevelUForMovieId() throws Exception {
        //Given
        final FakeMovieServiceImpl testSubject = new FakeMovieServiceImpl();
        final String movieId = "Kiddy";

        //When
        final String result = testSubject.getParentalControlLevel(movieId);

        //Then
        assertThat(result, is("U"));
    }

    @Test
    public void shouldExpectTitleNotFoundExceptionIfMovieIdNotFound() throws Exception {
        //Given
        final FakeMovieServiceImpl testSubject = new FakeMovieServiceImpl();
        final String movieId = "lutor";

        //Expect
        exception.expect(TitleNotFoundException.class);
        exception.expectMessage("The movie service could not find the given movie: "+ movieId);

        //When
        testSubject.getParentalControlLevel(movieId);
    }

    @Test
    public void shouldExpectTechnicalFailureExceptionIfMovieIdFailure() throws Exception {
        //Given
        final FakeMovieServiceImpl testSubject = new FakeMovieServiceImpl();
        final String movieId = "failure";

        //Expect
        exception.expect(TechnicalFailureException.class);
        exception.expectMessage("You cannot watch this movie, as we are currently experience a technical failure.");

        //When
        testSubject.getParentalControlLevel(movieId);
    }

}