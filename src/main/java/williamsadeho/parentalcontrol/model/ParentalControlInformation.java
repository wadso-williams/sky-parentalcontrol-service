package williamsadeho.parentalcontrol.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParentalControlInformation {

    @ApiModelProperty(notes = "The parental control level or rating")
    private ParentalControlLevel parentalControlLevel;

    @ApiModelProperty(notes = "The movie ID")
    private String movieId;

    @ApiModelProperty(notes = "Indicate that movie can be watched")
    private boolean allowedToWatchMovie;

    @ApiModelProperty(notes = "Message to be displayed to customer")
    private String message;

    public ParentalControlInformation() {
        super();
    }

    public ParentalControlInformation(ParentalControlLevel parentalControlLevel, String movieId, boolean allowedToWatchMovie) {
        this.parentalControlLevel = parentalControlLevel;
        this.movieId = movieId;
        this.allowedToWatchMovie = allowedToWatchMovie;
    }

    public ParentalControlLevel getParentalControlLevel() {
        return parentalControlLevel;
    }

    public ParentalControlInformation setParentalControlLevel(ParentalControlLevel parentalControlLevel) {
        this.parentalControlLevel = parentalControlLevel;
        return this;
    }

    public String getMovieId() {
        return movieId;
    }

    public ParentalControlInformation setMovieId(String movieId) {
        this.movieId = movieId;
        return this;
    }

    public boolean isAllowedToWatchMovie() {
        return allowedToWatchMovie;
    }

    public ParentalControlInformation setAllowedToWatchMovie(boolean allowedToWatchMovie) {
        this.allowedToWatchMovie = allowedToWatchMovie;
        return this;
    }

    public String getMessage() {
        return allowedToWatchMovie ? "You can watch this movie" : "You cannot watch this movie";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParentalControlInformation that = (ParentalControlInformation) o;
        return allowedToWatchMovie == that.allowedToWatchMovie
                && parentalControlLevel == that.parentalControlLevel
                && movieId.equals(that.movieId);

    }

    @Override
    public int hashCode() {
        int result = parentalControlLevel.hashCode();
        result = 31 * result + movieId.hashCode();
        result = 31 * result + (allowedToWatchMovie ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[MovieId: "+ movieId + " ParentalControl: " + parentalControlLevel + " Message: "+ getMessage()+"]";
    }
}
