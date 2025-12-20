import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class CustomerService {

    private static final CustomerService instance = new CustomerService();

    private final Set<Customer> customers = new HashSet<>();
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^.+@.+\\.com$");

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        return instance;
    }

    public void addCustomer(String firstName, String lastName, String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        customers.add(new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return customers;
    }
}
