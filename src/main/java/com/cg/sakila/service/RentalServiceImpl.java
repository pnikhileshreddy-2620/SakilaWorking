package com.cg.sakila.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.entity.Customer;
import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.Rental;
import com.cg.sakila.repository.InventoryRepository;
import com.cg.sakila.repository.RentalRepository;

@Service
public class RentalServiceImpl implements RentalService {

	 private final RentalRepository rentalRepository;

	    @Autowired
	    public RentalServiceImpl(RentalRepository rentalRepository) {
	        this.rentalRepository = rentalRepository;
	    }
	    
	    @Autowired
	    public  InventoryRepository inventoryRepository;
	    
	    @Override
	    public List<Rental> getAllRentals() {
	        return rentalRepository.findAll();
	    }
	    
	    
	    @Override
	    public List<Film> getTopTenRentedFilms() {
	        //return rentalRepository.findTop10RentedFilms();
	    	return null;
	    }
	    @Override
	    public List<Film> getFilmsRentedByCustomer(Long customerId) {
	        return rentalRepository.findFilmsRentedByCustomer(customerId);
	    }
	    @Override
	    public List<Film> getTopTenRentedFilmsByStore(Long storeId) {
	        //return rentalRepository.findTop10RentedFilmsByStore(storeId);
	    	return null;
	    }

	    @Override
	    public List<Customer> getCustomersWithPendingReturns(Long storeId) {
	        return rentalRepository.findCustomersWithPendingReturns(storeId);
	    }
	 
	    @Override
	    public Rental rentFilm(Rental rental) {
	       rental.setRentalDate(new Timestamp(System.currentTimeMillis()));
	       
	       rental.setLastUpdate(new Timestamp(System.currentTimeMillis()));
	       
	       // Save the rental using the repository
	       return rentalRepository.save(rental);
	    }
	    
	    
	    @Override
	    public Rental updateReturnDate(Integer rentalId) {
	        Rental rental = rentalRepository.findByRentalId(rentalId);

	        if (rental != null) {
	            rental.setReturnDate(Timestamp.valueOf(LocalDateTime.now()));
	            rental.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
	            return rentalRepository.save(rental);
	        }

	        return null; // Rental not found
	    }
	    
	    
	    @Override
	    public String addRental(Rental rental) {
	        Rental savedRental = rentalRepository.save(rental);
	        return "Record Created Successfully";
	    }
}
