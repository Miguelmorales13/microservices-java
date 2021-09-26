package micro.mike.products.interceptors;

import lombok.extern.log4j.Log4j2;
import micro.mike.commons.http.exceptions.HttpException;
import micro.mike.commons.http.exceptions.HttpRequestException;
import org.slf4j.MDC;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
@Log4j2
public class HttpRequestExceptionHandler {

    @ExceptionHandler(value = {HttpRequestException.class})
    public ResponseEntity<HttpException> handlerError(HttpServletRequest request, HttpRequestException e) {
        HttpException z = new HttpException(e.getMessage(), e.getStatus().value(), ZonedDateTime.now(ZoneId.of("Z")));
        printError(request, z);
        return new ResponseEntity<>(z, e.getStatus());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<HttpException> handlerErrorValidation(HttpServletRequest request, MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .reduce("", (String field, String error) -> field.concat(error).concat(", ")).toString();
        HttpException z = new HttpException(message, HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now(ZoneId.of("Z")));
        printError(request, z);
        return new ResponseEntity<>(z, HttpStatus.BAD_REQUEST);
    }

    private void printError(HttpServletRequest request, HttpException e) {
        MDC.put("path", request.getServletPath());
        String logString = "[MESSAGE|".concat(e.getMessage()).concat("][");
        logString = logString.concat("[CODE|").concat(String.valueOf(e.getCode())).concat("][");
        logString = logString.concat("[TIMESTAMP|").concat(String.valueOf(e.getTimestamp())).concat("]");
        log.error(logString);
    }
}
