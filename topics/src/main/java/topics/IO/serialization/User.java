package topics.IO.serialization;

import java.io.Serializable;

/**
 * Serializable user
 * Here is a class User with two fields name and password.
 * Make this class serializable excluding the password field from the serialization.
 * Please, add a field for version to follow the best practices.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 10L;
    String name;
    transient String password;
}