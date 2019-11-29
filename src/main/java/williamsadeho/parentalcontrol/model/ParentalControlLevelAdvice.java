package williamsadeho.parentalcontrol.model;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

/**
 * Binds customer parental control preference to the Parental Control Level.
 */
@ControllerAdvice
public class ParentalControlLevelAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ParentalControlLevel.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                    setValue(ParentalControlLevel.getByValue(text));
            }
        });
    }
}
