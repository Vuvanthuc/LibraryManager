package vn.cpa.api.exception.authentication;

import org.springframework.http.HttpStatus;
import vn.cpa.api.exception.HttpException;

public class InvalidTokenHttpException extends HttpException {
    private static final long serialVersionUID = 773684525186809237L;

    public InvalidTokenHttpException() {
        super(HttpStatus.FORBIDDEN);
    }
}
