package com.cg.sakila.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.entity.Address;
import com.cg.sakila.entity.Customer;
import com.cg.sakila.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repo;
	private CustomerService custservice;
	
	@Override
	public List<Customer> findAllCustomers(){
		return repo.findAll();
	}
	 @Override
	    public List<Customer> findCustomersByLastName(String lastName) {
	        return repo.findByLastName(lastName);
	    }
	 @Override
	    public List<Customer> findCustomersByFirstName(String firstName) {
	        return repo.findByFirstName(firstName);
	    }
//	 @Override
//	    public Customer findCustomerByEmail(String email) {
//	        return repo.findByEmail(email);
//	    }
	 @Override
	    public List<Customer> getCustomersByCity(String city) {
	        return repo.findByAddress_City_City(city);
	    }
	 @Override
	    public List<Customer> getCustomersByCountry(String country) {
	        return repo.findByAddress_City_Country_Country(country);
	    }

	 @Override
	    public List<Customer> findActiveCustomers() {
	        return repo.findByActive(1);
	    }
	 
	 @Override
	    public Customer assignAddressToCustomer(short customerId, Address address) {
	        Customer customer = repo.findById(customerId).orElse(null);
	        if (customer != null) {
	            customer.setAddress(address);
	            return repo.save(customer);
	        }
	        return null;
	    }
//city
	 @Override
	    public List<Customer> findInactiveCustomers() {
	        return repo.findByActive(0);
	    }
	 
	 @Override
	    public Customer findCustomerByPhone(String phone) {
	        return repo.findByAddressPhone(phone);
	    }
	 @Override
	    public Customer updateCustomerFirstName(Short id, String firstName) {
	        Optional<Customer> optionalCustomer = repo.findById(id);
	        if (optionalCustomer.isPresent()) {
	            Customer customer = optionalCustomer.get();
	            customer.setFirstName(firstName);
	            return repo.save(customer);
	        }
	        return null; 
	    }
	 @Override
	    public Customer updateCustomerLastName(Short id, String lastName) {
	        Optional<Customer> optionalCustomer = repo.findById(id);
	        if (optionalCustomer.isPresent()) {
	            Customer customer = optionalCustomer.get();
	            customer.setLastName(lastName);
	            return repo.save(customer);
	        }
	        return null; 
	    }
	 
	 @Override
	    public Customer updateCustomerEmail(Short id, String email) {
	        Optional<Customer> optionalCustomer = repo.findById(id);
	        if (optionalCustomer.isPresent()) {
	            Customer customer = optionalCustomer.get();
	            customer.setEmail(email);
	            return repo.save(customer);
	        }
	        return null; 
	    }

	 
	 @Override
	    public Optional<Customer> assignStoreToCustomer(Short id, Byte storeId) {
	        Optional<Customer> optionalCustomer = repo.findById(id);
	        if (optionalCustomer.isPresent()) {
	            Customer customer = optionalCustomer.get();
	            customer.setStoreId(storeId);
	            Customer updatedCustomer = repo.save(customer);
	            return Optional.of(updatedCustomer);
	        }
	        return Optional.empty();
	    }
	 @Override
	    public Customer updateCustomerPhone(Short customerId, String phone) {
	        Customer customer = repo.findById(customerId).orElse(null);
	        if (customer != null) {
	            Address address = customer.getAddress();
	            if (address != null) {
	                address.setPhone(phone);
	                return repo.save(customer);
	            }
	        }
	        return null;
	    }
	 
	 @Override
	    public Customer addCustomer(Customer customer) {
	        return repo.save(customer);
	    }
	 
	    @Override
	    public Customer saveCustomer(Customer customer) {
	        return repo.save(customer);
	    }
	    @Override
	    public Customer findCustomerByEmail(String email) {
	        return repo.findByEmail(email);
	    }

}
