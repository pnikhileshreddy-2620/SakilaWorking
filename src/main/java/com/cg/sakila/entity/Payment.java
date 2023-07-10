package com.cg.sakila.entity;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@JsonIgnoreProperties({"rental"})
@Table(name="payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="payment_id")
	private Integer paymentId;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;
	@ManyToOne
	@JoinColumn(name="rental_id")
	private Rental rental;
	@Column(name="amount",nullable = false)
	
	@DecimalMin(value = "0.0", inclusive = true, message = "Amount must be greater than or equal to 0")
	@DecimalMax(value = "9999999.99", inclusive = true, message = "Amount must be less than or equal to 9999999.99")
	private BigDecimal amount;
	  @Temporal(TemporalType.TIMESTAMP)
	@Column(name="payment_date", nullable = false)
	private Date paymentDate;
	  @Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update", nullable = false)
	private Date lastUpdate;
	
}
