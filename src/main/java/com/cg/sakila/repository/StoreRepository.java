package com.cg.sakila.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.sakila.entity.Address;
import com.cg.sakila.entity.Customer;
import com.cg.sakila.entity.Staff;
import com.cg.sakila.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store,Byte> {

	List<Store> findByAddressCityCity(String city);
	List<Store> findByAddressCityCountryCountry(String countryName);
	Store findByAddressPhone(String phone);
	Store findByStoreId(byte id);
	@Query("SELECT c FROM Customer c JOIN Store s ON c.storeId = s.storeId WHERE s.storeId = :storeId")
    List<Customer> findCustomersByStoreId(@Param("storeId") Byte storeId);
	
}
