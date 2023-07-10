package com.cg.sakila.service;

import java.util.List;

import com.cg.sakila.entity.Actor;
import com.cg.sakila.entity.Film;

public interface FilmActorService {
	
	//Find all Actors of a Film by Film id
    List<Actor> findAllActorsByFilmId(Short filmId);
    
    //find all films by actor id
    List<Film> getFilmsByActorId(Short actorId);
    
  //Assign Actor to a Film
    void assignActorToFilm(Short filmId, Actor actor);
    
    void assignFilmToActor(Short actorId, Film film);

	Film addFilm(Film film);    

}
