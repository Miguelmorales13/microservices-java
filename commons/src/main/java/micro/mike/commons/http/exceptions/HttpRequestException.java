package micro.mike.commons.http.exceptions;

import org.springframework.http.HttpStatus;

public class HttpRequestException extends RuntimeException {
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public HttpRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpRequestException(String message) {
        super(message);
    }

    public HttpRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
