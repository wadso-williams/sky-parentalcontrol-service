package williamsadeho.parentalcontrol.api;

import williamsadeho.parentalcontrol.exception.TechnicalFailureException;
import williamsadeho.parentalcontrol.exception.TitleNotFoundException;

/**
 * API service methods to provide all necessary information about a movie.
 *
 * @author - Williams Adeho
 */
public interface MovieService {

    /**
     * Fetch the parental control level for the given movie Id.
     *
     * @param movieId     - the movie Id of the movie to watch.
     * @return - movie parental control level.
     */
    String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;
}
