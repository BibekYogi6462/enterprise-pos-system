package com.bibek.enterprisepossystem.service.impl;


import com.bibek.enterprisepossystem.model.Customer;
import com.bibek.enterprisepossystem.repository.CustomerRepository;
import com.bibek.enterprisepossystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl  implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) throws Exception {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws Exception {
        Customer customerToUpdate = customerRepository.findById(id)
                .orElseThrow(() -> new Exception(
                        "Customer not found with id: " + id));
            customer.setFullName(customer.getFullName());
            customer.setEmail(customer.getEmail());
            customer.setPhone(customer.getPhone());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer customerToUpdate = customerRepository.findById(id)
                .orElseThrow(() -> new Exception(
                        "Customer not found with id: " + id));
        customerRepository.delete(customerToUpdate);
    }

    @Override
    public Customer getCustomer(Long id) throws Exception {
        return customerRepository.findById(id)
                .orElseThrow(() -> new Exception(
                        "Customer not found with id: " + id));
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) throws Exception {
        return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }
}
