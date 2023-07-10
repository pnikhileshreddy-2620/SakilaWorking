package com.cg.sakila.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.entity.Address;
import com.cg.sakila.entity.Staff;
import com.cg.sakila.entity.Store;
import com.cg.sakila.repository.AddressRepository;
import com.cg.sakila.repository.StaffRepository;
import com.cg.sakila.repository.StoreRepository;

@Service
public class StaffServiceImpl implements StaffService {

	

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Staff> searchStaffByLastName(String lastName) {
		return staffRepository.findByLastName(lastName);
	}

	@Override
	public List<Staff> searchStaffByFirstName(String firstName) {
		return staffRepository.findByFirstName(firstName);
	}

	@Override
	public Staff searchStaffByEmail(String email) {
		return staffRepository.findByEmail(email);
	}

	@Override
	public Staff assignAddressToStaff(byte id, Address address) {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.setAddress(address);
			return staffRepository.save(staff);
		}
		return null;
	}

	@Override
	public Staff searchStaffByAddress(Address address) {
		return staffRepository.findByAddress(address);
	}

	@Override
	public List<Staff> searchStaffByCity(String city) {

		return staffRepository.findByAddressCityCity(city);
	}

	@Override
	public List<Staff> searchStaffByCountry(String country) {
		return staffRepository.findByAddressCityCountryCountry(country);
	}

	@Override
	public Staff searchStaffByPhone(String phone) {
		return staffRepository.findByAddress_Phone(phone);
	}

	@Override
	public Staff updateStaffFirstName(byte id, String firstName) {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.setFirstName(firstName);
			return staffRepository.save(staff);
		}
		return null;
	}

	@Override
	public Staff updateStaffLastName(byte id, String lastName) {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.setLastName(lastName);
			return staffRepository.save(staff);
		}
		return null;
	}

	@Override
	public Staff updateStaffEmail(byte id, String email) {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.setEmail(email);
			return staffRepository.save(staff);
		}
		return null;
	}

	@Override
	public Staff assignStoreToStaff(byte id, Store store) {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.setStore(store);
			return staffRepository.save(staff);

		}
		return null;
	}

	@Override
	public Staff updateStaffPhone(byte id, String phone) {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.getAddress().setPhone(phone);
			return staffRepository.save(staff);
		}
		return null;
	}

	@Override
	public void deleteStaff(byte id) {
		staffRepository.deleteById(id);
	}

//	@Override
//	public List<Staff> fetchAll() {
//		List<Staff> staffs = staffRepository.findAll();
//		staffs.forEach(staff -> staff.getAddress().getAddressId());
//		staffs.forEach(staff -> staff.getAddress().getCityId());
//		// Fetch the address association eagerly
//		return staffs;
//	}
	
	@Override
    public Staff getStaffById(byte staffId) {
        Optional<Staff> optionalStaff = staffRepository.findById(staffId);
        return optionalStaff.orElse(null);
    }

    @Override
    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }

	@Override
	public Staff assignStoreToStaff(byte id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }
	
	//-----------------------------------------
	@Override
	public Staff createStaff(Staff staff) {
		return staffRepository.save(staff);
	}

	
	@Override
	public List<Staff> getAllStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Staff> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
