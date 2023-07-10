package com.cg.sakila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.entity.Actor;
import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.FilmActor;
import com.cg.sakila.entity.FilmActorId;
import com.cg.sakila.repository.FilmActorRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmActorServiceImpl implements FilmActorService {

    private final FilmActorRepository filmActorRepository;

    @Autowired
    public FilmActorServiceImpl(FilmActorRepository filmActorRepository) {
        this.filmActorRepository = filmActorRepository;
    }

  //Find all Actors of a Film by Film id
    @Override
    public List<Actor> findAllActorsByFilmId(Short filmId) {
        List<FilmActor> filmActors = filmActorRepository.findAllByIdFilmId(filmId);
        return filmActors.stream()
                .map(FilmActor::getActor)
                .collect(Collectors.toList());
    }
    
    
    @Override
    public List<Film> getFilmsByActorId(Short actorId) {
    	
    	List<FilmActor> filmActors = filmActorRepository.findByActorId(actorId);
    	return filmActors.stream()
    			.map(FilmActor::getFilm)
    			.collect(Collectors.toList());
//        return filmActorRepository.findByActorId(actorId);
    }
    
    
    
    
  //Assign Actor to a Film
    @Override
    public void assignActorToFilm(Short filmId, Actor actor) {
        Film film = new Film();
        film.setFilmId(filmId);

        FilmActorId filmActorId = new FilmActorId();
        filmActorId.setFilmId(filmId);
        filmActorId.setActorId(actor.getActorId());

        FilmActor filmActor = new FilmActor();
        filmActor.setId(filmActorId);
        filmActor.setFilm(film);
        filmActor.setActor(actor);
        filmActor.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        filmActorRepository.save(filmActor);
    }
    
    //Assign Film to a Actor
    
    @Override
    public void assignFilmToActor(Short actorId, Film film) {
        Actor actor = new Actor();
        actor.setActorId(actorId);

        FilmActorId filmActorId = new FilmActorId();
        filmActorId.setActorId(actorId);
        filmActorId.setFilmId(film.getFilmId());

        FilmActor filmActor = new FilmActor();
        filmActor.setId(filmActorId);
        filmActor.setActor(actor);
        filmActor.setFilm(film);
        filmActor.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        filmActorRepository.save(filmActor);
        
    }

	@Override
	public Film addFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}
}
