package williamsadeho.parentalcontrol.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import williamsadeho.parentalcontrol.api.MovieService;
import williamsadeho.parentalcontrol.exception.TechnicalFailureException;
import williamsadeho.parentalcontrol.exception.TitleNotFoundException;
import williamsadeho.parentalcontrol.model.ParentalControlLevel;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test cases for {@link ParentalControlServiceImpl}
 * Testing the concrete objects instead of mocking the fake movie service.
 *
 * @author williamsa
 */
public class ParentalControlServiceImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ParentalControlServiceImpl testSubject;

    private MovieService movieService;

    @Before
    public void setTestSubject() {
        movieService = new FakeMovieServiceImpl();
        testSubject = new ParentalControlServiceImpl(movieService);
    }

    @Test
    public void shouldBeAbleToWatchMovieIfMovieRatingIsEqualToCustomerPreference() throws Exception {
        //Given
        final ParentalControlLevel parentalControlLevel = ParentalControlLevel.FIFTEEN;
        final String movieId = "Avengers";

        //When
        boolean result = testSubject.isAbleToWatchMovie(parentalControlLevel, movieId);

        //Then
        assertThat(result, is(true));
    }

    @Test
    public void shouldBeAbleToWatchMovieIfMovieRatingIsLowerThanCustomerPreference() throws Exception {
        //Given
        final ParentalControlLevel parentalControlLevel = ParentalControlLevel.EIGHTEEN;
        final String movieId = "Spiderman";

        //When
        final boolean result = testSubject.isAbleToWatchMovie(parentalControlLevel, movieId);

        //Then
        assertThat(result, is(true));
    }

    @Test
    public void shouldNotBeAbleToWatchMovieIfMovieRatingIsHigherThanCustomerPreference() throws Exception {
        //Given
        final ParentalControlLevel parentalControlLevel = ParentalControlLevel.PARENTAL_GUIDANCE;
        final String movieId = "Avengers";

        //When
        final boolean result = testSubject.isAbleToWatchMovie(parentalControlLevel, movieId);

        //Then
        assertThat(result, is(false));
    }

    @Test
    public void shouldThrowExceptionIfThereIsATechnicalFailure() throws Exception {
        //Given
        final ParentalControlLevel parentalControlLevel = ParentalControlLevel.FIFTEEN;
        final String movieId = "Failure";

        //Expect
        exception.expect(TechnicalFailureException.class);
        exception.expectMessage("You cannot watch this movie, as we are currently experience a technical failure.");

        //When
        testSubject.isAbleToWatchMovie(parentalControlLevel, movieId);
    }

    @Test
    public void shouldThrowExceptionIfMovieTitleNotFound() throws Exception {
        //Given
        final ParentalControlLevel parentalControlLevel = ParentalControlLevel.FIFTEEN;
        final String movieId = "Not Sure";

        //Expect
        exception.expect(TitleNotFoundException.class);
        exception.expectMessage("The movie service could not find the given movie");

        //When
        testSubject.isAbleToWatchMovie(parentalControlLevel, movieId);
    }
}