package com.cg.sakila.service;

import java.util.List;
import java.util.Map;

import com.cg.sakila.entity.Payment;

public interface PaymentService {

	List<Map<String, Object>> getRevenueByDate();
	List<Map<String, Object>> getRevenueByDateAndStore(int storeId);
	List<Map<String,Object>> getRevenueByFilm();
	List<Map<String,Object>> getRevenueByFilmAndStoreId(int StoreId);
	String addPayment(Payment payment);
	List<Payment> getAllPayments();
	List<Object[]> calculateRevenueByFilmStoreWise(int filmId);
	
}
