package com.cg.sakila.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sakila.Exception.ActorDataException;
import com.cg.sakila.entity.Actor;
import com.cg.sakila.entity.Category;
import com.cg.sakila.entity.Film;
import com.cg.sakila.repository.FilmRepository;
import com.cg.sakila.service.FilmActorService;
import com.cg.sakila.service.FilmCategoryService;
import com.cg.sakila.service.FilmService;
import com.cg.sakila.service.FilmServiceImpl;

@RestController
@RequestMapping("api/films")
public class FilmController {

    private FilmService filmService;

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    FilmActorService filmActorService;
    @Autowired
    FilmCategoryService filmCategoryService;
    @Autowired
    FilmServiceImpl filmServiceImpl;

    @PostMapping("/post")
    public ResponseEntity<String> addFilm(@RequestBody Film film) {
        try {
            filmService.addFilm(film);
            return ResponseEntity.ok("Record created successfully");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Film>> searchFilmsByTitle(@PathVariable String title) {
        List<Film> films = filmService.searchFilmsByTitle(title);
        if (films.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(films);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Film>> searchFilmsByReleaseYear(@PathVariable int year) {
        List<Film> films = filmService.searchFilmsByReleaseYear(year);
        if (films.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(films);
    }

    @GetMapping("/duration/gt/{rd}")
    public List<Film> findFilmsByRentalDurationGreaterThan(@PathVariable("rd") int rentalDuration) {
        return filmService.findFilmsByRentalDurationGreaterThan(rentalDuration);
    }

    @GetMapping("/rate/gt/{rate}")
    public List<Film> findFilmsByRentalRateGreaterThan(@PathVariable("rate") double rentalRate) {
        return filmService.findFilmsByRentalRateGreaterThan(rentalRate);
    }

    @GetMapping("/length/gt/{length}")
    public List<Film> findFilmsByLengthGreaterThan(@PathVariable("length") int length) {
        return filmService.findFilmsByLengthGreaterThan(length);
    }

    @GetMapping("/duration/lt/{rentalDuration}")
    public List<Film> findFilmsByRentalDurationLessThan(@PathVariable("rentalDuration") int rentalDuration) {
        return filmService.findFilmsByRentalDurationLessThan(rentalDuration);
    }

    @GetMapping("/rate/lt/{rentalRate}")
    public List<Film> findFilmsByRentalRateLessThan(@PathVariable("rentalRate") double rentalRate) {
        return filmService.findFilmsByRentalRateLessThan(rentalRate);
    }

    @GetMapping("/length/lt/{length}")
    public List<Film> findFilmsByLengthLessThan(@PathVariable("length") int length) {
        return filmService.findFilmsByLengthLessThan(length);
    }

    @GetMapping("/betweenyear/{from}/{to}")
    public List<Film> getFilmsReleasedBetweenYears(@PathVariable int from, @PathVariable int to) {
        return filmService.getFilmsReleasedBetweenYears(from, to);
    }

    @GetMapping("/rating/lt/{rating}")
    public List<Film> findFilmsByRatingLessThan(@PathVariable("rating") String rating) {
        return filmService.findFilmsByRatingLessThan(rating);
    }

    @GetMapping("/rating/gt/{rating}")
    public List<Film> getFilmsByRatingGreaterThan(@PathVariable String rating) {
        return filmService.findFilmsByRatingGreaterThan(rating);
    }

    @GetMapping("/language/{lang}")
    public List<Film> getFilmsByLanguage(@PathVariable("lang") String language) {
        return filmService.getFilmsByLanguage(language);
    }

    @GetMapping("/countbyyear")
    public ResponseEntity<List<Object[]>> countFilmsByYear() {
        List<Object[]> filmsByYear =filmService.countFilmsByYear();
        if (filmsByYear.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(filmsByYear);
    }

    @PutMapping("/update/title/{id}")
    public ResponseEntity<String> updateFilmTitle(@Valid @PathVariable("id") short id,
            @RequestBody Map<String, String> requestBody) {
        String newTitle = requestBody.get("newTitle");
        if (newTitle == null) {
            return ResponseEntity.badRequest().body("New title is required");
        }

        filmService.updateFilmTitle(id, newTitle);
        return ResponseEntity.status(HttpStatus.OK).body("Film title updated successfully");
    }

    @PutMapping("/update/releaseyear/{id}")
    public ResponseEntity<String> updateFilmReleaseYear(@Valid @PathVariable("id") Short id,
            @RequestBody Map<String, Integer> requestBody) {
        Integer newReleaseYear = requestBody.get("newReleaseYear");
        if (newReleaseYear == null) {
            return ResponseEntity.badRequest().body("New release year is required");
        }

        filmService.updateFilmReleaseYear(id, newReleaseYear);
        return ResponseEntity.status(HttpStatus.OK).body("Film release year updated successfully");
    }

    @PutMapping("/update/rentalduration/{id}")
    public ResponseEntity<String> updateFilmRentalDuration(@PathVariable("id") Short id,
            @RequestBody Map<String, Integer> requestBody) {
        Integer newRentalDuration = requestBody.get("newRentalDuration");
        if (newRentalDuration == null) {
            return ResponseEntity.badRequest().body("New rental duration is required");
        }

        filmService.updateFilmRentalDuration(id, newRentalDuration);
        return ResponseEntity.status(HttpStatus.OK).body("Film rental duration updated successfully");
    }

    @PutMapping("/update/rentalrate/{id}")
    public ResponseEntity<String> updateFilmRentalRate(@PathVariable("id") Short id,
            @RequestBody Map<String, Double> requestBody) {
        Double newRentalRate = requestBody.get("newRentalRate");
        if (newRentalRate == null) {
            return ResponseEntity.badRequest().body("New rental rate is required");
        }

        filmService.updateFilmRentalRate(id, newRentalRate);
        return ResponseEntity.status(HttpStatus.OK).body("Film rental rate updated successfully");
    }

    @PutMapping("/update/rating/{id}")
    public ResponseEntity<String> updateFilmRating(@PathVariable("id") Short id,
            @RequestBody Map<String, String> requestBody) {
        String newRating = requestBody.get("newRating");
        if (newRating == null) {
            return ResponseEntity.badRequest().body("New rating is required");
        }

        filmService.updateFilmRating(id, newRating);
        return ResponseEntity.status(HttpStatus.OK).body("Film rating updated successfully");
    }

    @PutMapping("/update/language/{id}")
    public ResponseEntity<String> updateFilmLanguage(@PathVariable("id") Short id,
            @RequestBody Map<String, String> requestBody) {
        String newLanguage = requestBody.get("newLanguage");
        if (newLanguage == null) {
            return ResponseEntity.badRequest().body("New language is required");
        }

        filmService.updateFilmLanguage(id, newLanguage);
        return ResponseEntity.status(HttpStatus.OK).body("Film language updated successfully");
    }

    @GetMapping("/{id}/actors")
    public List<Actor> findAllActorsByFilmId(@PathVariable("id") Short filmId) {
        return filmActorService.findAllActorsByFilmId(filmId);
    }

    @PutMapping("{id}/actor")
    public void assignActorToFilm(@PathVariable("id") Short filmId, @RequestBody Actor actor) {
        filmActorService.assignActorToFilm(filmId, actor);
    }

    @PutMapping("update/category/{id}")
    public ResponseEntity<String> assignCategoryToFilm(@PathVariable("id") short filmId,
            @RequestBody Category category) {
        filmCategoryService.assignCategoryToFilm(filmId, category);
        return ResponseEntity.ok("Category successfully assigned to the film!");
    }

    @GetMapping("/category/{category}")
    public List<Film> getFilmsByCategory(@PathVariable("category") String category) {
        return filmCategoryService.getFilmsByCategory(category);
    }

    // Exception handling for ValidationException
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Exception handling for ActorDataException
    @ExceptionHandler(ActorDataException.class)
    public ResponseEntity<String> handleActorDataException(ActorDataException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    // Exception handling for generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
