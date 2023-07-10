package com.cg.sakila.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="country_id")
	private Short countryId;
	@Column(name="country",nullable=false)
	@Pattern(regexp = "^[A-Za-z\\s]+$", message = "Country should only contain letters and spaces")
	private String country;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update",nullable = false)
	private Date lastUpdate;
	
}
