package com.cg.sakila.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sakila.Exception.ActorDataException;
import com.cg.sakila.entity.Address;
import com.cg.sakila.entity.Customer;
import com.cg.sakila.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerservice;

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerservice.findAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/lastname/{ln}")
    public ResponseEntity<List<Customer>> searchCustomersByLastName(@PathVariable("ln") String lastName) {
        List<Customer> customers = customerservice.findCustomersByLastName(lastName);
        if (customers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/firstname/{fn}")
    public ResponseEntity<List<Customer>> searchCustomersByFirstName(@PathVariable("fn") String firstName) {
        List<Customer> customers = customerservice.findCustomersByFirstName(firstName);
        if (customers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> searchCustomerByEmail(@PathVariable("email") String email) {
        Customer customer = customerservice.findCustomerByEmail(email);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/city/{city}")
    public List<Customer> getCustomersByCity(@PathVariable("city") String city) {
        return customerservice.getCustomersByCity(city);
    }

    @GetMapping("/country/{country}")
    public List<Customer> getCustomersByCountry(@PathVariable("country") String country) {
        return customerservice.getCustomersByCountry(country);
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<Customer> assignAddressToCustomer(@PathVariable("id") short customerId,
            @RequestBody Address address) {
        Customer customer = customerservice.assignAddressToCustomer(customerId, address);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Customer>> searchActiveCustomers() {
        List<Customer> customers = customerservice.findActiveCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<Customer>> searchInactiveCustomers() {
        List<Customer> customers = customerservice.findInactiveCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Customer> searchCustomerByPhone(@PathVariable("phone") String phone) {
        Customer customer = customerservice.findCustomerByPhone(phone);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/update/fn/{id}")
    public ResponseEntity<Customer> updateCustomerFirstName(@PathVariable("id") Short id,
            @RequestBody Map<String, String> requestBody) {
        String firstName = requestBody.get("firstName");
        if (firstName == null || firstName.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Customer updatedCustomer = customerservice.updateCustomerFirstName(id, firstName);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/update/ln/{id}")
    public ResponseEntity<Customer> updateCustomerLastName(@PathVariable("id") Short id,
            @RequestBody Map<String, String> requestBody) {
        String lastName = requestBody.get("lastName");
        if (lastName == null || lastName.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Customer updatedCustomer = customerservice.updateCustomerLastName(id, lastName);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/update/email/{id}")
    public ResponseEntity<Customer> updateCustomerEmail(@PathVariable("id") Short id,
            @RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Customer updatedCustomer = customerservice.updateCustomerEmail(id, email);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/update/store/{id}")
    public ResponseEntity<Customer> assignStoreToCustomer(@PathVariable("id") Short id,
            @RequestParam("storeId") Byte storeId) {
        Optional<Customer> updatedCustomer = customerservice.assignStoreToCustomer(id, storeId);
        if (updatedCustomer.isPresent()) {
            return ResponseEntity.ok(updatedCustomer.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/phone/{id}")
    public ResponseEntity<Customer> updateCustomerPhone(@PathVariable("id") Short customerId,
            @RequestParam("phone") String phone) {
        Customer updatedCustomer = customerservice.updateCustomerPhone(customerId, phone);
        if (updatedCustomer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCustomer);
    }

    @PostMapping("/post")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        try {
            Customer addedCustomer = customerservice.addCustomer(customer);
            return ResponseEntity.ok("Record Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Create Record");
        }
    }

    // Exception handling for generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
