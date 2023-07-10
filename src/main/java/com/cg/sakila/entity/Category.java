package com.cg.sakila.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id",columnDefinition = "TINYINT")
	private Byte categoryId;
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "Name should only contain letters and numbers")
	@Column(name="name",nullable=false)
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update",nullable=false)
	private  Date lastUpdate;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "categories")
	private Set<Film> films = new HashSet<>();
}
