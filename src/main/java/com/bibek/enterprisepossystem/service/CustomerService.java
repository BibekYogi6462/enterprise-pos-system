package com.bibek.enterprisepossystem.service;

import com.bibek.enterprisepossystem.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer) throws Exception;
     Customer updateCustomer(Long id, Customer customer) throws Exception;
     void deleteCustomer(Long id) throws Exception;
     Customer getCustomer(Long id) throws Exception;
     List<Customer> getAllCustomers() throws Exception;
     List<Customer> searchCustomers(String keyword) throws Exception;




}
