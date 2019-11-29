package williamsadeho.parentalcontrol.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import williamsadeho.parentalcontrol.exception.TechnicalFailureException;

import java.util.stream.Stream;

/**
 * Enums of all parental control levels.
 */
@ApiModel
public enum ParentalControlLevel {

    UNDERAGE("U"),
    PARENTAL_GUIDANCE("PG"),
    TWELVE("12"),
    FIFTEEN("15"),
    EIGHTEEN("18");

    @ApiModelProperty(dataType = "string", notes = "The parental control level or rating")
    private final String rating;

    ParentalControlLevel(String rating) {
        this.rating = rating;
    }

    public static ParentalControlLevel getByValue(String rating) {
        return Stream.of(ParentalControlLevel.values())
                .filter(mainLevel -> mainLevel.getRating().equals(rating))
                .findAny()
                .orElseThrow(() -> new TechnicalFailureException("Could not find parental control level for the given value: "+ rating));
    }

    private String getRating() {
        return rating;
    }
}
