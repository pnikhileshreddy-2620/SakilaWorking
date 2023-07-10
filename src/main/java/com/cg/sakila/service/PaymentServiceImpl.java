package com.cg.sakila.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.entity.Payment;
import com.cg.sakila.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	private PaymentRepository paymentRepository;
	@Autowired
	public PaymentServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}


	@Override
	public List<Map<String, Object>> getRevenueByDate() {
		return paymentRepository.getRevenueByDate();
	}

	@Override
	public List<Map<String, Object>> getRevenueByDateAndStore(int storeId) {
		return paymentRepository.getRevenueByDateAndStore(storeId);
	}

	@Override
	public List<Map<String,Object>> getRevenueByFilm() {
		return paymentRepository.getRevenueByFilm();
	}

//	@Override
//	public List<Map<String,Object>> getRevenueByFilmAndStore(int StoreId) {
//		return paymentRepository.getRevenueByFilmAndStore(StoreId);
//	}
	
    @Override
    public List<Object[]> calculateRevenueByFilmStoreWise(int filmId) {
        return paymentRepository.calculateRevenueByFilmStoreWise(filmId);
    }

	@Override
	public List<Map<String,Object>> getRevenueByFilmAndStoreId(int storeId) {
		return paymentRepository.getRevenueByFilmAndStoreId(storeId);
	}
//	 @Override
//	    public List<Map<String, Object>> getCumulativeRevenueByFilmStore(int filmId) {
//	        return paymentRepository.getRevenueByFilmAndStoreId(filmId);
//	    }
	

	@Override
    public String addPayment(Payment payment) {
        paymentRepository.save(payment);
        return "Record Created Successfully";
    }
    
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
  
}
    
