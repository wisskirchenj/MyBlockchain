package topics.IO.serialization;

import java.io.*;
import java.util.stream.IntStream;

/**
 * Password processing
 * The UserProfile class is supposed to represent a user in a social network. It has the following fields:
 * class UserProfile implements Serializable {
 *     private String login;
 *     private String email;
 *     private transient String password;
 *     // a constructor and getters
 * }
 * You need to implement custom serialization and deserialization for objects of this class (see the methods readObject and writeObject).
 * The serialization should save the fields login and email as is and encrypt the password field. The encryption algorithm
 * is very simple and we use it only for education purposes: each char of the string is shifted by 1 position according
 * to the unicode table (i.e. 123 -> 234, abc -> bcd). The deserialization should perform a reverse process to restore the original password.
 */
class UserProfile implements Serializable {
    @Serial
    private static final long serialVersionUID = 26292552485L;

    private String login;
    private String email;
    private transient String password;

    public UserProfile(String login, String email, String password) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeUTF(encrypt(password));
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        password = decrypt(ois.readUTF());
    }

    private String encrypt(String password) {
        byte[] passwordBytes = password.getBytes();
        IntStream.range(0, passwordBytes.length).forEach(i -> passwordBytes[i]++);
        return new String(passwordBytes);
    }


    private String decrypt(String password) {
        byte[] passwordBytes = password.getBytes();
        IntStream.range(0, passwordBytes.length).forEach(i -> passwordBytes[i]--);
        return new String(passwordBytes);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
