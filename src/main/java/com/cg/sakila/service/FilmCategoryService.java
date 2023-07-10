package com.cg.sakila.service;

import java.util.List;

import com.cg.sakila.entity.Category;
import com.cg.sakila.entity.Film;


public interface FilmCategoryService {
	//Find all Films of specified {category}
    List<Film> getFilmsByCategory(String category);
    
    void assignCategoryToFilm(Short filmId, Category category);

//	void updateCategoryToFilm(short filmId, Category category);

	

	
    

}
