package micro.mike.commons.http.exceptions;

import java.time.ZonedDateTime;

public class HttpException {
    private final String message;
    private final int code;
    private final ZonedDateTime timestamp;

    public HttpException(String message, int code, ZonedDateTime timestamp) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[message:" + message + "][CODE:" + code + "][TIMESTAMP:" + timestamp + "]";
    }
}
