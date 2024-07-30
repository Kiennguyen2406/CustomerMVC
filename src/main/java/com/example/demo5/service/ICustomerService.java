package com.example.demo5.service;

import com.example.demo5.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getCustomerList();
    Customer getCustomerByIndex(int id);
    void addCustomer(Customer customer);
    boolean updateCustomer(int id, String name, int age, String email);
    boolean deleteCustomer(int id);
    int findById(int id);
}
