package com.example.demo5.controller;


import com.example.demo5.model.Customer;
import com.example.demo5.service.CustomerService;
import com.example.demo5.service.ICustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/c")
public class CustomerServlet extends HttpServlet {
    private final ICustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = customerService.getCustomerList();
        req.setAttribute("customers", customers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createCustomer(req, resp);
                break;
            case "edit":
                editCustomer(req, resp);
                break;
            case "delete":
                deleteCustomer(req, resp);
                break;
            default:
                doGet(req, resp);  // Display all customers if action is invalid
                break;
        }
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            String email = req.getParameter("email");
            Customer customer = new Customer(id, name, age, email);
            customerService.addCustomer(customer);
            req.setAttribute("message", "New customer was created.");
        } catch (NumberFormatException e) {
            req.setAttribute("message", "Invalid input format.");
        }
        // Forward to index.jsp to display updated list and message
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            String email = req.getParameter("email");

            boolean isUpdated = customerService.updateCustomer(id, name, age, email);
            req.setAttribute("message", isUpdated ? "Customer was updated successfully." : "Customer not found.");
        } catch (NumberFormatException e) {
            req.setAttribute("message", "Invalid input format.");
        }
        // Forward to index.jsp to display updated list and message
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            boolean isDeleted = customerService.deleteCustomer(id);
            req.setAttribute("message", isDeleted ? "Customer was deleted successfully." : "Customer not found.");
        } catch (NumberFormatException e) {
            req.setAttribute("message", "Invalid customer ID format.");
        }
        // Forward to index.jsp to display updated list and message
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
