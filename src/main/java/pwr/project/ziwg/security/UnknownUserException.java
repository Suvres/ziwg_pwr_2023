package pwr.project.ziwg.security;

import org.springframework.security.core.AuthenticationException;

public class UnknownUserException extends AuthenticationException {
    public UnknownUserException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UnknownUserException(String msg) {
        super(msg);
    }
}
