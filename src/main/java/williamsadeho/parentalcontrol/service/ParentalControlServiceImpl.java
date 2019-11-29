package williamsadeho.parentalcontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import williamsadeho.parentalcontrol.api.MovieService;
import williamsadeho.parentalcontrol.api.ParentalControlService;
import williamsadeho.parentalcontrol.exception.TechnicalFailureException;
import williamsadeho.parentalcontrol.exception.TitleNotFoundException;
import williamsadeho.parentalcontrol.model.ParentalControlLevel;
import williamsadeho.parentalcontrol.model.ParentalControlLevelComparator;

import java.util.Comparator;

/**
 * Business Implementation methods of the {@link ParentalControlService}, to retrieve
 * parental control information for movies.
 */
@Service("parentalControlService")
public class ParentalControlServiceImpl implements ParentalControlService {

    private MovieService movieService;

    private final Comparator<ParentalControlLevel> levelComparator;

    @Autowired
    public ParentalControlServiceImpl(MovieService movieService) {
        this.movieService = movieService;
        this.levelComparator = new ParentalControlLevelComparator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAbleToWatchMovie(ParentalControlLevel preferredRating, String movieId) throws TitleNotFoundException, TechnicalFailureException {
        final String movieRatingString = movieService.getParentalControlLevel(movieId);
        final ParentalControlLevel movieRating = ParentalControlLevel.getByValue(movieRatingString);
        return matchesPreferredRating(preferredRating, movieRating);
    }

    private boolean matchesPreferredRating(ParentalControlLevel preferredRating, ParentalControlLevel movieRating) {
        return levelComparator.compare(movieRating, preferredRating) <= 0;
    }
}
