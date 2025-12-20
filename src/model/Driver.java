package model;

public class Driver {
    public static void main(String[] args) {
        Customer customer =
                new Customer("pujitha", "ch", "abcd@gmail.com");
        System.out.println(customer.toString());
    }
}
