package org.example.bo.asset;


import javafx.collections.ObservableList;
import org.example.model.Customer;

public interface CustomerBo {

    String generateCustomerId();

    void insertCustomer(Customer customer);

    ObservableList<Customer> getAllCustomer();

    boolean isValidEmail(String email);

    Customer getUserById(String id);

    boolean updateCustomer(Customer customer);

    boolean deleteCustomerById(String id);

    ObservableList<String> getAllCustomerIds();
}