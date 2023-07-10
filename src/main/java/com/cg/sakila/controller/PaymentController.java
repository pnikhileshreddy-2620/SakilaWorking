package com.cg.sakila.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sakila.entity.Payment;
import com.cg.sakila.service.PaymentService;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

	private PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}


	//working
	@GetMapping("/revenue/datewise")
	public List<Map<String, Object>> getRevenueByDate() {
		return paymentService.getRevenueByDate();
	}

	//working
	@GetMapping("/revenue/datewise/store/{id}")
	public List<Map<String,Object>> getRevenueByDateAndStore(@PathVariable("id") int id) {
		return paymentService.getRevenueByDateAndStore(id);
	}

	//working
	@GetMapping("/revenue/filmwise")
	public List<Map<String,Object>> getRevenueByFilm() {
		return paymentService.getRevenueByFilm();
	}
	//working
    @GetMapping("/revenue/film/{id}")
    public ResponseEntity<List<Map<String, Object>>> getFilmRevenueStoreWise(@PathVariable("id") int filmId) {
        List<Object[]> revenueData = paymentService.calculateRevenueByFilmStoreWise(filmId);
        
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] result : revenueData) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("store", result[0]);
            entry.put("amount", result[1]);
            response.add(entry);
        }
        
        return ResponseEntity.ok(response);
    }
    
    

	
	@GetMapping("/revenue/films/store/{id}")
	public List<Map<String,Object>> getRevenueByFilmAndStoreId(@PathVariable int id) {
		return paymentService.getRevenueByFilmAndStoreId(id);
	}
	

	
	@PostMapping("/add")
    public ResponseEntity<String> addPayment(@RequestBody Payment payment) {
        String result = paymentService.addPayment(payment);
        return ResponseEntity.ok(result);
    }
	
	//working
	@GetMapping("/getall")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
