package williamsadeho.parentalcontrol.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import williamsadeho.parentalcontrol.api.ParentalControlService;
import williamsadeho.parentalcontrol.model.ParentalControlInformation;
import williamsadeho.parentalcontrol.model.ParentalControlLevel;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * Unit test cases for the {@link ParentalControlController}
 *
 * A separate spring integration test is written to test the REST controllers from end to end. {@link ParentalControlControllerIntegrationTest}
 */
@RunWith(MockitoJUnitRunner.class)
public class ParentalControlControllerTest {

    @InjectMocks
    private ParentalControlController parentalControlController;

    @Mock
    private ParentalControlService mockParentalControlService;

    @Test
    public void shouldReturnTrueIfCustomerIsAbleToWatchMovie() throws Exception {
        //Given
        final ParentalControlLevel parentalControlLevel = ParentalControlLevel.FIFTEEN;
        final String movieId = "Avengers";
        given(mockParentalControlService.isAbleToWatchMovie(parentalControlLevel, movieId)).willReturn(true);
        final ParentalControlInformation expectedInfo = new ParentalControlInformation(parentalControlLevel, movieId, true);

        //When
        final ParentalControlInformation result = parentalControlController.getParentalControlInfo("15", movieId);

        //Then
        then(mockParentalControlService).should().isAbleToWatchMovie(parentalControlLevel, movieId);
        assertThat(result, is(expectedInfo));
    }

    @Test
    public void shouldReturnFalseIfCustomerIsNotAbleToWatchMovie() throws Exception {
        //Given
        final ParentalControlLevel parentalControlLevel = ParentalControlLevel.FIFTEEN;
        final String movieId = "Dunkirk";
        given(mockParentalControlService.isAbleToWatchMovie(parentalControlLevel, movieId)).willReturn(false);
        final ParentalControlInformation expectedInfo = new ParentalControlInformation(parentalControlLevel, movieId, false);

        //When
        final ParentalControlInformation result = parentalControlController.getParentalControlInfo("15", movieId);

        //Then
        then(mockParentalControlService).should().isAbleToWatchMovie(parentalControlLevel, movieId);
        assertThat(result, is(expectedInfo));
    }
}