package com.cg.sakila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.entity.Category;
import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.FilmCategory;
import com.cg.sakila.entity.FilmCategoryId;
import com.cg.sakila.repository.FilmCategoryRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FilmCategoryServiceImpl implements FilmCategoryService {

	 private final FilmCategoryRepository filmCategoryRepository;

	    @Autowired
	    public FilmCategoryServiceImpl(FilmCategoryRepository filmCategoryRepository) {
	        this.filmCategoryRepository = filmCategoryRepository;
	    }

	  //Find all Films of specified {category}
	    @Override
	    public List<Film> getFilmsByCategory(String category) {
	        List<FilmCategory> filmCategories = filmCategoryRepository.findByCategoryName(category);
	        return filmCategories.stream()
	                .map(FilmCategory::getFilm)
	                .toList();
	    }


	    //Assign Actor to a Film
	    @Override
	    public void assignCategoryToFilm(Short filmId, Category category) {
	        Film film = new Film();
	        film.setFilmId(filmId);

//	        FilmActorId filmActorId = new FilmActorId();
//	        filmActorId.setFilmId(filmId);
//	        filmActorId.setActorId(actor.getActorId());
	        
	        FilmCategoryId filmCategoryId=new FilmCategoryId();
	        filmCategoryId.setFilmId(filmId);
	        filmCategoryId.setCategoryId(category.getCategoryId());
	        

//	        FilmActor filmActor = new FilmActor();
//	        filmActor.setId(filmActorId);
//	        filmActor.setFilm(film);
//	        filmActor.setActor(actor);
//	        filmActor.setLastUpdate(new Timestamp(System.currentTimeMillis()));
	        
	        FilmCategory filmCategory=new FilmCategory();
	        filmCategory.setId(filmCategoryId);
	        filmCategory.setFilm(film);
	        filmCategory.setCategory(category);
	        filmCategory.setLastUpdate(new Timestamp(System.currentTimeMillis()));

//	        filmActorRepository.save(filmActor);
	        filmCategoryRepository.save(filmCategory);
	    }

		
	 
}
