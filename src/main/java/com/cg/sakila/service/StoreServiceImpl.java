package com.cg.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.entity.Address;
import com.cg.sakila.entity.Customer;
import com.cg.sakila.entity.Staff;
import com.cg.sakila.entity.Store;
import com.cg.sakila.repository.AddressRepository;
import com.cg.sakila.repository.CustomerRepository;
import com.cg.sakila.repository.StaffRepository;
import com.cg.sakila.repository.StoreRepository;

@Service
public class StoreServiceImpl implements StoreService {

	/*@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void addStore(Store store) {
		storeRepository.save(store);
	}

	@Override
	public void updateStore(Store store) {
		storeRepository.save(store);
	}

	@Override
	public Store getStoreById(byte id) {
		Store store = storeRepository.findByStoreId(id);
		return store;
	}

	// working fine
	@Override
	public List<Store> getStoresByCity(String city) {
		return storeRepository.findByAddressCityCity(city);
	}

	// working fine
	@Override
	public List<Store> getStoresByCountry(String country) {
		return storeRepository.findByAddressCityCountryCountry(country);
	}

	@Override
	public Store getStoreByPhoneNumber(String phone) {
		return storeRepository.findByAddressPhone(phone);
	}
//
	@Override
	public void assignAddressToStore(byte storeId, Address address) {
	    Store store = storeRepository.findByStoreId(storeId);
	    store.setAddress(address);
	    addressRepository.save(address);
	}


	@Override
	public void updateStorePhoneNumber(byte storeId, String phone) {
		Optional<Store> storeOptional = storeRepository.findById(storeId);
		if (storeOptional.isPresent()) {
			Store store = storeOptional.get();
			store.getAddress().setPhone(phone);
			storeRepository.save(store);
		}
	}

	@Override
	public void assignManagerToStore(byte managerStaffId, Staff manager) {
		Store store = findStoreByManagerStaffId(managerStaffId);

		manager.setStore(store);
	}

	@Override
	public List<Staff> getAllStaffByStoreId(byte storeId) {

		return staffRepository.getByStoreStoreId(storeId);
	}

	@Override
	public List<Staff> getManagerDetailsByStoreId(byte storeId) {
		return staffRepository.findByStoreId(storeId);
	}

	@Override
	public List<Staff> getAllManagers() {

		return staffRepository.allManagers();
	}

	@Override
	public List<Store> fetchAllStore() {
		List<Store> stores = storeRepository.findAll();
		return stores;
	}

	@Override
	public Store findStoreByManagerStaffId(byte managerStaffId) {
		Staff staff = staffRepository.findByStaffId(managerStaffId);

		byte id = staff.getStaffId();

		Store store = storeRepository.findByStoreId(id);

		return store;

	}

	@PersistenceContext
	private EntityManager entityManager;

	public Map<Object, Object> findAllOfMyStore() {
		String jpql = "SELECT s.storeId, s.address.addressId, s.lastUpdate, s.staff.staffId FROM Store s";
		TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);

		List<Object[]> objlist = query.getResultList();
		Object[] arr = objlist.get(0);

		Object[] objarr = { "store_id", "manager_staff_id", "last_update", "address_id" };

		int count = 0;
		HashMap<Object, Object> map = new HashMap<>();
		for (Object obj : objarr) {
			map.put(obj, arr[count++]);
		}
		return map;
	}

	@Override
	public List<Address> fetchAllAddress() {
		List<Address> addresses = addressRepository.findAll();

		return addresses;

	}

	@Override
	public List<Staff> findAllStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> findAllStore() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public List<Customer> getCustomersByStoreId(Byte storeId) {
        return storeRepository.findCustomersByStoreId(storeId);
    }*/
	
	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void addStore(Store store) {
		storeRepository.save(store);
	}

	@Override
	public void updateStore(Store store) {
		storeRepository.save(store);
	}

	@Override
	public Store getStoreById(byte id) {
		Store store = storeRepository.findByStoreId(id);
		return store;
	}

	// working fine
	@Override
	public List<Store> getStoresByCity(String city) {
		return storeRepository.findByAddressCityCity(city);
	}

	// working fine
	@Override
	public List<Store> getStoresByCountry(String country) {
		return storeRepository.findByAddressCityCountryCountry(country);
	}

	@Override
	public Store getStoreByPhoneNumber(String phone) {
		return storeRepository.findByAddressPhone(phone);
	}
//
	@Override
	public void assignAddressToStore(byte storeId, Address address) {
	    Store store = storeRepository.findByStoreId(storeId);
	    store.setAddress(address);
	    addressRepository.save(address);
	}


	@Override
	public void updateStorePhoneNumber(byte storeId, String phone) {
		Optional<Store> storeOptional = storeRepository.findById(storeId);
		if (storeOptional.isPresent()) {
			Store store = storeOptional.get();
			store.getAddress().setPhone(phone);
			storeRepository.save(store);
		}
	}

	// working fine
	@Override
	public void assignManagerToStore(byte managerStaffId, Staff manager) {
		Store store = findStoreByManagerStaffId(managerStaffId);

		manager.setStore(store);
	}

	@Override
	public List<Staff> getAllStaffByStoreId(byte storeId) {

		return staffRepository.getByStoreStoreId(storeId);
	}

//	@Override
//	public List<Customer> getAllCustomersByStoreId(byte storeId) {
//
//		return customerRepository.findCustomersByStoreId(storeId);
//
//	}

	@Override
	public List<Staff> getManagerDetailsByStoreId(byte storeId) {
		return staffRepository.findByStoreId(storeId);
	}

	@Override
	public List<Staff> getAllManagers() {

		return staffRepository.allManagers();
	}

	@Override
	public List<Store> fetchAllStore() {
		List<Store> stores = storeRepository.findAll();
		return stores;
	}

	@Override
	public Store findStoreByManagerStaffId(byte managerStaffId) {
		Staff staff = staffRepository.findByStaffId(managerStaffId);

		byte id = staff.getStaffId();

		Store store = storeRepository.findByStoreId(id);

		return store;

	}

	@PersistenceContext
	private EntityManager entityManager;

	public Map<Object, Object> findAllOfMyStore() {
		String jpql = "SELECT s.storeId, s.address.addressId, s.lastUpdate, s.staff.staffId FROM Store s";
		TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);

		List<Object[]> objlist = query.getResultList();
		Object[] arr = objlist.get(0);

		Object[] objarr = { "store_id", "manager_staff_id", "last_update", "address_id" };

		int count = 0;
		HashMap<Object, Object> map = new HashMap<>();
		for (Object obj : objarr) {
			map.put(obj, arr[count++]);
		}
		return map;
	}

	@Override
	public List<Address> fetchAllAddress() {
		List<Address> addresses = addressRepository.findAll();

		return addresses;

	}

	@Override
	public List<Staff> findAllStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> findAllStore() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public List<Customer> getCustomersByStoreId(Byte storeId) {
        return storeRepository.findCustomersByStoreId(storeId);
    }


}
