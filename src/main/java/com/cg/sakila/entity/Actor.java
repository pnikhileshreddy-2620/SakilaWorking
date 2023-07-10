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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="actor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="actor_id")
	private Short actorId;
	@Column(name="first_name")
	@NotNull
	@Pattern(regexp = "^[A-Za-z]+$")
	private String firstName;
	@Column(name="last_name")
	@NotNull
	@Pattern(regexp = "^[A-Za-z]+$")
	private String lastName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update", nullable =false, length=19)
	private Date lastUpdate;

	
	
}
