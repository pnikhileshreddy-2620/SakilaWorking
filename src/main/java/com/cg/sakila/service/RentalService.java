package com.cg.sakila.service;

import java.util.List;

import com.cg.sakila.entity.Customer;
import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.Rental;

public interface RentalService {
	
	List<Rental> getAllRentals();
    List<Film> getFilmsRentedByCustomer(Long customerId);
    List<Film> getTopTenRentedFilms();
    List<Film> getTopTenRentedFilmsByStore(Long storeId);
    List<Customer> getCustomersWithPendingReturns(Long storeId);
    Rental rentFilm(Rental rental);
    Rental updateReturnDate(Integer rentalId);
    String addRental(Rental rental);

//	List<Customer> getDueCustomer(long id);
//  Rental updateReturnDate(Long id, Timestamp returnDate);
    
}
