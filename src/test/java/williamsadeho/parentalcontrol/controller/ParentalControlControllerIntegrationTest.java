package williamsadeho.parentalcontrol.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import williamsadeho.parentalcontrol.api.ParentalControlService;
import williamsadeho.parentalcontrol.exception.TechnicalFailureException;
import williamsadeho.parentalcontrol.exception.TitleNotFoundException;
import williamsadeho.parentalcontrol.model.ParentalControlLevel;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Spring Integration test cases for the {@link ParentalControlController}
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ParentalControlController.class)
public class ParentalControlControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParentalControlService mockParentalControlService;

    @Test
    public void shouldReturnAuthenticationRequiredErrorMessage() throws Exception {
        //Given
        given(mockParentalControlService.isAbleToWatchMovie(ParentalControlLevel.FIFTEEN, "Avengers")).willReturn(true);

        //Then
        this.mockMvc.perform(get("/api/parentalControls/rating/15").param("movieId", "Avengers"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username="sky", password="sky", roles={"USER"})
    public void shouldReturnCannotWatchMovieMessageIfMovieRatingIsHigherThanCustomerPreference() throws Exception {
        //Given
        given(mockParentalControlService.isAbleToWatchMovie(ParentalControlLevel.TWELVE, "Avengers")).willReturn(false);

        //Then
        this.mockMvc.perform(get("/api/parentalControls/rating/12").param("movieId", "Avengers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'parentalControlLevel':'TWELVE','movieId':'Avengers','allowedToWatchMovie':false,'message':'You cannot watch this movie'}"));
    }

    @Test
    @WithMockUser(username="sky", password="sky", roles={"USER"})
    public void shouldReturnCanWatchMovieMessageIfMovieRatingIsEqualToCustomerPreference() throws Exception {
        //Given
        given(mockParentalControlService.isAbleToWatchMovie(ParentalControlLevel.FIFTEEN, "Avengers")).willReturn(true);

        //Then
        this.mockMvc.perform(get("/api/parentalControls/rating/15").param("movieId", "Avengers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'parentalControlLevel':'FIFTEEN','movieId':'Avengers','allowedToWatchMovie':true,'message':'You can watch this movie'}"));
    }

    @Test
    @WithMockUser(username="sky", password="sky", roles={"USER"})
    public void shouldReturnCanWatchMovieMessageIfMovieRatingIsLowerThanCustomerPreference() throws Exception {
        //Given
        given(mockParentalControlService.isAbleToWatchMovie(ParentalControlLevel.EIGHTEEN, "Avengers")).willReturn(true);

        //Then
        this.mockMvc.perform(get("/api/parentalControls/rating/18").param("movieId", "Avengers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'parentalControlLevel':'EIGHTEEN','movieId':'Avengers','allowedToWatchMovie':true,'message':'You can watch this movie'}"));
    }

    @Test
    @WithMockUser(username="sky", password="sky", roles={"USER"})
    public void shouldReturnMissedParameterMessage() throws Exception {
        //Then
        this.mockMvc.perform(get("/api/parentalControls/rating/18"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{'message':'Please supply movieId as a parameter i.e ?movieId=value','errors':['movieId is missing']}"));
    }

    @Test
    @WithMockUser(username="sky", password="sky", roles={"USER"})
    public void shouldReturnMessageIndicatingThatRatingCouldNotBeFound() throws Exception {

        //Then
        this.mockMvc.perform(get("/api/parentalControls/rating/PGF").param("movieId", "Avengers"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{'message':'Could not find parental control level for the given value: PGF','errors':['Could not find parental control level for the given value: PGF']}"));
    }

    @Test
    @WithMockUser(username="sky", password="sky", roles={"USER"})
    public void shouldReturnMessageIndicatingThatTitleCouldNotBeFound() throws Exception {
        //Given
        given(mockParentalControlService.isAbleToWatchMovie(ParentalControlLevel.PARENTAL_GUIDANCE, "Unknown")).willThrow(new TitleNotFoundException("Title Not found"));

        //Then
        this.mockMvc.perform(get("/api/parentalControls/rating/PG").param("movieId", "Unknown"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{'message':'Title Not found','errors':['internal error occurred']}"));
    }

    @Test
    @WithMockUser(username="sky", password="sky", roles={"USER"})
    public void shouldReturnMessageIndicatingThatTechnicalFailureOccured() throws Exception {
        //Given
        given(mockParentalControlService.isAbleToWatchMovie(ParentalControlLevel.PARENTAL_GUIDANCE, "Failure")).willThrow(new TechnicalFailureException("System error occurred"));

        //Then
        this.mockMvc.perform(get("/api/parentalControls/rating/PG").param("movieId", "Failure"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{'message':'System error occurred','errors':['System error occurred']}"));
    }
}