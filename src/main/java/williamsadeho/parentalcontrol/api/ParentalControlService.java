package williamsadeho.parentalcontrol.api;

import williamsadeho.parentalcontrol.exception.TechnicalFailureException;
import williamsadeho.parentalcontrol.exception.TitleNotFoundException;
import williamsadeho.parentalcontrol.model.ParentalControlLevel;

/**
 * API service methods to retrieve parental control information for movies.
 *
 * @author - Williams Adeho
 */
public interface ParentalControlService {

    /**
     * Check if customer is able to watch a movie based on the supplied control level preference and movie Id.
     *
     * @param parentalControlLevelPreference     - customers preferred parent control level.
     * @param movieId     - the movie Id of the movie to watch.
     * @return - returns engagement with ratings.
     */
    boolean isAbleToWatchMovie(ParentalControlLevel parentalControlLevelPreference, String movieId) throws TitleNotFoundException, TechnicalFailureException;
}
