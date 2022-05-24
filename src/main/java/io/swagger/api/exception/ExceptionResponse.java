package io.swagger.api.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;

public class ExceptionResponse {
    @JsonProperty
    public String codeError;
    @JsonProperty
    public String occurredDate;
    @JsonProperty
    public String message;
    public ExceptionResponse(String codeError, ZonedDateTime occurredDate, String message) {
        this.codeError = codeError;
        this.occurredDate = occurredDate.toString();
        this.message = message;
    }
}
