package com.cg.sakila.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Short customerId;
	@Column(name="store_id",nullable=false)
	private Byte storeId;
	@Column(name="first_name",nullable=false)
	@Pattern(regexp = "^[A-Za-z]+$", message = "First name should only contain letters")
	private String firstName;
	@Column(name="last_name",nullable=false)
	@Pattern(regexp = "^[A-Za-z]+$", message = "First name should only contain letters")
	private String lastName;
	@Column(name = "email")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format")
	private String email;
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	@Column(name = "active", nullable = false)
	@Pattern(regexp = "^[01]$", message = "Active should be either 0 or 1")
	private int active;
	
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;
	
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update", nullable = false)
	private Date lastUpdate;
	
}
