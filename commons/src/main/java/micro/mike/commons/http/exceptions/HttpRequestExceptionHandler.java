package micro.mike.commons.http.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class HttpRequestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestExceptionHandler.class);

    @ExceptionHandler(value = {HttpRequestException.class})
    public ResponseEntity<HttpException> handlerError(HttpServletRequest request, HttpRequestException e) {
        HttpException z = new HttpException(e.getMessage(), e.getStatus().value(), ZonedDateTime.now(ZoneId.of("Z")));
        printError(request, z);
        return new ResponseEntity<HttpException>(z, e.getStatus());
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
        long time = System.currentTimeMillis() - ((long) request.getAttribute("time"));
        String uuid = (String) request.getAttribute("uuid");
        String log = "[ERROR|".concat(uuid).concat("][").concat(request.getMethod()).concat("|").concat(request.getServletPath()).concat("]");
        log = log.concat("[MESSAGE|").concat(e.getMessage()).concat("][");
        log = log.concat("[CODE|").concat(String.valueOf(e.getCode())).concat("][");
        log = log.concat("[TIMESTAMP|").concat(String.valueOf(e.getTimestamp())).concat("][");
        log = log.concat("[TIME|").concat(String.valueOf(time)).concat("ms]");
        logger.info(log);
    }
}
