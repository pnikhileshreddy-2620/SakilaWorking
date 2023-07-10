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

import com.cg.sakila.entity.Address;
import com.cg.sakila.entity.Staff;
import com.cg.sakila.entity.Store;
import com.cg.sakila.service.StaffService;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;

	@PostMapping("/post")
    public ResponseEntity<String> addStaff(@RequestBody Staff staff) {
        try {
            Staff newStaff = staffService.addStaff(staff);
            return new ResponseEntity<>("Record Created Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create record", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/all")
	public List<Staff> fetchAll() {
		return staffService.fetchAll();
	}

	@GetMapping("/lastname/{ln}")
	public List<Staff> searchStaffByLastName(@PathVariable String ln) {
		return staffService.searchStaffByLastName(ln);
	}

	@GetMapping("/firstname/{fn}")
	public List<Staff> searchStaffByFirstName(@PathVariable String fn) {
		return staffService.searchStaffByFirstName(fn);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Staff> searchStaffByEmail(@PathVariable String email) {
        try {
            Staff staff = staffService.searchStaffByEmail(email);
            if (staff != null) {
                return new ResponseEntity<>(staff, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/{id}/address")
    public ResponseEntity<Staff> assignAddressToStaff(@PathVariable("id") byte staffId, @RequestBody Address address) {
        try {
            Staff staff = staffService.getStaffById(staffId);
            if (staff == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            staff.setAddress(address);
            Staff updatedStaff = staffService.updateStaff(staff);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@GetMapping("/city/{city}")
	public List<Staff> searchStaffByCity(@PathVariable String city) {
		return staffService.searchStaffByCity(city);
	}

	@GetMapping("/country/{country}")
	public List<Staff> searchStaffByCountry(@PathVariable String country) {
		return staffService.searchStaffByCountry(country);
	}

	@GetMapping("/phone/{phone}")
	public ResponseEntity<Staff> searchStaffByPhone(@PathVariable String phone) {
        try {
            Staff staff = staffService.searchStaffByPhone(phone);
            if (staff != null) {
                return new ResponseEntity<>(staff, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PutMapping("/update/fn/{id}")
	public ResponseEntity<Staff> updateStaffFirstName(@PathVariable byte id, @RequestBody Staff staff) {
        try {
            staff.setFirstName(staff.getFirstName());
            Staff updatedStaff = staffService.updateStaffFirstName(id, staff.getFirstName());
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PutMapping("/update/ln/{id}")
	public ResponseEntity<Staff> updateStaffLastName(@PathVariable byte id, @RequestBody Staff staff) {
        try {
            staff.setLastName(staff.getLastName());
            Staff updatedStaff = staffService.updateStaffLastName(id, staff.getLastName());
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PutMapping("/update/email/{id}")
	public ResponseEntity<Staff> updateStaffEmail(@PathVariable byte id, @RequestBody Staff staff) {
        try {
            staff.setEmail(staff.getEmail());
            Staff updatedStaff = staffService.updateStaffEmail(id, staff.getEmail());
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PutMapping("/update/store/{id}")
    public ResponseEntity<Staff> assignStoreToStaff(@PathVariable byte id, @RequestBody Store store) {
        try {
            Staff updatedStaff = staffService.assignStoreToStaff(id, store);
            if (updatedStaff != null) {
                return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PutMapping("/update/phone/{id}")
	public ResponseEntity<Staff> updateStaffPhone(@PathVariable byte id, @RequestBody String phone) {
        try {
            Staff updatedStaff = staffService.updateStaffPhone(id, phone);
            if (updatedStaff != null) {
                return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
