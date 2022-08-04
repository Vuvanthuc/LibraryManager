package vn.cpa.api.exception.authentication;

import org.springframework.http.HttpStatus;
import vn.cpa.api.exception.HttpException;

public class PasswordsDontMatchException extends HttpException {

    private static final long serialVersionUID = -7852550573176915476L;

    public PasswordsDontMatchException() {
        super("Passwords don't match", HttpStatus.BAD_REQUEST);
    }
}
