package com.cg.sakila.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sakila.entity.Customer;
import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.Rental;
import com.cg.sakila.service.RentalService;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    private final RentalService rentalService;
    
    

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    //working
    @GetMapping("/getAll")
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }
    
    
    

    //working
    @GetMapping("/toptenfilms")
    public ResponseEntity<List<Film>> getTopTenRentedFilms() {
        List<Film> films = rentalService.getTopTenRentedFilms();
        return new ResponseEntity<>(films, HttpStatus.OK);
    }
    
    //working
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Film>> getFilmsRentedByCustomer(@PathVariable("id") Long customerId) {
        List<Film> films = rentalService.getFilmsRentedByCustomer(customerId);
        return new ResponseEntity<>(films, HttpStatus.OK);
    }
    
    //working
    @GetMapping("/toptenfilms/store/{id}")
    public ResponseEntity<List<Film>> getTopTenRentedFilmsByStore(@PathVariable("id") Long storeId) {
        List<Film> films = rentalService.getTopTenRentedFilmsByStore(storeId);
        return new ResponseEntity<>(films, HttpStatus.OK);
    }
    

   //working
    @GetMapping("/due/store/{id}")
    public ResponseEntity<List<Customer>> getCustomersWithPendingReturns(@PathVariable("id") Long storeId) {
        List<Customer> customers = rentalService.getCustomersWithPendingReturns(storeId);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    //working check by me
    @PutMapping("/update/returndate/{id}")
    public ResponseEntity<Rental> updateReturnDate(@PathVariable("id") Integer rentalId) {
        Rental updatedRental = rentalService.updateReturnDate(rentalId);

        if (updatedRental != null) {
            return ResponseEntity.ok(updatedRental);
        }

        return ResponseEntity.notFound().build(); // Rental not found
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> addRental(@RequestBody Rental rental) {
        String response = rentalService.addRental(rental);
        return ResponseEntity.ok(response);
    }
}
   