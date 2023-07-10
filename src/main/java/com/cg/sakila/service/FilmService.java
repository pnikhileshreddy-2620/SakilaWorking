package com.cg.sakila.service;

import java.util.List;

import com.cg.sakila.entity.Category;
import com.cg.sakila.entity.Film;

public interface FilmService {


	//Add new Film object in DB
		
		Film addFilm(Film film);
		
		List<Film> searchFilmsByTitle(String title);
		
	    List<Film> searchFilmsByReleaseYear(int year);
	    
	    List<Film> findFilmsByRentalDurationGreaterThan(int rentalDuration);
	    
	    List<Film> findFilmsByRentalRateGreaterThan(double rentalRate);
	    
	    List<Film> findFilmsByLengthGreaterThan(int length);
	    
	    List<Film> findFilmsByRentalDurationLessThan(int rentalDuration);
	    
	    List<Film> findFilmsByRentalRateLessThan(double rentalRate);
	    
	    List<Film> findFilmsByLengthLessThan(int length);
	    
	    List<Film> getFilmsReleasedBetweenYears(int fromYear, int toYear);
	    
	    List<Film> findFilmsByRatingLessThan(String rating);
	    
	    List<Film> findFilmsByRatingGreaterThan(String rating);
	    
		List<Film> getFilmsByLanguage(String language);
		
		//Display number of Films released by each Year
//		Map<Integer, Long> countFilmsByYear();
		
	    //Update Title of a Film
		void updateFilmTitle(short id, String newTitle);
		
		//Update Release Year of a Film
		void updateFilmReleaseYear(Short id, Integer newReleaseYear);
		
	    //Update Rental Duration of a Film
		void updateFilmRentalDuration(Short id, Integer newRentalDuration);
		
	    //Update Rental Rate of a Film
		void updateFilmRentalRate(Short id, Double newRentalRate);
		
		//Update Rating of a Film
		void updateFilmRating(Short id, String newRating);
		
	    //Update Language of a Film
	    void updateFilmLanguage(Short filmId, String languageName);
	    
	    
	    //count film by year
	    List<Object[]> countFilmsByYear();

	    //for inventory (Display inventory(count) of a Film in all Stores)
		Film getFilmById(Short filmId);



		
		
	    
	    //
	
}
