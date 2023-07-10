package com.cg.sakila.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.FilmCategory;
import com.cg.sakila.entity.FilmCategoryId;

import java.util.List;

@Repository
public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {
	//Find all Films of specified {category}
    List<FilmCategory> findByCategoryName(String category);

	Film findFilmById(short filmId);

	void save(Film film);
    
    
}
