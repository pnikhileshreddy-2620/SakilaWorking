package com.cg.sakila.entity;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name="film")
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private Short filmId;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "release_year")
	@Pattern(regexp = "^(19|20)\\d{2}$", message = "Invalid release year")
	private int releaseYear;
	@ManyToOne
	@JoinColumn(name = "language_id",nullable = false)
	@JsonIgnoreProperties("films")
//	@JsonIgnore
	private Language language;
	@ManyToOne
	@JoinColumn(name = "original_language_id")
	private Language originalLanguage;
	@Column(name = "rental_duration", nullable = false)
	private int rentalDuration;
	@Column(name = "rental_rate", nullable = false)
	private Double rentalRate;
	@Column(name = "length")
	private int length;
	@Column(name = "replacement_cost", nullable = false)
	private Double replacementCost;
	@Column(name = "rating", nullable = false)
	@Pattern(regexp = "^(G|PG|PG-13|R|NC-17)$", message = "Invalid rating")
	private String rating;
	@Column(name = "special_features",columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes') DEFAULT NULL")
	private String specialFeatures;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update", nullable = false)
	private Date lastUpdate;

	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
	private Set<Actor> actors;
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;

	public void setCategory(Category category) {
		// TODO Auto-generated method stub
		
	}
	
}
