package com.cg.sakila.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.sakila.entity.FilmActor;
import com.cg.sakila.entity.FilmActorId;

import java.util.List;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {
	
	//Find all Actors of a Film by Film id
    List<FilmActor> findAllByIdFilmId(Short filmId);
    
	//Find all Films by actor id
    @Query("SELECT fa FROM FilmActor fa JOIN fa.actor a WHERE a.id = :actorId")
    List<FilmActor> findByActorId(@Param("actorId") Short actorId);
    
    
}
