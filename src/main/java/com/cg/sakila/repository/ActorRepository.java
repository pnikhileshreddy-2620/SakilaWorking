package com.cg.sakila.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.cg.sakila.entity.Actor;
import com.cg.sakila.entity.Film;

@Transactional
@Repository
public interface ActorRepository extends JpaRepository<Actor,Short>{
	
	List<Actor> findByLastName(String lastName);
	List<Actor> findByFirstName(String firstName);
	//@Query("SELECT a FROM Actor a ORDER BY SIZE(a.films) DESC LIMIT 10")
	//List<Actor> findTopTenActorsByFilmCount();
//	List<Actor> findTop10ByOrderByFilmsDesc();
	
}
