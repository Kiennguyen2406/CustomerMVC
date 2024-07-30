package com.example.demo5.service;

import com.example.demo5.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService {
    private static List<Customer> customerList;

    static {
        customerList = new ArrayList<>();
        customerList.add(new Customer(1, "Kien", 25, "nguyenduckien@gmail.com"));
        customerList.add(new Customer(2, "Anh", 30, "anhnguyen@gmail.com"));
        customerList.add(new Customer(3, "Mai", 28, "maithanh@gmail.com"));
        customerList.add(new Customer(4, "Long", 35, "longtran@gmail.com"));
        customerList.add(new Customer(5, "Thao", 22, "thaomai@gmail.com"));
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerList;
    }

    @Override
    public Customer getCustomerByIndex(int id) {
        int index = findById(id);
        if (index != -1) {
            return customerList.get(index);
        }
        return null;
    }


    @Override
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    @Override
    public boolean updateCustomer(int id, String name, int age, String email) {
        int index = findById(id);
        if (index != -1) {
            Customer customer = customerList.get(index);
            customer.setName(name);
            customer.setAge(age);
            customer.setEmail(email);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int id) {
        int index = findById(id);
        if (index != -1) {
            customerList.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public int findById(int id) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
