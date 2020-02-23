/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.2.3).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.fdobrotv.elvl.api;

import com.fdobrotv.elvl.model.Error;
import com.fdobrotv.elvl.model.Quote;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Api(value = "quotes", description = "the quotes API")
public interface QuotesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /quotes : Create a quote
     *
     * @param quote Body of the quote (required)
     * @return Created quote (status code 201)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Create a quote", nickname = "createQuote", notes = "", response = Quote.class, tags={ "Quote", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created quote", response = Quote.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
    @RequestMapping(value = "/quotes",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Quote> createQuote(@ApiParam(value = "Body of the quote" ,required=true )  @Valid @RequestBody Quote quote) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"ask\" : 101.9, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"bid\" : 100.2, \"isin\" : \"RU000A0JX0J2\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /quotes : List of all quotes (котировки)
     *
     * @return Array of quotes (status code 200)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "List of all quotes (котировки)", nickname = "getAllQuotes", notes = "", response = Quote.class, responseContainer = "List", tags={ "Quote", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Array of quotes", response = Quote.class, responseContainer = "List"),
        @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
    @RequestMapping(value = "/quotes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<Quote>> getAllQuotes() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"ask\" : 101.9, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"bid\" : 100.2, \"isin\" : \"RU000A0JX0J2\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
