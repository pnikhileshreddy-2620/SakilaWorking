package com.cg.sakila.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="film_actor")
public class FilmActor {
	
	@EmbeddedId
	private FilmActorId id;
	
	@ManyToOne
	@MapsId("actorId")
	@JoinColumn(name="actor_id")
	private Actor actor;
	
	@ManyToOne
	@MapsId("filmId")
	@JoinColumn(name="film_id")
	private Film film;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update",nullable = false)
	private Date lastUpdate;
	
	
}
