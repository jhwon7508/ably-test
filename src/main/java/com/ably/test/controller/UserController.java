package com.ably.test.controller;

import java.util.List;

import com.ably.test.entity.Customer;
import com.ably.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(@PathVariable("keyword") String keyword){
        System.out.println("Get Customers :: ");
        return this.customerService.getCustomers(keyword);
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return this.customerService.getCustomer(id);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer)  {
        System.out.println("Post :: " + customer.toString());
        this.customerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {
        System.out.println("Put :: " + customer.toString());
        this.customerService.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        this.customerService.deleteCustomer(id);
    }
}