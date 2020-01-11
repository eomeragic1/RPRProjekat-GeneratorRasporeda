package Model;

import java.security.InvalidParameterException;

public class UserNotFoundException extends InvalidParameterException {
    public UserNotFoundException(String s) {
        super(s);
    }
}
