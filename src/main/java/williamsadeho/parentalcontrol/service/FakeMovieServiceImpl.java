package williamsadeho.parentalcontrol.service;

import org.springframework.stereotype.Service;
import williamsadeho.parentalcontrol.api.MovieService;
import williamsadeho.parentalcontrol.exception.TechnicalFailureException;
import williamsadeho.parentalcontrol.exception.TitleNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * A fake implementation for the {@link MovieService} API methods.
 *
 * @author Williams Adeho
 */
@Service("movieService")
public class FakeMovieServiceImpl implements MovieService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
        final Map<String, String> movieIdToParentalControl = new HashMap<>();
        movieIdToParentalControl.put("avengers", "15");
        movieIdToParentalControl.put("mummy", "PG");
        movieIdToParentalControl.put("dunkirk", "18");
        movieIdToParentalControl.put("spiderman", "12");
        movieIdToParentalControl.put("kiddy", "U");
        movieIdToParentalControl.put("failure", "");

        final String lowerCaseMovieId = movieId.toLowerCase();
        if("failure".equalsIgnoreCase(lowerCaseMovieId)){
            throw new TechnicalFailureException("You cannot watch this movie, as we are currently experience a technical failure.");
        }

        if(movieIdToParentalControl.containsKey(lowerCaseMovieId)) {
            return movieIdToParentalControl.get(lowerCaseMovieId);
        } else {
            throw new TitleNotFoundException("The movie service could not find the given movie: " + movieId);
        }
    }
}