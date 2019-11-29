package williamsadeho.parentalcontrol.exception;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class to hold exception and error messages which can be converted to JSON.
 */
public class ParentalControlError {

    @ApiModelProperty(notes = "The HTTP Status Response code")
    private HttpStatus status;

    @ApiModelProperty(notes = "The error message")
    private String message;

    @ApiModelProperty(notes = "The list of errors")
    private List<String> errors;

    public ParentalControlError() {
        super();
    }

    ParentalControlError(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    ParentalControlError(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Collections.singletonList(error);
    }

    HttpStatus getStatus() {
        return status;
    }

    public void setStatus(final HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    public void setError(final String error) {
        errors = Arrays.asList(error);
    }

}