package williamsadeho.parentalcontrol.controller;

import williamsadeho.parentalcontrol.api.ParentalControlService;
import williamsadeho.parentalcontrol.model.ParentalControlInformation;
import williamsadeho.parentalcontrol.model.ParentalControlLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/parentalControls")
@Api( value = "/api/parentalControls" +
        "", description = "Information pertaining to movie parental information" )
public class ParentalControlController {

    @Autowired
    private ParentalControlService parentalControlService;

    //-------------------Retrieve Parental Control Info--------------------------------------------------------
    @ApiOperation( value = "Find parental information movie Id with preferred parental control level or rating", notes = "Find parental information movie Id with preferred parental control level or rating", response = ParentalControlInformation.class)
    @ApiResponses( {
        @ApiResponse( code = 404, message = "Parental Information for movie doesn't exists" )
    })
    @GetMapping(value = "/rating/{preferredRating}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ParentalControlInformation getParentalControlInfo(@PathVariable String preferredRating, @RequestParam(name = "movieId", required = true) String movieId) {
        final ParentalControlLevel rating = ParentalControlLevel.getByValue(preferredRating);
        return new ParentalControlInformation(rating, movieId,parentalControlService.isAbleToWatchMovie(rating, movieId));
    }
}
