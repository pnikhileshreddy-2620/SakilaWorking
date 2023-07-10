package com.cg.sakila.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FilmActorId implements Serializable {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	@Column(name = "actor_id")
	private Short actorId;
	@Column(name = "film_id")
	private Short filmId;
	
}
