package ca.sheridancollege.billana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.sheridancollege.billana.domain.Customer;
import ca.sheridancollege.billana.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public Customer findCustomerByEmail(String email) {
    	return customerRepository.findByEmail(email);
    }

//    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    

    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}

