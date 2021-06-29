package com.springhibernate.dao;

import com.springhibernate.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomer(int customerId);

    void deleteCustomer(int id);
}
