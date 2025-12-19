import java.util.*;
import java.util.regex.Pattern;

public class CustomerService {

    private final Set<Customer> customers = new HashSet<>();
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^.+@.+\\.com$");

    public void addCustomer(String first, String last, String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        customers.add(new Customer(first, last, email));
    }

    public Customer getCustomer(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equals(email)) return c;
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return customers;
    }
}
