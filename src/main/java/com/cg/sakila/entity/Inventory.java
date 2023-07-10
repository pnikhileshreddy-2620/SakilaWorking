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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="inventory_id")
	private Integer inventoryId;
	
	@ManyToOne
	@JoinColumn(name="film_id")
	private Film film;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	  @Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update",nullable = false)
	private Date lastUpdate;
	
}
