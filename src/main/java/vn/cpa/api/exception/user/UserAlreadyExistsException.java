package vn.cpa.api.exception.user;

import vn.cpa.api.exception.ApiException;

public class UserAlreadyExistsException extends ApiException {
    private static final long serialVersionUID = -2737319632275059973L;

    public UserAlreadyExistsException(String email) {
        super("Account with email: " + email + " already registered");
    }
}
