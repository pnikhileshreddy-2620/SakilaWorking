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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private Short addressId;
	@Column(name="address",nullable=false)
	private String address;
	@Column(name="address2")
	private String address2;
	@Column(name="district",nullable=false)
	private String district;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="city_id")
	private City city;
	@Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$")
	@Column(name="postal_code")
	private String postalCode;
	@Pattern(regexp = "^(\\+[0-9]{1,3}[- ]?)?\\(?[0-9]{3}\\)?[- ]?[0-9]{3}[- ]?[0-9]{4}$")
	@Column(name="phone",nullable=false)
	private String phone;
	 @Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update", nullable = false)
	private Date lastUpdate;
	
}
