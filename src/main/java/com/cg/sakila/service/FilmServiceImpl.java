package com.cg.sakila.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.cg.sakila.Exception.FilmNotFoundException;
import com.cg.sakila.entity.Category;
import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.Language;
import com.cg.sakila.repository.CategoryRepository;
import com.cg.sakila.repository.FilmCategoryRepository;
import com.cg.sakila.repository.FilmRepository;
import com.cg.sakila.repository.LanguageRepository;
import com.google.common.base.Optional;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

	 
    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;
    private final FilmCategoryRepository filmCategoryRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, LanguageRepository languageRepository,CategoryRepository categoryRepository,FilmCategoryRepository filmCategoryRepository) {
        this.filmRepository = filmRepository;
        this.languageRepository = languageRepository;
        this.categoryRepository=categoryRepository;
        this.filmCategoryRepository=filmCategoryRepository;
    }
    
	
//    @Override
//    public Film createFilm(Film film) {
//        return filmRepository.save(film);
//    }
    
    @Override
    public List<Film> searchFilmsByTitle(String title) {
        return filmRepository.findByTitleContaining(title);
    }
    
    @Override
    public List<Film> searchFilmsByReleaseYear(int year) {
        return filmRepository.findByReleaseYear(year);
    }
    
    @Override
    public List<Film> findFilmsByRentalDurationGreaterThan(int rentalDuration) {
        return filmRepository.findFilmsByRentalDurationGreaterThan(rentalDuration);
    }
    
    @Override
    public List<Film> findFilmsByRentalRateGreaterThan(double rentalRate) {
        return filmRepository.findFilmsByRentalRateGreaterThan(rentalRate);
    }
    
    @Override
    public List<Film> findFilmsByLengthGreaterThan(int length) {
        return filmRepository.findFilmsByLengthGreaterThan(length);
    }
    
    @Override
    public List<Film> findFilmsByRentalDurationLessThan(int rentalDuration) {
        return filmRepository.findFilmsByRentalDurationLessThan(rentalDuration);
    }
    
    @Override
    public List<Film> findFilmsByRentalRateLessThan(double rentalRate) {
        return filmRepository.findFilmsByRentalRateLessThan(rentalRate);
    }
    
    
    @Override
    public List<Film> findFilmsByLengthLessThan(int length) {
        return filmRepository.findFilmsByLengthLessThan(length);
    }
    
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Film> getFilmsReleasedBetweenYears(int fromYear, int toYear) {
        Query query = entityManager.createQuery("SELECT f FROM Film f WHERE f.releaseYear BETWEEN :fromYear AND :toYear");
        query.setParameter("fromYear", fromYear);
        query.setParameter("toYear", toYear);
        return query.getResultList();
    }
    
    
    
    @Override
    public List<Film> findFilmsByRatingLessThan(String rating) {
        TypedQuery<Film> query = entityManager.createQuery(
                "SELECT f FROM Film f WHERE ASCII(f.rating) < ASCII(:rating)", Film.class);
        query.setParameter("rating", rating);
        return query.getResultList();
    }
    
    @Override
    public List<Film> findFilmsByRatingGreaterThan(String rating) {
        TypedQuery<Film> query = entityManager.createQuery(
                "SELECT f FROM Film f WHERE ASCII(f.rating) > ASCII(:rating)", Film.class);
        query.setParameter("rating", rating);
        return query.getResultList();
    }
    
	 @Override
	    public List<Film> getFilmsByLanguage(String language) {
	        return filmRepository.findByLanguageName(language);
	}
	 
	 
	    //Update Title of a Film
	    @Override
	    public void updateFilmTitle(short id, String newTitle) {
	        filmRepository.updateFilmTitleById(id, newTitle);
	    }
	    
	    //Update Release Year of a Film
	    @Override
	    public void updateFilmReleaseYear(Short id, Integer newReleaseYear) {
	        Film film = filmRepository.findById(id).orElseThrow();
	        film.setReleaseYear(newReleaseYear);
	        filmRepository.save(film);
	    }
	    
	    //Update Rental Duration of a Film
	    @Override
	    public void updateFilmRentalDuration(Short id, Integer newRentalDuration) {
	        Film film = filmRepository.findById(id).orElseThrow();
	        film.setRentalDuration(newRentalDuration);
	        filmRepository.save(film);
	    }
	    
	    
	    //Update Rental Rate of a Film
	    @Override
	    public void updateFilmRentalRate(Short id, Double newRentalRate) {
	        Film film = filmRepository.findById(id).orElseThrow();
	        film.setRentalRate(newRentalRate);
	        filmRepository.save(film);
	    }
	    
	  //Update Rating of a Film
	    @Override
	    public void updateFilmRating(Short id, String newRating) {
	        Film film = filmRepository.findById(id).orElseThrow();
	        film.setRating(newRating);
	        filmRepository.save(film);
	    }
	    
	    //Update Language of a Film
	    @Override
	    public void updateFilmLanguage(Short filmId, String languageName) {
	        Film film = filmRepository.findById(filmId)
	                .orElseThrow(() -> new IllegalArgumentException("Film not found with id: " + filmId));

	        Language language = languageRepository.findByName(languageName);
	        if (language == null) {
	            throw new IllegalArgumentException("Language not found with name: " + languageName);
	        }

	        film.setLanguage(language);
	        filmRepository.save(film);
	    }

	    
	    //count film by year
	    @Override
	    public List<Object[]> countFilmsByYear() {
	        return filmRepository.countFilmsByYear();
	    }

		@Override
		public Film getFilmById(Short filmId) {
			return filmRepository.getById(filmId);
		}
		@Override
	    public Film addFilm(Film film) {
	        return filmRepository.save(film);
	    }
	
		






		
}
