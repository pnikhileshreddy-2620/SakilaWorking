	package com.cg.sakila.service;

import org.springframework.stereotype.Service;

import com.cg.sakila.Exception.ActorDataException;
import com.cg.sakila.Exception.NotFoundException;
import com.cg.sakila.entity.Actor;
import com.cg.sakila.entity.Film;
import com.cg.sakila.repository.ActorRepository;
import com.cg.sakila.repository.FilmRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

	private ActorRepository actorRepository;
	private FilmRepository filmRepository;
	
	@Autowired
	public void setActorRepository(ActorRepository actorRepository,FilmRepository filmRepository) {
		this.filmRepository =filmRepository;
		this.actorRepository = actorRepository;
	}
	
    @Override
    public List<Actor> findAllActors() {
        return actorRepository.findAll();
    }

    
    
    
    @Override
    public Actor addActor(Actor actor) throws ActorDataException {
        try {
            return actorRepository.save(actor);
        } catch (Exception e) {
            throw new ActorDataException("Failed to add actor.", e);
        }
    }
    

    @Override
    public List<Actor> findActorsByLastName(String lastName) {
        return actorRepository.findByLastName(lastName);
    }

    @Override
    public List<Actor> findActorsByFirstName(String firstName) {
        return actorRepository.findByFirstName(firstName);
    }
    
	@Override
    public Actor updateActorLastName(Short id, String lastName) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor != null) {
            actor.setLastName(lastName);
            return actorRepository.save(actor);
        }
        return null;
    }

    @Override
    public Actor updateActorFirstName(Short id, String firstName) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor != null) {
            actor.setFirstName(firstName);
            return actorRepository.save(actor);
        }
        return null;
    }
    
    public List<Actor> getTopTenActorsByFilmCount() {
        //return actorRepository.findTopTenActorsByFilmCount();
    	return null;
    }


	@Override
	public void createActor(Actor actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Film> getFilmsByActorId(Short actorId) {
		 Optional<Actor> optionalActor = actorRepository.findById(actorId);
       if (optionalActor.isPresent()) {
           Actor actor = optionalActor.get();
           return filmRepository.findByActorsContains(actor);
       } else {
           throw new NotFoundException("Actor not found with id: " + actorId);
       }
   }
	}


