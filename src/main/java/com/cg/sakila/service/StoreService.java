package com.cg.sakila.service;

import java.util.List;
import java.util.Map;

import com.cg.sakila.entity.Address;
import com.cg.sakila.entity.Customer;
import com.cg.sakila.entity.Staff;
import com.cg.sakila.entity.Store;

public interface StoreService {

	void addStore(Store store);
	void updateStore(Store store);
	Store getStoreById(byte id);
	List<Store> getStoresByCity(String city);
	List<Store> getStoresByCountry(String country);
	Store getStoreByPhoneNumber(String phone);
	void assignAddressToStore(byte storeId, Address address);
	void updateStorePhoneNumber(byte storeId, String phone);
	void assignManagerToStore(byte storeId, Staff manager);
	List<Staff> getAllStaffByStoreId(byte storeId);
//	List<Customer> getAllCustomersByStoreId(byte storeId);
	List<Staff> getManagerDetailsByStoreId(byte storeId);
	List<Staff> getAllManagers();
	List<Store> fetchAllStore();
	Store findStoreByManagerStaffId(byte managerStaffId);
	List<Staff> findAllStaff();
	List<Address> fetchAllAddress();
	List<Store> findAllStore();
	Map<Object, Object> findAllOfMyStore();
	List<Customer> getCustomersByStoreId(Byte storeId);
}
