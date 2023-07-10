package com.cg.sakila.service;

import com.cg.sakila.Exception.ActorDataException;
import com.cg.sakila.entity.Actor;
import com.cg.sakila.entity.Film;

import java.util.Collection;
import java.util.List;

public interface ActorService {
	
	Collection<Actor> findActorsByLastName(String lastName);
	Collection<Actor> findActorsByFirstName(String firstName);
	Collection<Actor> findAllActors();
	Actor addActor(Actor actor) throws ActorDataException;
	List<Actor> getTopTenActorsByFilmCount();
	Actor updateActorFirstName(Short id, String firstName);
	Actor updateActorLastName(Short id, String lastName);
	void createActor(Actor actor);
	public	List<Film> getFilmsByActorId(Short actorId);
   
}
