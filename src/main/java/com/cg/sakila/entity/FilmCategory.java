package com.cg.sakila.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="film_category")
public class FilmCategory {
	@EmbeddedId
	private FilmCategoryId id;
	
	@ManyToOne
	@MapsId("filmId")
	@JoinColumn(name="film_id")
	private Film film;
	
	@ManyToOne
	@MapsId("categoryId")
	@JoinColumn(name="category_id")
	private Category category;
	  @Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update",nullable = false)
	private Date lastUpdate;
	
	
}
