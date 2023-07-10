package com.cg.sakila.service;

import java.util.List;

import com.cg.sakila.entity.Address;
import com.cg.sakila.entity.Staff;
import com.cg.sakila.entity.Store;

public interface StaffService {

	Staff createStaff(Staff staff);

	List<Staff> searchStaffByLastName(String lastName);

	List<Staff> searchStaffByFirstName(String firstName);

	Staff searchStaffByEmail(String email);

	Staff assignAddressToStaff(byte id, Address address);

	List<Staff> searchStaffByCity(String city);

	List<Staff> searchStaffByCountry(String country);

	Staff searchStaffByPhone(String phone);

	Staff updateStaffFirstName(byte id, String firstName);

	Staff updateStaffLastName(byte id, String lastName);

	Staff updateStaffEmail(byte id, String email);

	Staff assignStoreToStaff(byte id, Store store);

	Staff updateStaffPhone(byte id, String phone);

	void deleteStaff(byte id);

	Staff searchStaffByAddress(Address address);

	List<Staff> fetchAll();

	Staff assignStoreToStaff(byte id);

	Staff getStaffById(byte staffId);

	Staff updateStaff(Staff staff);
	
	Staff addStaff(Staff staff);

	List<Staff> getAllStaff();
}
