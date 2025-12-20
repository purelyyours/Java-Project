package model;

import java.util.regex.Pattern;

public class Customer {

    private final String firstName;
    private final String lastName;
    private final String email;

    private static final Pattern EMAIL =
            Pattern.compile("^.+@.+\\.com$");

    public Customer(String firstName, String lastName, String email) {
        if (!EMAIL.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() { return email; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + ")";
    }
}
