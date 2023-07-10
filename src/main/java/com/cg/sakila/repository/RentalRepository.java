package com.cg.sakila.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.sakila.entity.Customer;
import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental,Long> {

//	@Query("SELECT r.inventory.film, COUNT(r) AS rentalCount " +
//		       "FROM Rental r " +
//		       "GROUP BY r.inventory.film " +
//		       "ORDER BY rentalCount DESC " +
//		       "LIMIT 10")
//	List<Film> findTop10RentedFilms();
	
	@Query("SELECT r.inventory.film " +
		       "FROM Rental r " +
		       "WHERE r.customer.customerId = :customerId")
	List<Film> findFilmsRentedByCustomer(Long customerId);
	
//	@Query("SELECT r.inventory.film, COUNT(r) AS rentalCount " +
//	           "FROM Rental r " +
//	           "WHERE r.inventory.store.storeId = :storeId " +
//	           "GROUP BY r.inventory.film " +
//	           "ORDER BY rentalCount DESC " +
//	           "LIMIT 10")
//	List<Film> findTop10RentedFilmsByStore(Long storeId);
//	
	@Query("SELECT DISTINCT r.customer FROM Rental r WHERE r.returnDate IS NULL AND r.inventory.store.id = :storeId")
	List<Customer> findCustomersWithPendingReturns(@Param("storeId") Long storeId);
	Rental findByRentalId(Integer rentalId);	
}
