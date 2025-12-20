package service;

import model.Customer;
import java.util.HashSet;
import java.util.Set;

public class CustomerService {

    private static final CustomerService INSTANCE =
            new CustomerService();

    private final Set<Customer> customers = new HashSet<>();

    private CustomerService() {}

    public static CustomerService getInstance() {
        return INSTANCE;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomer(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public Set<Customer> getAllCustomers() {
        return customers;
    }
}
